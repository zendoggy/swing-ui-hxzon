package org.hxzon.validate.simple;

import org.hxzon.util.ConverterUtil;
import org.hxzon.validate.core.AbstractValidator;
import org.hxzon.validate.core.ErrorMsg;

public class MinLengthValidator extends AbstractValidator {
	private int _min;

	public void init(String min) {
		this._min = Integer.valueOf(min);
	}

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2) {
		if (value == null) {
			return true;
		}
		String valueAsString = ConverterUtil.toStringNoNull(value);
		boolean pass = valueAsString.length() >= _min;
		if (!pass) {
			msg.addErrorMsg(desc + "'s length must >=" + _min);
		}
		return pass;
	}
}
