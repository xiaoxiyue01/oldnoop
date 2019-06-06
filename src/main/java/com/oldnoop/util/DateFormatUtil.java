package com.oldnoop.util;

import java.text.SimpleDateFormat;

public class DateFormatUtil {

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static final ThreadLocal<SimpleDateFormat> yyyyMMdd = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(YYYY_MM_DD);
		}

	};

	private static final ThreadLocal<SimpleDateFormat> yyyyMMddHHmmss = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		}

	};

	public static SimpleDateFormat getDateFormat(String pattern) {
		if (YYYY_MM_DD.equals(pattern)) {
			return getYmdFormat();
		}
		if (YYYY_MM_DD_HH_MM_SS.equals(pattern)) {
			return getYmdHmsFormat();
		}
		return null;
	}

	public static SimpleDateFormat getYmdFormat() {
		return yyyyMMdd.get();
	}

	public static SimpleDateFormat getYmdHmsFormat() {
		return yyyyMMddHHmmss.get();
	}
}
