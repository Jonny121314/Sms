package com.zhoujianming.www.utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MapUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONConvert<T> {

	@SuppressWarnings("hiding")
	public <T> T convert(Class<T> mapperClass, String jsonString) {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.enableComplexMapKeySerialization();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(jsonString, TypeToken.of(mapperClass).getType());
	}

	@SuppressWarnings("hiding")
	public <T> T convert(Type type, String jsonString) {
		return new Gson().fromJson(jsonString, type);
	}

	/**
	 * 将page对象转换成json对象
	 * 
	 * @param page
	 * @return
	 */
	public static String pageObjectToCamelKeyJsonString(
			Page<Map<String, String>> page) {
		JSONObject json = new JSONObject();
		json.put("page", page.getPage());
		json.put("limit", page.getLimit());
		json.put("total", page.getTotal());
		json.put("totalPage", page.getTotalPage());

		List<Map<String, String>> items = page.getItems();
		JSONArray jsonArr = mapListToJSONArray(items);
		json.put("items", jsonArr);
		
		json.put("attrs", page.getAttrs());
		
		return JSON.toJSONString(json, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 将List<Map<String, Object>>对象转换成JSONArray对象 如果Map的key是大写下划线字符串，这转换成驼峰字符�?
	 * 
	 * @param list
	 * @return
	 */
	public static JSONArray mapListToJSONArray(List<Map<String, String>> list) {
		if (list != null) {
			JSONArray arr = new JSONArray();
			for (int i = 0, length = list.size(); i < length; i++) {
				Map<String, String> m = list.get(i);
				JSONObject obj = new JSONObject();
				for (Entry<String,String> entry : m.entrySet()) {
					obj.put(underlineToCamel(entry.getKey()), entry.getValue());
				}
				arr.add(obj);
			}
			return arr;
		}
		return null;
	}
	
	/**
	 * 将Map对象转换成JSON字符�?
	 * @param m
	 * @return
	 */
	public static String mapToCamelKeyJsonString(Map<String, String> m) {
		JSONObject json = new JSONObject();
		if (MapUtils.isNotEmpty(m)) {
			for (Entry<String,String> entry : m.entrySet()) {
				json.put(underlineToCamel(entry.getKey()), entry.getValue());
			}
		}
		return JSON.toJSONString(json, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 大写下划线字符串转驼峰字符串 <br>
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param.toLowerCase());
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1,
					sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {
		String txt = "assignStaffId";
		txt = underlineToCamel(txt);
		//System.out.println(txt);
	}
	
}
