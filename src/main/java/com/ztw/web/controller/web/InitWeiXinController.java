package com.ztw.web.controller.web;

import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.model.WeiXinConfig;
import com.ztw.weixin.util.SecurityKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by admin on 2016/9/21.
 */
@Controller
@RequestMapping(value = "weixin")
public class InitWeiXinController {

    @Autowired
    private IWeiXinConfigService weiXinConfigService;

    /**
     * 微信服务器验证
     */
    @RequestMapping(value = "init",method = RequestMethod.GET)
    public void init(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) throws IOException {
        WeiXinConfig wc = weiXinConfigService.findOne(1);
        String[] arr = {wc.getToken(),timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        for(String a:arr) {
            sb.append(a);
        }
        String sha1Msg = SecurityKit.sha1(sb.toString());
        if(signature.equals(sha1Msg)) {
            response.getWriter().println(echostr);
        }
    }
}
