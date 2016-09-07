package com.ztw.web.controller.basic;

import javax.servlet.http.HttpServletRequest;

import com.ztw.basic.auth.annotations.Token;
import com.ztw.basic.auth.iservice.IUserService;
import com.ztw.basic.auth.model.User;
import com.ztw.basic.auth.service.UserServiceImpl;
import com.ztw.basic.auth.tools.TokenTools;
import com.ztw.basic.iservice.IAppConfigService;
import com.ztw.basic.model.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统初始化Controller
 * @author zslin.com 20160520
 *
 */
@Controller
@RequestMapping
public class InitController {

    @Autowired
    private IAppConfigService appConfigService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /** 初始化GET */
    @RequestMapping(value="init", method=RequestMethod.GET)
    @Token(flag= Token.READY)
    public String init(Model model, HttpServletRequest request) {
        AppConfig appConfig = appConfigService.loadOne();
        if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
            //表示可以初始化
            User user = new User();
            user.setStatus(1);
            model.addAttribute("user", user);
            model.addAttribute("initFlag", true);
        } else {
            //表示不可以初始化
            model.addAttribute("initFlag", false);
        }
        return "init";
    }

    /** 初始化POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="init", method=RequestMethod.POST)
    public String init(Model model, User user, HttpServletRequest request) {
        if(TokenTools.isNoRepeat(request)) {
            AppConfig appConfig = appConfigService.loadOne();
            if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
                userServiceImpl.initBaseUser(user);
            } else {
                //表示不可以初始化，不可以初始化，则直接返回
            }
        }
        return "redirect:/init";
    }

    /** 系统首页 */
    @RequestMapping(value={"index"}, method=RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        AppConfig appConfig = appConfigService.loadOne();
        if(appConfig==null || appConfig.getInitFlag()==null || "0".equals(appConfig.getInitFlag())) {
            //表示可以初始化
            return "redirect:/init";
        } else {
            //表示不可以初始化，不可以初始化，则直接返回
            String indexPage = appConfig.getIndexPage();
            if(indexPage==null) {indexPage = "/admin";}
            return "redirect:"+indexPage;
        }
    }
}