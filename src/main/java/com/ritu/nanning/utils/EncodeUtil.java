package com.ritu.nanning.utils;

import java.io.UnsupportedEncodingException;
 

public class EncodeUtil {
	/**
	 * 通过get方式获取的带中文的参数转换
	 * @param str
	 * @return
	 */
	public static String encodeToUtf8(String str){
		String encodeToUtf8 = null;
		try {
			if (str != null) {
				encodeToUtf8 = new String(((String) str).getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encodeToUtf8;
	}
}