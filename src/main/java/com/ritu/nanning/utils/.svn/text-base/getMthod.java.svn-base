package com.ritu.nanning.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getMthod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 根据属性名称获取值
	 * @param fieldName 名称
	 * @param o 类
	 * @return
	 */
 	public static Object getFieldValueByName(String fieldName, Object o) {  
       try {    
    	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
           String getter = "get" + firstLetter + fieldName.substring(1);    
           Method method = o.getClass().getMethod(getter, new Class[] {}); 
           Type returnType = method.getGenericReturnType();// 返回类型
           Object value ="";
           if (new Date().getClass() == returnType){
        	   value = method.invoke(o, new Object[] {});
        	   value = df.format(value);
           }else{
        	   value = method.invoke(o, new Object[] {})+"";
           }
           return value;    
       } catch (Exception e) {    
           e.getMessage() ;    
           return "";    
       }
       
   }

}
