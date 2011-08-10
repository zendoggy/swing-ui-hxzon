package org.hxzon.validate.core;

public interface Validator {
	public void init(String restrict);

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1, Object o2);

	public boolean validate(Object value, String desc, ErrorMsg msg, Object o1);

	public boolean validate(Object value, String desc, ErrorMsg msg);
}
