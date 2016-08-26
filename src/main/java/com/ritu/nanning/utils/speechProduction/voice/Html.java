package com.ritu.nanning.utils.speechProduction.voice;

import java.util.HashMap;

import com.ritu.nanning.utils.o;

public class Html {
	
	public static final String INPUT = "输入框";
	public static final String DATAINPUT = "日期";
	public static final String SELECT = "下拉";
	public static final String RADIO = "单选";
	public static final String MULTISELECT = "多选";
	public static final String TEXTFIELD = "文本域";
	static HashMap<String , CreateSQL> hs = new HashMap<String , CreateSQL>();
	
	public Html(){
		ready();
	}
	/**
	 * 参数准备
	 */
	private static void ready() {
		//输入框
		create(INPUT , " varchar(233) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ");
		//日期
		create(DATAINPUT , " datetime NULL DEFAULT NULL ");
		
		//create(SELECT , " varchar(233) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ");
	}
	
	/**
	 * 根据命令获取SQL语句
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static String getSql(String name) {
		String rt = "";
		CreateSQL createSQL = hs.get(name);
		if (createSQL != null) {
			rt += (createSQL.getSql() + " ");
		}else{
			o.o(name,"非类型字段，使用默认",INPUT);
			rt += (hs.get(INPUT).getSql() + " ");
		}
		return rt;
	}
	
	/**
	 * 命令以及SQL语句带入
	 * @param name
	 * @param sql
	 */
	private static void create(String name, String sql){
		CreateSQL createSQL = new CreateSQL();
		createSQL.setCommand(name);
		createSQL.setCommandEN(sql);
		createSQL.setSql(sql);
		hs.put(name, createSQL);
	}
	
}
