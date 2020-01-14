package com.zhoujianming.www.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class StrTrim {

	public static Map<String, Object> mapTrim(Map<String, Object> map) {
		if (map == null) {
			return map;
		}
		HashMap<String, String> tempMap = Maps.newHashMap();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			Object obj = entry.getValue();
			if (obj instanceof String) {
				String s = (String) obj;
				String trim = s.trim();
				tempMap.put(key, trim);
			}
		}

		for (String key : tempMap.keySet()) {
			map.put(key, tempMap.get(key));
		}
		
		return map;

	}
	
	
	/**
	 * 将字符串的编码格式转换为utf-8
	 * 
	 * @param str
	 * @return Name = new
	 * String(Name.getBytes("ISO-8859-1"), "utf-8");
	 */
	public static String toUTF8(String str) {
		if (isEmpty(str)) {
			return "";
		}
		try {
			if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
				str = new String(str.getBytes("GB2312"), "utf-8");
				return str;
			}
		} catch (Exception exception) {
		}
		try {
			if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				str = new String(str.getBytes("ISO-8859-1"), "utf-8");
				return str;
			}
		} catch (Exception exception1) {
		}
		try {
			if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
				str = new String(str.getBytes("GBK"), "utf-8");
				return str;
			}
		} catch (Exception exception3) {
		}
		return str;
	}
	

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		// 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
		if (str != null && !str.trim().isEmpty()) {
			return false;// 不为�?
		}
		return true;// 为空
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> params = Maps.newHashMap();
		params.put("keyword", " �?  �?  �?  ");
		params.put("shopCode", "code 0001");
		params.put("shopName", " name0001");
		params.put("chargePerson", "1");
		Map<String, Object> mapTrim = StrTrim.mapTrim(params);
		//System.out.println(mapTrim);
	}

}
