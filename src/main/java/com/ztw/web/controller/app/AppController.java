package com.ztw.web.controller.app;

import com.ztw.basic.exception.SystemException;
import com.ztw.basic.tools.BaseSpecification;
import com.ztw.basic.tools.SearchCriteria;
import com.ztw.car.iservice.*;
import com.ztw.car.model.*;
import com.ztw.car.tools.DateTools;
import com.ztw.deposit.model.Deposit;
import com.ztw.people.iservice.IPeopleService;
import com.ztw.people.model.People;
import com.ztw.weixin.iservice.IWeixinUserService;
import com.ztw.weixin.tools.WeixinXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/28.
 */
@RestController
@RequestMapping(value = "app")
public class AppController {

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IPeopleService peopleService;

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IWeixinUserService weixinUserService;

    /** 品牌 */
    @RequestMapping(value = "brands", method = RequestMethod.GET)
    public List<CarBrand> brands(HttpServletRequest request) {
        List<CarBrand> list = carBrandService.findAll();
        return list;
    }

    /** 类型 */
    @RequestMapping(value = "types", method = RequestMethod.GET)
    public List<CarType> types(HttpServletRequest request) {
        List<CarType> list = carTypeService.findAll();
        return list;
    }

    /** 车辆详细信息 */
    @RequestMapping(value = "carDetail", method = RequestMethod.GET)
    public CarInfo carDetail(Integer id, HttpServletRequest request) {
        if(id==null || id<=0) {return null;}
        CarInfo info = carInfoService.findOne(id);
        return info;
    }

    /** 列表页 */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<CarInfoDto> list(HttpServletRequest request) {
        List<CarInfoDto> res = new ArrayList<>();
        Integer brandId = 0, typeId = 0;
        Float startPrice = 0f, endPrice = 0f;
        try { brandId = Integer.parseInt(request.getParameter("brandId")); } catch (Exception e) { }
        try { typeId = Integer.parseInt(request.getParameter("typeId")); } catch (Exception e) { }
        try { startPrice = Float.parseFloat(request.getParameter("startPrice")); } catch (Exception e) { }
        try { endPrice = Float.parseFloat(request.getParameter("endPrice")); } catch (Exception e) { }

        Specifications<CarInfo> infoParam = null;
        if(brandId!=null && brandId>0) {
            if(infoParam==null) {
                infoParam = Specifications.where(new BaseSpecification<>(new SearchCriteria("brandId", "eq", brandId)));
            } else {
                infoParam = infoParam.and(new BaseSpecification<>(new SearchCriteria("brandId", "eq", brandId)));
            }
        }

        if(typeId!=null && typeId>0) {
            if(infoParam==null) {
                infoParam = Specifications.where(new BaseSpecification<>(new SearchCriteria("typeId", "eq", typeId)));
            } else {
                infoParam = infoParam.and(new BaseSpecification<>(new SearchCriteria("typeId", "eq", typeId)));
            }
        }

        if(startPrice!=null && startPrice>=0) {
            if(infoParam==null) {
                infoParam = Specifications.where(new BaseSpecification<>(new SearchCriteria("rjj", "ge", startPrice)));
            } else {
                infoParam = infoParam.and(new BaseSpecification<>(new SearchCriteria("rjj", "ge", startPrice)));
            }
        }

        if(endPrice!=null && endPrice>0) {
            if(infoParam==null) {
                infoParam = Specifications.where(new BaseSpecification<>(new SearchCriteria("rjj", "le", endPrice)));
            } else {
                infoParam = infoParam.and(new BaseSpecification<>(new SearchCriteria("rjj", "le", endPrice)));
            }
        }

        List<CarInfo> infoList = carInfoService.findAll(infoParam);
        for(CarInfo ci : infoList) {
            CarInfoDto cid = new CarInfoDto(ci, carService.queryCount(ci.getId(), "1"));
            res.add(cid);
        }

        return res;
    }

    /** 下订单 */
    @RequestMapping(value = "addOrder", method = RequestMethod.GET)
    public ResDto addOrder(HttpServletRequest request) {
        String name = "", phone = "", identity = "", remind = "";
        Integer carInfoId = 0, days = 1;

        name = request.getParameter("name"); //姓名
        phone = request.getParameter("phone"); //手机号码
        identity = request.getParameter("identity"); //身份证号
        remind = request.getParameter("remind"); //备注

        if(phone==null || phone.length()!=11) {return new ResDto(ResDto.ERROR, "手机号码不能为空");}

        try { carInfoId = Integer.parseInt(request.getParameter("infoId"));} catch (NumberFormatException e) {}
        try { days = Integer.parseInt(request.getParameter("days"));} catch (NumberFormatException e) {}

        CarInfo info = null;
        People p = null;
        if(carInfoId!=null && carInfoId>0) {
            info = carInfoService.findById(carInfoId);
        }
        if(identity!=null && !"".equals(identity)) {
            p = peopleService.findByIdentity(identity);
        }

        Orders orders = new Orders();

        orders.setCostumerName(name);
        orders.setRemind(remind);
        orders.setCostumerIdentity(identity);
        orders.setCostumerPhone(phone);

        if(p!=null) {
            orders.setCostumerId(p.getId());
            orders.setCostumerAddress(p.getAddress());
            orders.setCostumerAge(p.getAge());
            orders.setCostumerIdentity(p.getIdentity());
            orders.setCostumerName(p.getName());
            orders.setCostumerSex(p.getSex());
        }
        if(info!=null) {
            orders.setBrandName(info.getBrandName());
            orders.setBrandId(info.getBrandId());
            orders.setCarSerial(info.getPpxl());
            orders.setCarType(info.getTypeName());
            orders.setTypeId(info.getTypeId());
            orders.setInfoId(info.getId());
            orders.setCurPrice(info.getRjj());
        }

        orders.setCreateDate(new Date());
        orders.setCreateDateLong(System.currentTimeMillis());
        orders.setIsOverdue(0); //未逾期
        orders.setLllegalCount(0);
        orders.setLllegalMoney(0f);
        orders.setLllegalScore(0);
        orders.setStatus("0"); //未提车
        orders.setType("1"); //客户下单
        if(days>0) {
            orders.setDays(days);
            orders.setMoney(info!=null?info.getRjj()*days:-1);
            orders.setNeedMoney(info!=null?info.getRjj()*days:-1);
            orders.setNeedBackDate(DateTools.plusDay(days));
            orders.setNeedBackDay(DateTools.plusDay(days, ""));
            orders.setNeedBackLong(DateTools.plusDayByLong(days));
        }


        ordersService.save(orders);

        List<String> adminOpenid = weixinUserService.findAdmin();
        StringBuffer sb = new StringBuffer();
        sb.append("\\n车型：").append(info==null?"未选择":info.getBrandName()+info.getPpxl())
          .append("\\n电话：").append(phone)
          .append("\\n姓名：").append(name)
          .append("\\n请赶紧跟进吧！");
        WeixinXmlUtil.eventRemind(adminOpenid, "租车新订单通知", "租车提醒", "消息内容："+sb.toString(), null);

        return new ResDto(ResDto.SUC, ResDto.OK);
    }
}
