package com.ritu.nanning.utils;

/**
 * 给JSON格式后的单个对象String添加属性
 * @author Cgy
 *
 */
public class JsonAddProperty {

	public static String StrAddProperty(String jsonF ,String propertyName ,String property) {
		String Str = jsonF.substring(0, jsonF.length()-1)+",\""+propertyName+"\":"+property+"}";
		return Str;
	}
}
