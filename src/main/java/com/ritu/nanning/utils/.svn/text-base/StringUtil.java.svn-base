package com.ritu.nanning.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class StringUtil {

	public static final String URL_REG_EXPRESSION = "^(https?://)?([a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+)+(/*[A-Za-z0-9/\\-_&:?\\+=//.%]*)*";
	public static final String EMAIL_REG_EXPRESSION = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";

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

	public static void toFile(File f, String s) throws IOException {
		// 只有手机rom有足够的空间才写入本地缓存
		// if (CommonUtil.enoughSpaceOnPhone(s.getBytes().length)) {
		// FileOutputStream fos = new FileOutputStream(f);
		// fos.write(s.getBytes());
		// fos.close();
		// }
	}

}
