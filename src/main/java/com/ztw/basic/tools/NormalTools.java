package com.ztw.basic.tools;

/**
 * Created by zsl-pc on 2016/9/14.
 */
public class NormalTools {

    public static String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
