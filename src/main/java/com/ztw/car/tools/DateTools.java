package com.ztw.car.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * Created by zsl-pc on 2016/9/21.
 */
public class DateTools {

    public static Date plusDay(Integer days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    /**
     *
     * @param days
     * @param pattern
     * @return
     */
    public static String plusDay(Integer days, String pattern) {
        Date date = plusDay(days);
        if(pattern==null || "".equalsIgnoreCase(pattern)) {pattern = "yyyy-MM-dd";}
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Long plusDayByLong(Integer days) {
        Date date = plusDay(days);
        return date.getTime();
    }
}
