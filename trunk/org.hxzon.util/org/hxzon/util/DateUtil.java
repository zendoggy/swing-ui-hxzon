package org.hxzon.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	public static String UnitePattern_day = "yyyy-MM-dd";
	public static String UnitePattern_minute = "yyyy-MM-dd, HH:mm";
	public static String UnitePattern_second = "yyyy-MM-dd, HH:mm:ss";
	public static String UnitePattern_nano = "yyyy-MM-dd, HH:mm:ss.SSS";
	public static String UnitePattern_time = UnitePattern_nano;

	private static Map<String, DateFormat> dateFormatCache = new HashMap<String, DateFormat>();

	private static DecimalFormat decimalFormat = new DecimalFormat();
	private static String numPattern = "000";

	public static String formatToDay(Date date) {
		return getFormat(UnitePattern_day).format(date);
	}

	public static String formatToMinute(Date date) {
		return getFormat(UnitePattern_minute).format(date);
	}

	public static String formatToSecond(Date date) {
		return getFormat(UnitePattern_second).format(date);
	}

	public static String formatToUniteTime(Date date) {
		return getFormat(UnitePattern_time).format(date);
	}

	public static String format(int i) {
		decimalFormat.applyPattern(numPattern);
		return decimalFormat.format(i);
	}

	public static String format(Date date, String pattern) {
		return getFormat(pattern).format(date);
	}

	private static DateFormat getFormat(String pattern) {
		DateFormat format = dateFormatCache.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			dateFormatCache.put(pattern, format);
		}
		return format;
	}
}
