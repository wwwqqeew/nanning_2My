package com.ritu.nanning.utils.speechProduction.voice;

import java.util.HashMap;

import com.ritu.nanning.utils.o;
import com.ritu.nanning.utils.speechProduction.jdbc.Demo;
import com.ritu.nanning.utils.speechProduction.spring.baiduTranslateMain;

public class voiceCreateMain {
	static HashMap<String , CreateSQL> hs = new HashMap<String , CreateSQL>();
	public static final String CREATE_CN = "创建";//创建
	public static final String PARAMETER_CN = "字段";//字段属性
	public static final String TYPE_CN = "类型";//字段属性
	public static boolean isCreate = false;
	static Html html = new Html();

	public static void main(String[] args) throws Exception {
		ready();
		String cr = "创建 人员" +
				"字段    名称    类型    输入框" +
				"字段    性格    类型    日期" +
				"字段    年龄    类型    输入框";
		createSQL(cr);
	}

	public static String createSQL(String cr) throws Exception {
		ready();
		String [] sc = cr.split(PARAMETER_CN);
		o.o(sc);
//		o.o(sc[0].split(CREATE_CN));
		String sql = "";
		String sqlOne = "";
		for (int i = 0; i < sc.length; i++) {
			sqlOne = "";
			if (i == 0) {
				//分解的第一个语句，创建表是否存在
				if (sc[i].indexOf(CREATE_CN) > -1) {
					sqlOne = createTable(sc[i]);
					if (sqlOne == null) {
						break;
					}else{
						sql += sqlOne;
					}
				}else{
					o.o("识别不到表名称,退出创建");
					break;
				}
			}else{
				sql += (", "+createParameter(sc[i]));
			}
		}
		sql += ") ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ROW_FORMAT=COMPACT;";
		o.o("创建SQL结束",sql);
		Demo.createTable(sql);
		return sql;
	}

	/**
	 * 创建属性语句
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private static String createParameter(String str) throws Exception {
		String rt = "";
		String[] strs = str.split(TYPE_CN);
		if (strs.length == 2) {
			rt += ("`"+getSql(strs[0])+"`");
			rt += html.getSql(strs[1]);
		}else{
			o.o(str,"创建字段语句参数出问题");
			return null;
		}
		
		return rt;
	}
	
	/**
	 * 创建表格语句
	 * @param str 命令+表格名称
	 * @return null（出错），String
	 * @throws Exception
	 */
	private static String createTable(String str) throws Exception{
		String rt = "";
		String[] strs = str.split(CREATE_CN);
		o.o(strs);
		if (strs.length == 2) {
			for (int i = 0; i < strs.length; i++) {
				if ("".equals(strs[i])) {
					rt += getSql(CREATE_CN);
				}else{
					rt += ("`"+getSql(strs[i])+"`");
				}
			}
		}else{
			o.o(str,"创建表格语句参数出问题");
			return null;
		}
		//添加ID，默认  int（11）
		rt += "(`id`  int(11) NOT NULL ,PRIMARY KEY (`id`) ";
		return rt;
	}
	
	/**
	 * 参数准备
	 */
	private static void ready() {
		if (!isCreate) {
			create(CREATE_CN,"CREATE TABLE");
			//create("表格","table");
			create(PARAMETER_CN,"varchar");
			o.o(hs);
			isCreate = true;
		}

	}

	/**
	 * 根据命令获取SQL语句
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	private static String getSql(String name) throws Exception {
		String rt = "";
		CreateSQL createSQL = hs.get(name);
		if (createSQL != null) {
			rt += (createSQL.getSql() + " ");
		}else{
			o.o(name,"非命令字段，即将百度翻译");
			rt += ( baiduTranslateMain.Translate(name).replaceAll(" ", ""));
		}
		return rt;
	}
	
	/**
	 * 命令以及SQL语句带入
	 * @param name
	 * @param sql
	 */
	public static void create(String name, String sql){
		CreateSQL createSQL = new CreateSQL();
		createSQL.setCommand(name);
		createSQL.setCommandEN(sql);
		createSQL.setSql(sql);
		hs.put(name, createSQL);
	}
}
