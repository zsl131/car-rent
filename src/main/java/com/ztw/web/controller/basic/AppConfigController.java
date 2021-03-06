package com.ztw.web.controller.basic;

import com.ztw.basic.auth.annotations.AdminAuth;
import com.ztw.basic.iservice.IAppConfigService;
import com.ztw.basic.model.AppConfig;
import com.ztw.basic.service.AppConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统配置
 * @author zslin.com 20160519
 *
 */
@Controller
@RequestMapping(value="admin/appConfig")
@AdminAuth(name="系统配置", orderNum=10, psn="系统管理", pentity=0, porderNum=20)
public class AppConfigController {

    @Autowired
    private IAppConfigService appConfigService;

    @Autowired
    private AppConfigServiceImpl appConfigServiceImpl;

    @AdminAuth(name="系统配置", orderNum=1, icon="glyphicon glyphicon-cog")
    @RequestMapping(value="index", method= RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        AppConfig config = appConfigService.loadOne();
        if(config.getAbout()!=null) {
            config.setAbout(config.getAbout().replaceAll("</p><p>", "\\\n").replaceAll("<p>", "").replaceAll("</p>", ""));
        }
        model.addAttribute("appConfig", config);
        return "admin/appConfig/index";
    }

    @RequestMapping(value="index", method=RequestMethod.POST)
    public String index(Model model, AppConfig appConfig, HttpServletRequest request) {
        String about = appConfig.getAbout();
        about = about==null?"":about;
        StringBuffer sb = new StringBuffer("<p>");
        sb.append(about.replaceAll("\\n", "</p><p>")).append("</p>");

        appConfig.setAbout(sb.toString());
        appConfigServiceImpl.addOrUpdate(appConfig);
        request.getSession().setAttribute("appConfig", appConfig); //修改后需要修改一次Session中的值
        return "redirect:/admin/appConfig/index";
    }
}