package com.ztw.test;

import com.alibaba.fastjson.JSON;
import com.ztw.weixin.vo.Button;
import com.ztw.weixin.vo.Sub_button;
import com.ztw.weixin.vo.WeiXinMenuDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/25.
 */
public class JsonTest {
    @Test
    public void jsonTest(){
        WeiXinMenuDto weiXinMenuDto = new WeiXinMenuDto();
        List<Sub_button> sub_button = new ArrayList<Sub_button>();
        List<Button> buttons = new ArrayList<Button>();
        Sub_button sub_buttonObj = new Sub_button();
        sub_buttonObj.setUrl("http://www.qq.com");
        sub_buttonObj.setType("view");
        sub_buttonObj.setName("qq");
        Button button = new Button();
        button.setName("百度");
        button.setType("view");
        button.setUrl("https://www.baidu.com");
        buttons.add(button);
        sub_button.add(sub_buttonObj);
        weiXinMenuDto.setButton(buttons);
        String jsonObj = JSON.toJSONString(weiXinMenuDto);
        System.out.println(jsonObj);
    }

    @Test
    public void createWeiXinMenu(){

    }
}
