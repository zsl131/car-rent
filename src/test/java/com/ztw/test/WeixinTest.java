package com.ztw.test;


import com.mashape.unirest.http.Unirest;
import com.ztw.weixin.iservice.IWeiXinMenuService;
import com.ztw.weixin.iservice.IWeixinUserService;
import com.ztw.weixin.model.WeixinUser;
import com.ztw.weixin.util.WeixinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by admin on 2016/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("zxt")
public class WeixinTest {

    @Autowired
    private IWeixinUserService weixinService;

    @Autowired
    private IWeiXinMenuService weiXinMenuService;

    @Test
    public void add(){
        WeixinUser weixinUser = new WeixinUser();
        weixinUser.setAge(18);
        weixinUser.setCreateDate(new Date());
        weixinUser.setAttentionDate(new Date());
        weixinUser.setBindingDate(new Date());
        weixinUser.setCity("昭通");
        weixinUser.setBindingStatus(1);
        weixinUser.setHeadImg("59b1f1a2-bf57-4113-80d3-6e6227e42aab.jpg");
        weixinUser.setNickName("ztt");
        weixinUser.setOpenId("59b1f1a2-bf57-4113-80d3-6e6227e42aabsdads");
        weixinUser.setPassword("59b1f1a2bf57-411380d3-6e6227e42aab");
        weixinUser.setPhone("15925099125");
        weixinUser.setPlace("云南省昭通是市昭阳区");
        weixinUser.setSex(1);
        weixinUser.setRealName("力士");
        weixinUser.setUserName("root");
        weixinService.save(weixinUser);
    }

    @Test
    public void weixin(){
        String str1 = "ejNnokjePyfCkBZp86MyjGXDgP5R0-PUFD7g4C8fzQqfmKL0jWZ36LXuVZo65pkT-zQM7zSVGUZP4h1HttHHkTAs2bE4DyOXYeNmaJNfSNp3R42rs1svqs-kC5Xd9ho6SRTcAIAONP";
        String str2 = "ejNnokjePyfCkBZp86MyjGXDgP5R0-PUFD7g4C8fzQreQYRizWbjm5h_XDKTvUMgloTsw2QAWrz77qtokDCzmr8USz6ORdQ-xpye-6NTa1H6fAgFIKE0_Ypkk7-5FAfpAKScAIAVFN";
        System.out.println(str1.equals(str2));
    }


    @Test
    public void httpGet(){
        System.out.println("============================================================");
        try {
            String body = Unirest.get("https://www.weather.com.cn").asString().getBody();
            System.out.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void httpGetStr(){
        System.out.println("======access_token_url====================================================");
        //String re = WeixinUtil.getWeiXinAccessToken();
        //System.out.println(re);
    }

    @Test
    public void getWeiXinMenu(){
        String json = WeixinUtil.createWeiXinMenu(weiXinMenuService);
        System.out.println("=============================================");
        System.out.println(json);
        System.out.println("==============================================");
    }




}
