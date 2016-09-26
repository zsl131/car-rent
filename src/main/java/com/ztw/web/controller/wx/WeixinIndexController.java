package com.ztw.web.controller.wx;

import com.ztw.basic.tools.PageableTools;
import com.ztw.basic.tools.ParamFilterTools;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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

    /** 微信首页 */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer page, HttpServletRequest request) {
        Page<CarInfo> datas = carInfoService.findAll(new ParamFilterTools<CarInfo>().buildSpecification(model, request), PageableTools.basicPage(page));
        model.addAttribute("datas", datas);

        model.addAttribute("typeList", carTypeService.findAll()); //车辆类型列表
        model.addAttribute("brandList", carBrandService.findAll()); //车辆品牌列表
        return "wx/index";
    }

}
