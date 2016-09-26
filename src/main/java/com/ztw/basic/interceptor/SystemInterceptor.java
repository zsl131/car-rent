package com.ztw.basic.interceptor;

import com.ztw.basic.iservice.IAppConfigService;
import com.ztw.basic.model.AppConfig;
import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.model.WeiXinConfig;
import com.ztw.weixin.tools.WeixinConstant;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IAppConfigService appConfigService;

    @Autowired
    private IWeiXinConfigService weiXinConfigService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //将系统配置文件存入Session中
        AppConfig appConfig = (AppConfig) session.getAttribute("appConfig");
        if(appConfigService==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            appConfigService = (IAppConfigService) factory.getBean("appConfigService");
        }
        if(appConfig==null) {
            appConfig = appConfigService.loadOne();
            session.setAttribute("appConfig", appConfig);
        }

        //在生成菜单时需要用到该配置文件，所以要在这里初始化
        WeiXinConfig weiXinConfig = (WeiXinConfig) session.getAttribute("weiXinConfig");
        if(weiXinConfig==null) {
            weiXinConfig = WeixinConstant.getInstance().getWeiXinConfig();
        }
        if(weiXinConfig==null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            weiXinConfigService = (IWeiXinConfigService) factory.getBean("weiXinConfigService");

            weiXinConfig = weiXinConfigService.findOne(1);
            session.setAttribute("weiXinConfig", weiXinConfig);
            WeixinConstant.getInstance().setWeiXinConfig(weiXinConfig);
        }
        return super.preHandle(request, response, handler);
    }
}