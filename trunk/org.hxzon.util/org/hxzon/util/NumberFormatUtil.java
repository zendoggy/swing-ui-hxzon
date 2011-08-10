package org.hxzon.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class NumberFormatUtil {
//	private static String numPattern = "000";

	private static Map<String, DecimalFormat> decimalFormatCache = new HashMap<String, DecimalFormat>();

	public static String format(int i, String pattern) {
		return getFormat(pattern).format(i);
	}

	private static DecimalFormat getFormat(String pattern) {
		DecimalFormat format = decimalFormatCache.get(pattern);
		if (format == null) {
			format = new DecimalFormat(pattern);
			decimalFormatCache.put(pattern, format);
		}
		return format;
	}
}
