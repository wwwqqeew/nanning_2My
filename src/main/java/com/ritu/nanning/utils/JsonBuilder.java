package com.ritu.nanning.utils;

/**
 * Json Builder
 * 
 * @author Joe
 * 
 */
public class JsonBuilder {

	public static String toJson(boolean success) {
		return "{\"success\":" + new Boolean(success).toString() + "}";
	}

	/**
	 * 静态方法toJson
	 * @param success 是否成功
	 * @param data 数据
	 * @return JSON格式数据
	 */
	public static String toJson(boolean success, Object data) {
		// 检测：
		// 是：
		if (data != null) {
			return toJson(success, data.toString());
		}else {
			return toJson(success, null);
		}
		
	}


	public static String toJson(boolean success, String jsonData) {
		return "{\"success\":" + new Boolean(success).toString() + ",\"data\":" + jsonData + "}";
	}
	
	public static String toJsonOne(boolean success, String jsonData) {
		return "{\"success\":" + new Boolean(success).toString() + ",\"data\":\"" + jsonData + "\"}";
	}

	/**
	 * 静态方法带页码默认
	 * @param success 成功
	 * @param data 数据
	 * @param page 页码
	 * @return ＪＳＯＮ格式数据
	 */
	public static String toJson(boolean success, Object data, int page) {
		return toJson(success, data.toString(), page);
	}

	public static String toJson(boolean success, String jsonData, int page) {
		return "{\"success\":" + new Boolean(success).toString() + ",\"data\":" + jsonData + ",\"page\":" + page + "}";
	}

	/**
	 * 静态方法 带页码 总页数
	 * @param success 成功
	 * @param data 数据
	 * @param page 页码
	 * @param totalPages 总页数
	 * @return JSON格式数据
	 */
	public static String toJson(boolean success, Object data, int page, int totalPages) {
		return toJson(success, data.toString(), page, totalPages);
	}

	public static String toJson(boolean success, String jsonData, int page, int totalPages) {
		return "{\"success\":" + new Boolean(success).toString() + ",\"data\":" + jsonData + ",\"page\":" + page
				+ ",\"totalPages\":" + totalPages + "}";
	}

}
