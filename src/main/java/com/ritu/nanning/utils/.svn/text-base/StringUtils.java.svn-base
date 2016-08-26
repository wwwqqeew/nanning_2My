package com.ritu.nanning.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class StringUtils {

	public static final String URL_REG_EXPRESSION = "^(https?://)?([a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+)+(/*[A-Za-z0-9/\\-_&:?\\+=//.%]*)*";
	public static final String EMAIL_REG_EXPRESSION = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

	public static String[] separate(String s) {
		return s.split("[ |\\n]");
	}

	public static String[] separateParams(String s) {
		return s.split("&");
	}

	public static String[] separateParam(String s) {
		return s.split("=");
	}

	public static String[] separatePathAndParam(String s) {
		return s.split("\\?");
	}

	public static String[] separatePath(String s) {
		s = s.trim();
		s = s.substring(s.indexOf("/") == 0 ? 1 : 0);
		s = s.substring(0, s.lastIndexOf("/") == s.length() - 1 ? s.length() - 1 : s.length());
		return s.split("[/|\\\\]");
	}

	public static boolean isUrl(String s) {
		if (s == null) {
			return false;
		}
		return Pattern.matches(URL_REG_EXPRESSION, s);
	}

	public static boolean isEmail(String s) {
		if (s == null) {
			return true;
		}
		return Pattern.matches(EMAIL_REG_EXPRESSION, s);
	}

	public static String limitStr(String mStr, int length) {
		mStr = buildStr(mStr, "");
		if (mStr.length() > length) {
			mStr = mStr.substring(0, length) + "...";
		}
		return mStr;
	}

	public static String[] parseClass(String content) {
		if (content == null) {
			return new String[0];
		}
		return content.split(",|，");
	}

	public static String buildBarName(String className) {
		StringBuilder mBarName = new StringBuilder();
		for (int i = 0; i < className.length(); i++) {
			mBarName.append(className.charAt(i) + "\n");
		}
		return mBarName.length() > 0 ? mBarName.substring(0, mBarName.length() - 1) : "";
	}

	public static String buildStr(String mStr, String defaultStr) {
		if (mStr == null) {
			return defaultStr;
		}
		return mStr;
	}

	public static boolean isBlank(String s) {
		if (s == null) {
			return true;
		}
		return Pattern.matches("\\s*", s);
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static String join(String spliter, Object[] arr) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		if (spliter == null) {
			spliter = "";
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				break;
			}
			if (arr[i] == null) {
				continue;
			}
			builder.append(arr[i].toString());
			builder.append(spliter);
		}
		return builder.toString();
	}

	public static String fromFile(File f) throws IOException {
		InputStream is = new FileInputStream(f);
		byte[] bs = new byte[is.available()];
		is.read(bs);
		is.close();
		return new String(bs);
	}

}
