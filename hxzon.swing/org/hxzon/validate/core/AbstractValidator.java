package org.hxzon.validate.core;

public abstract class AbstractValidator implements Validator {

	public void init(String register) {

	}

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1) {
		return validate(value, desc, msg, null, null);
	}

	public boolean validate(Object value, String desc, ErrorMsg msg) {
		return validate(value, desc, msg, null, null);
	}
}
