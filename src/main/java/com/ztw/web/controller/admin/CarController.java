package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.car.iservice.ICarService;
import com.ztw.car.model.Car;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 2016/9/13.
 */
@Controller
@RequestMapping(value ="/admin/car")
@AdminAuth(name = "汽车管理",psn = "系统管理",orderNum = 1,porderNum = 2,pentity = 0)
public class CarController {

    @Autowired
    private ICarService carService;

    @RequestMapping(value = "add",method = RequestMethod.GET)
    @AdminAuth(name = "汽车添加",orderNum = 1)
    @Token(flag = Token.READY)
    public String add(Model model){
        model.addAttribute("car",new Car());
        return "/admin/car/add";
    }

   /* @RequestMapping(value = "add",method = RequestMethod.POST)
    @Token(flag = Token.CHECK)
    public String add(Model model,Car car, HttpServletRequest request){
        if(TokenTools.isNoRepeat(request)){
            //Car c= new Car();
        }
        return "";
    }*/
}
