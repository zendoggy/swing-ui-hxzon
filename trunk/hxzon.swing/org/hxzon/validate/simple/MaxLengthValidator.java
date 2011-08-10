package org.hxzon.validate.simple;

import org.hxzon.util.ConverterUtil;
import org.hxzon.validate.core.AbstractValidator;
import org.hxzon.validate.core.ErrorMsg;

public class MaxLengthValidator extends AbstractValidator {
	private int _max;

	public void init(String max) {
		this._max = Integer.valueOf(max);
	}

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2) {
		if(value==null){
			return true;
		}
		String valueAsString = ConverterUtil.toStringNoNull(value);
		boolean pass = valueAsString.length() <= _max;
		if (!pass) {
			msg.addErrorMsg(desc + "'s length must <=" + _max);
		}
		return pass;
	}
}
