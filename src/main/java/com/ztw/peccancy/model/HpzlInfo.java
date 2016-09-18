package com.ztw.peccancy.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 号牌种类 information
 * @author 马旭
 */
public class HpzlInfo {
    public static final Map<String, String> hpzlInfo = new HashMap<String , String>(){
        private static final long serialVersionUID = 1L;
        {
            put("大型汽车","01");
            put("小型汽车","02");
            put("两、三轮摩托车","03");
            put("轻便摩托车","08");
            put("农用运输车","13");
            put("挂车","15");
            put("教练汽车","16");
            put("教练摩托车","17");
        }
    };
}
