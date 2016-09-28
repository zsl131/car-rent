package com.ztw.web.controller.app;

/**
 * Created by zsl-pc on 2016/9/28.
 */
public class ResDto {

    public static final String OK = "ok";
    public static final Integer SUC = 1;
    public static final Integer ERROR = 0;

    private Integer resCode;

    private String resMsg;

    public ResDto(Integer resCode, String resMsg) {
        this.resCode = resCode; this.resMsg = resMsg;
    }

    public ResDto() {}

    public Integer getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
