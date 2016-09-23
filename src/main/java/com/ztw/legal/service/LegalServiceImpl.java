package com.ztw.legal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ztw.deposit.model.Deposit;
import com.ztw.deposit.service.IDepositService;
import com.ztw.legal.dto.TenantInfo;
import com.ztw.legal.model.Legal;
import com.ztw.legal.dto.LegalDto;
import com.ztw.legal.dto.SearchInfo;
import com.ztw.platform.ws.IPlatformOldWebService;
import com.ztw.platform.ws.PlatformOldWebService;
import org.hibernate.type.DoubleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 交通违章查询业务层
 * Created by 马旭 on 2016/9/19.
 */
@Component
public class LegalServiceImpl {
    // 查询违章json数据格式
    // String json ="{'hpzl':'02', 'hphm':'云CEX999', 'kssj':'2015-01-01 00:00:00', 'jssj':'2016-07-08 00:00:00', 'wzsf':'云南'}";
    private static final IPlatformOldWebService pws = new PlatformOldWebService().getPlatformOldWebServicePort();
    private static final String sourceCode = "ND737SJWN6W1L40WOT3K7958880013";
    private static final String urlCode = "N01";

    @Autowired
    private ILegalService legalService;

    @Autowired
    private IDepositService depositService;

    /**
     * 违章查询
     * @param searchInfo 查询需要的信息
     * @param tenantInfo 租户基本信息，包括订单号
     */
    public void searchAndSave(SearchInfo searchInfo, TenantInfo tenantInfo) {

        // 设定违章的基本信息
        Legal legal = new Legal(tenantInfo, searchInfo);

        String json = JSON.toJSONString(searchInfo);
        String result = pws.uploadObject(sourceCode, urlCode, json);
        JSONArray jsonArray = JSONArray.parseArray(result);

        LegalDto legalDto;
        for(int i = 0; i < jsonArray.size(); i++) {
            legalDto = JSON.toJavaObject(jsonArray.getJSONObject(i), LegalDto.class);

            // 用车牌号码，车牌种类， 违章时间（Long）标识一条唯一的违章记录，避免重复写入
            Legal legalExist = legalService.findOne(legal.getCphm(), legal.getCpzl(), legalDto.getLegalong());

            if(legalExist == null) {
                // 添加违章记录
                legal.setLegalDto(legalDto);
                legalService.save(legal);

                // 更新保证金违章罚款
                Deposit existDeposit = depositService.findByRentId(legal.getRentId());
                Double legalMoney = 0.0;

                if(existDeposit.getLegalMoney() == null || existDeposit.getLegalMoney() <= 0) {
                    // do nothing
                } else {
                    legalMoney = legalMoney + existDeposit.getLegalMoney();
                }

                if(legal.getMoney() == null || legal.getMoney() <= 0) {
                    // do nothing
                } else {
                    legalMoney = legalMoney + legal.getMoney();
                }

                Double returnMoney = existDeposit.getMoney() - legalMoney;
                depositService.updateByRentId(legal.getRentId(), legalMoney, returnMoney);
            }
        }
    }
}
