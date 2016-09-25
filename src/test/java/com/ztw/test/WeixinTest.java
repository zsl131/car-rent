package com.ztw.test;


import com.mashape.unirest.http.Unirest;
import com.ztw.weixin.iservice.IWeiXinMenuService;
import com.ztw.weixin.iservice.IWeixinService;
import com.ztw.weixin.model.WeiXin;
import com.ztw.weixin.util.RefreshAccessToken;
import com.ztw.weixin.util.WeixinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by admin on 2016/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("zxt")
public class WeixinTest {

    @Autowired
    private IWeixinService weixinService;

    @Autowired
    private IWeiXinMenuService weiXinMenuService;

    @Test
    public void add(){
        WeiXin weiXin = new WeiXin();
        weiXin.setAge(18);
        weiXin.setCreateDate(new Date());
        weiXin.setAttentionDate(new Date());
        weiXin.setBindingDate(new Date());
        weiXin.setCity("昭通");
        weiXin.setBindingStatus(1);
        weiXin.setHeadImg("59b1f1a2-bf57-4113-80d3-6e6227e42aab.jpg");
        weiXin.setNickName("ztt");
        weiXin.setOpenId("59b1f1a2-bf57-4113-80d3-6e6227e42aabsdads");
        weiXin.setPassword("59b1f1a2bf57-411380d3-6e6227e42aab");
        weiXin.setPhone("15925099125");
        weiXin.setPlace("云南省昭通是市昭阳区");
        weiXin.setSex(1);
        weiXin.setRealName("力士");
        weiXin.setUserName("root");
        weixinService.save(weiXin);
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
