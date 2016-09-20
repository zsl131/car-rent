package com.ztw.legal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ztw.legal.model.Legal;
import com.ztw.legal.model.LegalDto;
import com.ztw.legal.model.SearchInfo;
import com.ztw.platform.ws.IPlatformOldWebService;
import com.ztw.platform.ws.PlatformOldWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

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
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ILegalService legalService;

    /**
     * 违章查询
     * @param searchInfo 查询需要的信息
     * @param tenantName 租户姓名
     * @param tenantSfz 租户身份证
     * @param phone 租户联系电话
     * @param rentId 订单唯一标识
     */
    public void searchAndSave(SearchInfo searchInfo, String tenantName, String tenantSfz, String phone, Integer rentId) {
        Legal legal = new Legal();
        legal.setTenantName(tenantName);
        legal.setTenantSfz(tenantSfz);
        legal.setPhone(phone);
        legal.setRentId(rentId);
        legal.setCphm(searchInfo.getHphm());

        String json = JSON.toJSONString(searchInfo);
        String result = pws.uploadObject(sourceCode, urlCode, json);
        JSONArray jsonArray = JSONArray.parseArray(result);
        System.out.println(result);
        LegalDto legalDto;
        for(int i = 0; i < jsonArray.size(); i++) {
            legalDto = JSON.toJavaObject(jsonArray.getJSONObject(i), LegalDto.class);
            legal.setType(legalDto.getType());
            legal.setOrgName(legalDto.getOrgName());
            legal.setLegalNo(legalDto.getLegalNo());
            legal.setTime(legalDto.getTime());
            legal.setBehavior(legalDto.getBehavior());
            legal.setAddress(legalDto.getAddress());
            legal.setMoney(legalDto.getMoney());
            legal.setScore(legalDto.getScore());
            legalService.save(legal);
        }
    }
}
