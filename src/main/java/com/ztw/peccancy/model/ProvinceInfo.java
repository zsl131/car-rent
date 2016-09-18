package com.ztw.peccancy.model;

import java.util.HashMap;
import java.util.Map;

/**
 * province information
 * @author 马旭
 */
public class ProvinceInfo {

	public static final Map<String, String> proInfo = new HashMap<String , String>(){
		private static final long serialVersionUID = 1L;
		{
            put("京", "北京");
            put("沪", "上海");
            put("津", "天津");
            put("渝", "重庆");
            put("冀", "河北");
            put("晋", "山西");
            put("蒙", "内蒙古");
            put("辽", "辽宁");
            put("吉", "吉林");
            put("黑", "黑龙江");
            put("苏", "江苏");
            put("浙", "浙江");
            put("皖", "安徽");
            put("闽", "福建");
            put("赣", "江西");
            put("鲁", "山东");
            put("豫", "河南");
            put("鄂", "湖北");
            put("湘", "湖南");
            put("粤", "广东");
            put("桂", "广西");
            put("琼", "海南");
            put("川", "四川");
            put("贵", "贵州");
            put("云", "云南");
            put("藏", "西藏");
            put("陕", "陕西");
            put("甘", "甘肃");
            put("宁", "宁夏");
            put("青", "青海");
            put("新", "新疆");
		}
	};
}
