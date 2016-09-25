package com.ztw.legal.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ztw.car.iservice.IOrdersService;
import com.ztw.car.model.Car;
import com.ztw.car.model.Orders;
import com.ztw.legal.dto.DateTimeDto;
import com.ztw.legal.dto.LegalDto;
import com.ztw.legal.dto.SearchInfo;
import com.ztw.legal.model.Legal;
import com.ztw.legal.service.ILegalService;
import com.ztw.platform.ws.IPlatformOldWebService;
import com.ztw.platform.ws.PlatformOldWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/23.
 */
@Component
public class QueryLegalTools {
    private static final String sourceCode = "ND737SJWN6W1L40WOT3K7958880013";
    private static final String urlCode = "N01";

    /*private static LegalTools instance;
    private LegalTools(){}
    public static LegalTools getInstance() {
        if(instance==null) {instance = new LegalTools();}
        return instance;
    }*/

    @Autowired
    private ILegalService legalService;

    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询违章信息
     * @param carType 车辆号牌种类
     * @param carNo 车辆号牌号码
     */
    public void queryLegalAndSave(String carType, String carNo) {
        DateTimeDto dtd = DateTimeTools.buildDateDto("12"); //时间为一年
        SearchInfo searchInfo = new SearchInfo(carType, carNo, dtd.getStarttime(), dtd.getEndtime(), "云南");

        IPlatformOldWebService pws = new PlatformOldWebService().getPlatformOldWebServicePort();
        String json = JSON.toJSONString(searchInfo);
        String result = pws.uploadObject(sourceCode, urlCode, json);
        JSONArray jsonArray = JSONArray.parseArray(result);
        List<Long> timeList = new ArrayList<>();
        LegalDto legalDto;
        for(int i = 0; i < jsonArray.size(); i++) {
            legalDto = JSON.toJavaObject(jsonArray.getJSONObject(i), LegalDto.class);

            timeList.add(legalDto.getLegalong());

            // 用车牌号码，车牌种类， 违章时间（Long）标识一条唯一的违章记录，避免重复写入
            Legal legalExist = legalService.findOne(carNo, carType, legalDto.getLegalong());

            if(legalExist == null) {
                Legal legal = new Legal();
                legal.setCpzl(carType); legal.setCphm(carNo);
                // 添加违章记录
                legal.setLegalDto(legalDto);
                legal.setStatus("0"); //默认设置为未处理

                Orders orders = ordersService.queryOne(legal.getLegalong());
                if(orders!=null) {
                    legal.setTenantSfz(orders.getCostumerIdentity());
                    legal.setTenantName(orders.getCostumerName());
                    legal.setPhone(orders.getCostumerPhone());
                    legal.setRentId(orders.getId());

                    //修改订单中的违章数据
                    ordersService.updateLegal(Float.valueOf(String.valueOf(legal.getMoney())), legal.getScore(), orders.getId());
                }

                legalService.save(legal);

            } else if(legalExist.getRentId()==null || legalExist.getRentId() <=  0) { //如果之前获取出来的数据未找到订单数据
                Orders orders = ordersService.queryOne(legalExist.getLegalong());
                if(orders!=null) {
                    legalExist.setTenantSfz(orders.getCostumerIdentity());
                    legalExist.setTenantName(orders.getCostumerName());
                    legalExist.setPhone(orders.getCostumerPhone());
                    legalExist.setRentId(orders.getId());

                    //修改订单中的违章数据
                    ordersService.updateLegal(Float.valueOf(String.valueOf(legalExist.getMoney())), legalExist.getScore(), orders.getId());
                }

                legalService.save(legalExist);
            }
        }

        legalService.updateStatusByNotIn(timeList.toArray(new Long[timeList.size()]), carType, carNo);
    }

    public void queryLegalAndSave(Car car) {
        queryLegalAndSave(car.getCarType(), car.getCarNo());
    }
}
