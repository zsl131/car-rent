package com.ztw.legal.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDto {

	private String starttime;
	private String endtime;
	
	public DateTimeDto(String starttime, String endtime) {
		this.starttime = starttime + " 00:00:00";
		this.endtime = endtime + " 00:00:00";
	}
	
	public DateTimeDto(Date startDate, Date endDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		this.starttime = sdf.format(startDate);
		this.endtime = sdf.format(endDate);
	}
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@Override
	public String toString() {
		return "starttime:"+starttime+",endtime:"+endtime;
	}
}
