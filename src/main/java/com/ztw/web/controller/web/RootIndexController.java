package com.ztw.web.controller.web;

import com.ztw.basic.iservice.IAppConfigService;
import com.ztw.basic.model.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsl-pc on 2016/9/25.
 */
@Controller
public class RootIndexController {

    @Autowired
    private IAppConfigService appConfigService;

    /** 网站首页 */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        AppConfig ac = (AppConfig) request.getSession().getAttribute("appConfig");
        if(ac==null) {
            ac = appConfigService.loadOne();
        }
        if(ac!=null && ac.getIndexPage()!=null) {
            return "redirect:" + ac.getIndexPage();
        } else {
            return "redirect:/";
        }
    }
}
