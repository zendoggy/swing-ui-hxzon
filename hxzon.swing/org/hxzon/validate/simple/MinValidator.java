package org.hxzon.validate.simple;

import java.math.BigDecimal;

import org.hxzon.util.ConverterUtil;
import org.hxzon.validate.core.AbstractValidator;
import org.hxzon.validate.core.ErrorMsg;

public class MinValidator extends AbstractValidator {
	private BigDecimal _min;

	public void init(String max) {
		this._min = new BigDecimal(max);
	}

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2) {
		BigDecimal valueAsBigDecimal = ConverterUtil.toBigDecimal(value);
		if (valueAsBigDecimal == null) {
			return true;
		}
		boolean pass = valueAsBigDecimal.compareTo(_min) >= 0;
		if (!pass) {
			msg.addErrorMsg(desc + "must >= " + _min);
		}
		return pass;
	}
}
