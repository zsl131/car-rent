package com.ztw.legal.model;

/**
 * 车牌号码种类
 * Created by 马旭 on 2016/9/19.
 */
public enum HpzlInfo {
    大型汽车("01"),
    小型汽车("02"),
    两三轮摩托车("03"),
    轻便摩托车("08"),
    农用运输车("13"),
    挂车("15"),
    教练汽车("16"),
    教练摩托车("17");

    private String code;

    private HpzlInfo(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
