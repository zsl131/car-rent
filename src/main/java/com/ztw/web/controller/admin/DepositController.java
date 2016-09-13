package com.ztw.web.controller.admin;

import com.ztw.basic.auth.annotations.AdminAuth;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 保证金管理controller
 * Created by 马旭 on 2016/9/8.
 */
@Controller
@RequestMapping(value = "admin/deposit")
@AdminAuth(name = "保证金", orderNum = 10, psn = "系统管理", pentity=0, porderNum=20)
public class DepositController {

    @AdminAuth(name="保证金管理", orderNum=1, icon="glyphicon glyphicon-usd")
    @RequestMapping(value="index", method= RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "admin/deposit/index";
    }

}
