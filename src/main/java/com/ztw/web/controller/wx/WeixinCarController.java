package com.ztw.web.controller.wx;

import com.ztw.basic.tools.PageableTools;
import com.ztw.car.dto.CarDto;
import com.ztw.car.iservice.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 车辆管理微信首页-主要是二手车页面
 * Created by zsl-pc on 2016/10/14.
 */
@Controller
@RequestMapping(value = "wx/car")
public class WeixinCarController {

    @Autowired
    private ICarService carService;

    /** 车辆首页 */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, Integer page, HttpServletRequest request) {
        Page<CarDto> datas = carService.querySale(PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "wx/car/index";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id, HttpServletRequest request) {
        CarDto cd = carService.queryOne(id);
        model.addAttribute("carDto", cd);
        return "wx/car/detail";
    }
}
