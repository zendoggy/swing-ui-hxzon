package org.hxzon.util;

import java.math.BigDecimal;

public class ConverterUtil {

	public static String toStringNoNull(Object o) {
		if (o == null) {
			return "";
		} else {
			return o.toString().trim();
		}
	}

	public static BigDecimal toBigDecimal(Object o) {
		String tmp = toStringNoNull(o);
		if (tmp.isEmpty()) {
			return null;
		} else {
			return new BigDecimal(tmp);
		}
	}
}
