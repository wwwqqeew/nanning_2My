package com.ritu.nanning.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class PortMap {
	private static PropertiesLoader prop = init();
	private static PropertiesLoader init() {
		String canUseUrl = null;
//		return new PropertiesLoader("src/main/resources/port.properties");//jetty
		try {
			canUseUrl = getURL() + "port.properties";
			System.out.println("进入第一次创建："+canUseUrl);
			new FileInputStream(canUseUrl);
		} catch (Exception e) {
			canUseUrl = "src/main/resources/port.properties";
			try {
				System.out.println("进入第二次创建："+canUseUrl);
				new FileInputStream(canUseUrl);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
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
			e.printStackTrace();
		}
		return path;
	}

	public static String get(String key) {
		return prop.getString(key);
	}

	public static void put(String key, String value) {
		prop.put(key, value);
	}
	
}
