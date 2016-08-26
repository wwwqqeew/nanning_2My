package com.ritu.nanning.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SystemCfgMap {

	private static PropertiesLoader prop = init();

	private static PropertiesLoader init() {
		String canUseUrl = null;
//		System.out.println("RUL："+getURL()+"setting.properties");
		try {
			canUseUrl = getURL() + "setting.properties";
			System.out.println("进入第一次创建："+canUseUrl);
			new FileInputStream(canUseUrl);
		} catch (Exception e) {
//			SystemCfgMap.class.get
//			canUseUrl = "src/main/resources/setting.properties";
			canUseUrl = getURL() + "setting.properties";
//			canUseUrl = getURL() + "setting.properties";
			try {
				System.out.println("进入第二次创建："+canUseUrl);
				new FileInputStream(canUseUrl);
			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
			}
		}
		return new PropertiesLoader(canUseUrl);//jetty
	}
	
	public static String getURL(){
		String path = null;
		try {
			 path = java.net.URLDecoder.decode(SystemCfgMap.class.getResource("").getPath(),"utf-8");
//			 System.out.println("0000000000000000000000000000000:"+path);
			 int index = path.indexOf("classes");
			if(index!=-1){
				path = path.substring(0, index)+"classes/";
			}
//			 path = path.substring(1,path.length());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return path;
	}

	
	public static String get(String key) {
		return prop.getString(key);
	}

	public static void put(String key, String value) {
		prop.put(key, value);
	}

	public static class Setting {

		private String name;
		private String type;
		private String search;
		private String title;
		private String url;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getSearch() {
			return search;
		}

		public void setSearch(String search) {
			this.search = search;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}
}
