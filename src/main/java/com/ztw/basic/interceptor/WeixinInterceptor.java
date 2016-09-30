package com.ztw.basic.interceptor;

import com.ztw.weixin.tools.WeixinConstant;
import com.ztw.weixin.tools.WeixinUserTools;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * Created by zsl-pc on 2016/9/26.
 */
public class WeixinInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String openid = WeixinUserTools.getOpenid(request); //获取Openid
        if(openid==null || "".equals(openid) || "null".equals(openid)) { //如果session中没有openid，则获取并存入session
            String code = request.getParameter("code");
            String state = request.getParameter("state");  //正确值：hztOauth2，参考授权跳转地址
            if(code!=null && !"".equals(code) && state!=null && state.equals("hztOauth2")) { //如果code存在
                openid = WeixinConstant.getUserOpenId(code); //获取openid

                if(openid!=null && !"".equals(openid.trim()) && !"null".equalsIgnoreCase(openid)) {
                    session.setAttribute(WeixinUserTools.SESSION_OPENID, openid); //将openid存入session中
                } else {
                    System.out.println("=====获取openid异常：");
                }
            } else { //如果code不存在，则重新获取
                String fullPath = URLEncoder.encode(fullPath(request), "UTF-8");
                String appId = WeixinConstant.getInstance().getWeiXinConfig().getAppID();
                String authPath = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=hztOauth2#wechat_redirect";
                authPath = authPath.replace("APPID", appId).replace("REDIRECT_URI", fullPath);
                response.sendRedirect(authPath);
            }
        }
        return super.preHandle(request, response, handler);
    }

    /** 获取全路径 */
    private String fullPath(HttpServletRequest request) {
        String param = request.getQueryString()==null?"":request.getQueryString();
        if(param==null || "".equals(param) || param.indexOf("openid=")<0) {
            String openid = (String) request.getSession().getAttribute("openid");
            if(openid!=null && !"".equals(openid)) {
                param = "openid="+openid+"&"+param;
            }
        }
        String res = request.getRequestURL().toString()+"?" + param;
        return res;
    }
}
