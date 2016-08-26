package com.ritu.nanning.utils;

import java.util.Date;

/**
 * 
 * @author Joe
 * 
 */
public class DateUtils {

	public static long getDayLong(long day) {
		// 1000 * 60 * 60 * 24 * day;
		return 86400000l * day;
	}

	public static Date getBeforeDate(long day) {
		return new Date(System.currentTimeMillis() - getDayLong(day));
	}

	public static Date getBeforeDate(Date date, long day) {
		return new Date(date.getTime() - getDayLong(day));
	}

	public static Date getAfterDate(long day) {
		return new Date(System.currentTimeMillis() + getDayLong(day));
	}

	public static Date getAfterDate(Date date, long day) {
		return new Date(date.getTime() + getDayLong(day));
	}

}
