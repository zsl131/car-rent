package com.ztw.weixin.util;

import com.alibaba.fastjson.JSON;
import com.ztw.weixin.iservice.IWeiXinConfigService;
import com.ztw.weixin.vo.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2016/9/22.
 */
public class RefreshAccessToken {

    public RefreshAccessToken(int second,IWeiXinConfigService weiXinConfigService) {
        Timer timer =new Timer();
        timer.schedule(new RefreshAccessTokenTask(weiXinConfigService),0,second*1000);
    }

    private class RefreshAccessTokenTask extends TimerTask{

        private IWeiXinConfigService weiXinConfigService;

        public RefreshAccessTokenTask(IWeiXinConfigService weiXinConfigService){
            this.weiXinConfigService = weiXinConfigService;
        };


        @Override
        public void run() {
            String body = WeixinUtil.getWeiXinAccessToken(weiXinConfigService);
            AccessToken at = JSON.parseObject(body,AccessToken.class);
            BeanFactoryContext.setAccessToken(at.getAccess_token());
        }
    }

}
