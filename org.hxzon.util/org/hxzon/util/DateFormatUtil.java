package org.hxzon.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatUtil {
	public static final String UnitePattern_day = "yyyy-MM-dd";
	public static final String UnitePattern_minute = "yyyy-MM-dd, HH:mm";
	public static final String UnitePattern_second = "yyyy-MM-dd, HH:mm:ss";
	public static final String UnitePattern_nano = "yyyy-MM-dd, HH:mm:ss.SSS";
	public static final String UnitePattern_time = UnitePattern_nano;
	public static final String UnitePattern_onlytime = "HH:mm:ss";

	private static Map<String, DateFormat> dateFormatCache = new HashMap<String, DateFormat>();

	public static String formatToDay(Date date) {
		return getFormat(UnitePattern_day).format(date);
	}

	public static String formatToDay() {
		return formatToDay(new Date());
	}

	public static String formatToMinute(Date date) {
		return getFormat(UnitePattern_minute).format(date);
	}

	public static String formatToMinute() {
		return formatToMinute(new Date());
	}

	public static String formatToSecond(Date date) {
		return getFormat(UnitePattern_second).format(date);
	}

	public static String formatToSecond() {
		return formatToSecond(new Date());
	}

	public static String formatToUniteTime(Date date) {
		return getFormat(UnitePattern_time).format(date);
	}

	public static String formatToUniteTime() {
		return formatToUniteTime(new Date());
	}

	public static String formatToOnlytime(Date date) {
		return getFormat(UnitePattern_onlytime).format(date);
	}

	public static String formatToOnlytime() {
		return formatToOnlytime(new Date());
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

	public static String formatDate(int year, int month, int date) {
		return NumberFormatUtil.format(year, "0000") + "-" + NumberFormatUtil.format(month, "00") + "-" + NumberFormatUtil.format(date, "00");
	}

	public static String formatTime(int hour, int minute, int second) {
		return NumberFormatUtil.format(hour, "00") + ":" + NumberFormatUtil.format(minute, "00") + ":" + NumberFormatUtil.format(second, "00");
	}
}
