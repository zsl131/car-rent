package com.ztw.weixin.tools;

import com.ztw.weixin.model.WeiXinConfig;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.ztw.weixin.util.BeanFactoryContext.getAccessToken;

/**
 * Created by zsl-pc on 2016/9/26.
 */
public class WeixinConstant {

    private static WeixinConstant instance;
    private WeixinConstant() {}

    public static WeixinConstant getInstance() {
        if(instance==null) {instance = new WeixinConstant();}
        return instance;
    }

    private WeiXinConfig weiXinConfig;

    public WeiXinConfig getWeiXinConfig() {
        try {
            return this.weiXinConfig;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setWeiXinConfig(WeiXinConfig weiXinConfig) {
        this.weiXinConfig = weiXinConfig;
    }

    /**
     * 微信的相关交互URL
     * @author zsl
     *
     */
    public static class WeixinUrl {
        public static final String WEIXIN_ROOT_URL = "https://api.weixin.qq.com/cgi-bin/"; //微信的根Url
        public static final String GET_TOKEN = WEIXIN_ROOT_URL + "token"; //获取token的URL
        public static final String SEND_MESSAGE = WEIXIN_ROOT_URL + "message/custom/send"; //发送消息
        public static final String CREATE_MENU = WEIXIN_ROOT_URL + "menu/create"; //创建菜单
    }

    public static JSONObject getUserInfo(String openid) {
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("access_token", getAccessToken());
            params.put("openid", openid);
            params.put("lang", "zh_CN");

            String result = InternetTools.doGet("https://api.weixin.qq.com/cgi-bin/user/info", params);

            JSONObject jsonObj = new JSONObject(result);
            return jsonObj;
        } catch (Exception e) {
            String result = InternetTools.doGet("https://api.weixin.qq.com/sns/userinfo", params);
            JSONObject jsonObj = new JSONObject(result);
            return jsonObj;
        }
    }

    public static synchronized String getUserOpenId(String code) {
        try {
            if(code!=null && !"".equals(code.trim())) {
//			https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("appid", getInstance().getWeiXinConfig().getAppID());
                params.put("secret", getInstance().getWeiXinConfig().getAppsecret());
                params.put("code", code);
                params.put("grant_type", "authorization_code");
                String result = InternetTools.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
//				System.out.println(code+"获取openid="+result);
                if(result==null) { //如果由于网络等原因，result为空时，再获取一次
                    result = InternetTools.doGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
                }
                String res = JsonTools.getJsonParam(result, "openid");
                if(res==null || "".equals(res)) {
                    System.out.println("===未获取openid==="+result+"===code:"+code);
                }
                return res;
            } return "";
        } catch (Exception e) {
            return "";
        }
    }
}
