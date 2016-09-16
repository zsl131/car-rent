package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.ConfigTools;
import com.ztw.basic.tools.MyBeanUtils;
import com.ztw.basic.tools.PageableTools;
import com.ztw.car.iservice.ICarBrandService;
import com.ztw.car.iservice.ICarInfoService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/15.
 */
@Controller
@RequestMapping(value ="/admin/carInfo")
@AdminAuth(name = "车辆信息管理",psn = "车库管理",orderNum = 1,porderNum = 2,pentity = 0)
public class CarInfoController {

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarInfoService carInfoService;

    @Autowired
    private ICarBrandService carBrandService;

    @Autowired
    private ConfigTools configTools;

    @AdminAuth(name = "车辆信息列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<CarInfo> datas = carInfoService.pageAll(PageableTools.basicPage(page));
        model.addAttribute("datas", datas);
        return "admin/carInfo/list";
    }

    @Token(flag= Token.READY)
    @AdminAuth(name = "添加车辆信息", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        CarInfo ci = new CarInfo();
        model.addAttribute("carInfo", ci);
        return "admin/carInfo/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, CarInfo carInfo, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            carInfoService.save(carInfo);
        }
        return "redirect:/admin/carInfo/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改车辆信息", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        CarInfo ci = carInfoService.findById(id);
        model.addAttribute("carInfo", ci);
        return "admin/carInfo/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, CarInfo carInfo, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            CarInfo ci = carInfoService.findById(id);
            MyBeanUtils.copyProperties(carInfo, ci, new String[]{"id"});
            carInfoService.save(ci);
        }
        return "redirect:/admin/carInfo/list";
    }

    /*@AdminAuth(name="删除车辆信息", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Integer id) {
        try {
            carInfoService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }*/
}
