package com.ztw.legal.tools;

import com.ztw.legal.dto.DateTimeDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 违章查询的工期处理工具类
 * @author zsl-pc
 *
 */
public class DateTimeTools {

	public static DateTimeDto buildDateDto(String amount) {
		DateTimeDto dd = null;
		Date curDate = new Date();
		Integer month = Integer.parseInt(amount);
		/*if("1".equals(type)) { //一个月
			
		}*/
		dd = new DateTimeDto(getDateStr(getDate(curDate, Calendar.MONTH, 0-month)), getDateStr(curDate));
		return dd;
	}
	
	public static DateTimeDto buildDateDtoByDays(String days) {
		DateTimeDto dd = null;
		Date curDate = new Date();
		Integer dAmount = 0;
		try {
			dAmount = Integer.parseInt(days);
		} catch (Exception e) {
		}
		dd = new DateTimeDto(getDate(curDate, Calendar.DAY_OF_MONTH, 0-dAmount), curDate, "yyyy-MM-dd");
		return dd;
	}

	private static Date getDate(Date date, Integer type, Integer flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(type, flag);
		return cal.getTime();
	}
	
	private static String getDateStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static Long getDateLong(String timeStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(timeStr).getTime();
		} catch (ParseException e) {
			return new Date().getTime();
		}
	}
}
