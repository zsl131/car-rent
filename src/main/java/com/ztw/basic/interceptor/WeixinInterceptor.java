package com.ztw.basic.interceptor;

import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.model.WeiXinConfig;
import com.ztw.weixin.tools.WeixinConstant;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zsl-pc on 2016/9/26.
 */
public class WeixinInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IWeiXinConfigService weiXinConfigService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
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
