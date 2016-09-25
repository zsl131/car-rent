package com.ztw.weixin.util;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.iservice.IWeiXinMenuService;
import com.ztw.weixin.model.WeiXin;
import com.ztw.weixin.model.WeiXinConfig;
import com.ztw.weixin.model.WeiXinMenu;
import com.ztw.weixin.vo.Button;
import com.ztw.weixin.vo.Sub_button;
import com.ztw.weixin.vo.WeiXinMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/19.
 */
public class WeixinUtil {

    /**
     *获取access_token
     *http请求方式: GET
     *https://api.weixin.qq.com/cgi-bin/token?APPID&secret=APPSECRET
     */
    public static String getWeiXinAccessToken(IWeiXinConfigService weiXinConfigService){
        WeiXinConfig weiXinConfig = weiXinConfigService.findOne(1);
        String token ="";
        if(weiXinConfig!=null){
            try {
                token = Unirest.get("https://api.weixin.qq.com/cgi-bin/token")
                        .queryString("grant_type","client_credential")
                        .queryString("appid",weiXinConfig.getAppID())
                        .queryString("secret",weiXinConfig.getAppsecret()).asString().getBody();
            } catch (UnirestException e) {
                token = e.getMessage();
            }
        }
        return token;
    }




    public static String createWeiXinMenu(IWeiXinMenuService weiXinMenuService){
        List<WeiXinMenu> menuList = weiXinMenuService.findAllPidIsNull();
        List<Button> wxdButton = new ArrayList<Button>();
        WeiXinMenuDto wxd = new WeiXinMenuDto();
        for(WeiXinMenu wm : menuList){
            Button b = new Button();
            List<Sub_button> sub_buttons =new ArrayList<Sub_button>();
            b.setType(wm.getType());
            b.setUrl(wm.getUrl());
            b.setName(wm.getName());
            List<WeiXinMenu> sonList = weiXinMenuService.findSonMenuByPid(wm.getId());
            System.out.println("++++++++++++++++++++++++ : "+wm.getPid()+"--------------------:"+sonList.size());
            if(sonList.size()>0){
                for(WeiXinMenu swm : sonList){
                    Sub_button sb = new Sub_button();
                    sb.setName(swm.getName());
                    sb.setType(swm.getType());
                    sb.setUrl(swm.getUrl());
                    sub_buttons.add(sb);
                }
                b.setSub_button(sub_buttons);
            }
            wxdButton.add(b);
        }
        wxd.setButton(wxdButton);
        String jsonObj = JSON.toJSONString(wxd);
        return  jsonObj;
    }
    



}
