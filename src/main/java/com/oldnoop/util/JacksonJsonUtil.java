package com.oldnoop.util;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonJsonUtil {

	/**
	 * 对象转json字符串
	 * @param o 对象
	 * @return
	 */
	public static String object2Json(Object o){
		ObjectMapper om = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			om.writeValue(sw, o);
			String json = sw.toString();
			return json;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * json字符串转对象
	 * @param json	json字符串
	 * @param clazz	对象类型(类名.class)
	 * @return
	 */
	public static <T> T json2Object(String json,Class<T> clazz){
		ObjectMapper om = new ObjectMapper();
		try {
			return om.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
