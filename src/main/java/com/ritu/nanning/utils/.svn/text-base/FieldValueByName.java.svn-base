package com.ritu.nanning.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class FieldValueByName {

	public String name;
	public int age;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FieldValueByName fb = new FieldValueByName();
		fb.setAge(10);
		fb.setName("CGY");
		HashMap<String,Method> hmp = new HashMap<String,Method>();
		sy.o(getFieldValueByName("name", fb ,hmp));
		try {
			o.o(((Method) hmp.get("name")));
//			sy.o( ((Method) hmp.get("name")).invoke(fb, new Object[] {}))	;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//sy.o( ""+((Method) hmp.get("name")).invoke(fb, new Object[] {}));
	}

	/**
	 * 根据属性名称和类获取属性值(导出Excel用)
	 * 遇到NULL，设为 “”
	 * @param fieldName 属性名称
	 * @param o 类
	 * @return 返回
	 */
	public static int getExcelWidthByName(String fieldName, Object o) {
		try {
			String rt = "4500";
			Method method = null;
			//检查HashMap中是否已经有了参数
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + fieldName.substring(1)+"width";
				method = o.getClass().getMethod(getter, new Class[] {});
			//日期类型的转换与其他类型的判断
				rt =  method.invoke(o, new Object[] {}) +"";
				rt = ("null".equals(rt) ? "4500" : rt);
			return Integer.valueOf(rt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("错误："+e);
			return 30 * 150;
		}
	}

	/**
	 * 根据属性名称和类获取属性值，且把方法放到HashMap中(导出Excel用)
	 * 遇到NULL，设为 “”
	 * @param fieldName 属性名称
	 * @param o 类
	 * @param hmp 存放方法的HashMap
	 * @return 返回
	 */
	public static Object getFieldValueByName(String fieldName, Object o,HashMap hmp) {
		try {
			String rt = "";
			Method method = null;
			//检查HashMap中是否已经有了参数
			if (hmp.get(fieldName) == null) {
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getter = "get" + firstLetter + fieldName.substring(1);
				method = o.getClass().getMethod(getter, new Class[] {});
				hmp.put(fieldName, method);
			}else{
				method = (Method) hmp.get(fieldName);
			}
			//日期类型的转换与其他类型的判断
			if (new Date().getClass() ==  method.getGenericReturnType()) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				rt = df.format(method.invoke(o, new Object[] {}));
			} else {
				rt =  method.invoke(o, new Object[] {}) +"";
				rt = ("null".equals(rt) ? "": rt);
			}
			return (rt == null ? "": rt );
		} catch (Exception e) {
			e.getMessage();
			return "";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
