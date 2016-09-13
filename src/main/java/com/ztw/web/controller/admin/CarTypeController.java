package com.ztw.web.controller.admin;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;
import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.tools.PageableTools;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.iservice.ICarTypeService;
import com.ztw.car.model.CarType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by admin on 2016/9/8.
 */
@Controller
@RequestMapping(value = "/admin/carType")
@AdminAuth(name = "汽车种类管理",psn = "权限管理",orderNum = 1,porderNum = 2,pentity = 0)
public class CarTypeController {

    @Autowired
    private ICarTypeService carTypeService;

    @Token(flag = Token.READY)
    @RequestMapping(value = "add",method = RequestMethod.GET)
    @AdminAuth(name = "汽车种类添加",orderNum = 2,icon = "fa fa-plus")
    public String add(Model model){
        model.addAttribute("carType",new CarType());
        return "/admin/carType/add";
    }

    @Token(flag = Token.CHECK)
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String add(Model model, CarType carType, HttpServletRequest request){
        if(TokenTools.isNoRepeat(request)){
            CarType c = new CarType();
            c.setCreateDate(new Date());
            c.setTypeName(carType.getTypeName());
            carTypeService.save(c);
        }
        return "redirect:/admin/carType/list";
    }

    @AdminAuth(name = "汽车种类列表",orderNum = 1, icon = "fa fa-list")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model,Integer page){
        Page<CarType> datas = carTypeService.findAll(PageableTools.basicPage(page));
        model.addAttribute("datas",datas);
        return "/admin/carType/list";
    }

    @Token(flag = Token.READY)
    @AdminAuth(name = "汽车种类修改",type = "2",orderNum = 3)
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String update(Model model,@PathVariable Integer id){
        CarType carType = carTypeService.findOne(id);
        model.addAttribute("carType",carType);
        return "/admin/carType/update";
    }



}

