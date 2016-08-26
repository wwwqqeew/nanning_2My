package com.ritu.nanning.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateParser {

	private static Logger logger = LoggerFactory.getLogger(DateParser.class);

	public static String FORMAT1 = "yy-MM-dd HH:mm:ss";
	public static String FORMAT2 = "yy-MM-dd";
	public static String FORMAT3 = "yyMMdd";
	public static String FORMAT4 = "yyyyMM";

	SimpleDateFormat dateFormat;

	public DateParser() {
		dateFormat = new SimpleDateFormat(FORMAT2);
	}

	public DateParser(String form) {
		dateFormat = new SimpleDateFormat(form);
	}

	public Date formString(String date) {
		try {
			return new Date(dateFormat.parse(date).getTime());
		} catch (ParseException e) {
			logger.warn("parser date error : " + date);
		}
		return null;
	}

	public static Date getLongBeforeDate() {
		return new Date(System.currentTimeMillis() - 100000000000000L);
	}

	public static Date getFarDate() {
		return new Date(System.currentTimeMillis() + 100000000000000L);
	}

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public String toString(Date date) {
		return dateFormat.format(date);
	};
}
