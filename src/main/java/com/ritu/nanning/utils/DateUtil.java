package com.ritu.nanning.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("2014-11-71".length());
//		System.out.println(":2014-11-7 11:31:15".indexOf(":"));
//		System.out.println("2014-9-30 11:31:15");
//		System.out.println(StringToDate(new Date().toString()).getTime());
//		o.o(new Date().getMinutes());
//		System.out.println("1415688625495:"+new Date(1415688625495l));
//		/vehicles/1997794990?sta=1429141550000&end=1429147850000
//		o.o(StringToDate("2015-04-16 07:45:50").getTime());
//		o.o(StringToDate("2015-04-16 09:30:50").getTime());
//		o.o(Math.abs((long) "32042229561261df".hashCode()));
	}
	
	/**
	 * String ת Date
	 * @param DateStr
	 * @return ��������
	 * ���ݡ������ж����ʲô����
	 * @throws ParseException 
	 */
	public static Date StringToDate(String DateStr) throws ParseException{
		    SimpleDateFormat sdf = null;
		    if (DateStr.length() > 10 && DateStr.indexOf(":") >=0) {
		    	sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			} else {
				sdf = new SimpleDateFormat("yyyy-MM-dd");  
			}
		    return sdf.parse(DateStr);
//		    try {
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return null;
//			}  
	}
	
	/**
	 * Date ת"yyyy-MM-dd HH:mm:ss"���͵�String
	 * @param data
	 * @return
	 */
	public static String getDateString(Date data){
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if (data == null) {
			return sdf.format(new Date());
		} else {
			return sdf.format(data);
		}
	}
}
