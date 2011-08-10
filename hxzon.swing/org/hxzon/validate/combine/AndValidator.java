package org.hxzon.validate.combine;

import java.util.ArrayList;
import java.util.List;

import org.hxzon.validate.core.AbstractValidator;
import org.hxzon.validate.core.ErrorMsg;
import org.hxzon.validate.core.Validator;

public class AndValidator extends AbstractValidator {
	private final List<Validator> validaters = new ArrayList<Validator>();

	public void addValidate(Validator validater) {
		this.validaters.add(validater);
	}

	@Override
	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2) {
		for (Validator validater : validaters) {
			if (!validater.validate(value, desc, msg, o1, o2)) {
				return false;
			}
		}
		return true;
	}

}
