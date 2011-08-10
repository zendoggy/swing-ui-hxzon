package org.hxzon.validate.simple;

import org.hxzon.util.ConverterUtil;
import org.hxzon.validate.core.AbstractValidator;
import org.hxzon.validate.core.ErrorMsg;

public class RequiredValidator extends AbstractValidator {

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2) {
		boolean pass = true;
		if (value == null) {
			pass = false;
		} else {
			String valueAsString = ConverterUtil.toStringNoNull(value);
			pass = !valueAsString.isEmpty();
		}
		if (!pass) {
			msg.addErrorMsg(desc + " required");
		}
		return pass;
	}
}
