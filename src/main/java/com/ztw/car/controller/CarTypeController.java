package com.ztw.car.controller;

import com.ztw.car.model.Car;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/8.
 */
@RestController
@RequestMapping(value = "/admin/carType")
public class CarTypeController {

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(){
        //Car car = new Car();
        System.out.println("==============================================================================");
        return "/admin/add";
    }

}
