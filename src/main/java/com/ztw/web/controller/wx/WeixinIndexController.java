package com.ztw.web.controller.wx;

import com.ztw.basic.tools.BaseSpecification;
import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.basic.tools.SearchCriteria;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarInfo;
import com.ztw.web.controller.app.CarInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/25.
 */
@Controller
@RequestMapping(value = "wx/")
public class WeixinIndexController {

    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ICarService carService;

    /** 微信首页 */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer page, HttpServletRequest request) {
//        Page<CarInfo> datas = carInfoService.findAll(new ParamFilterTools<CarInfo>().buildSpecification(model, request), PageableTools.basicPage(page));
//        model.addAttribute("datas", datas);
//
//        model.addAttribute("typeList", carTypeService.findAll()); //车辆类型列表
//        model.addAttribute("brandList", carBrandService.findAll()); //车辆品牌列表

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

        model.addAttribute("datas", res);

        return "wx/index";
    }

}
