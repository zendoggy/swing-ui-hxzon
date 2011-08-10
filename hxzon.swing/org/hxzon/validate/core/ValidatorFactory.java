package org.hxzon.validate.core;

import java.util.HashMap;
import java.util.Map;

import org.hxzon.validate.combine.AndValidator;
import org.hxzon.validate.simple.MaxLengthValidator;
import org.hxzon.validate.simple.MaxValidator;
import org.hxzon.validate.simple.MinLengthValidator;
import org.hxzon.validate.simple.MinValidator;
import org.hxzon.validate.simple.RequiredValidator;

public class ValidatorFactory {

	private Map<String, Validator> validators = new HashMap<String, Validator>();
	private Map<String, Class<? extends Validator>> register = new HashMap<String, Class<? extends Validator>>();

	public ValidatorFactory() {
		registerDefaultValidator();
	}

	public void registerDefaultValidator() {
		registerValidator("required", RequiredValidator.class);
		registerValidator("minLength", MinLengthValidator.class);
		registerValidator("maxLength", MaxLengthValidator.class);
		registerValidator("min", MinValidator.class);
		registerValidator("max", MaxValidator.class);
	}

	public void registerValidator(String name, Class<? extends Validator> clazz) {
		register.put(name, clazz);
	}

	public void addValidator(String restrict, Validator validater) {
		validators.put(restrict, validater);
	}

	public Validator getValidator(String restrict) {
		Validator result = validators.get(restrict);
		if (result == null) {
			result = new AndValidator();
			String[] restricts = restrict.split(";");
			for (String resItem : restricts) {
				((AndValidator) result).addValidate(getSimpleValidator(resItem));
			}
			validators.put(restrict, result);
		}
		return result;
	}

	private Validator getSimpleValidator(String restrict) {
		Validator result = validators.get(restrict);
		if (result == null) {
			String[] tmp = restrict.split("=");
			Class<? extends Validator> clazz = register.get(tmp[0]);
			if (clazz != null) {
				try {
					result = clazz.newInstance();
					if (tmp.length == 2) {
						result.init(tmp[1]);
					}
					validators.put(restrict, result);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
