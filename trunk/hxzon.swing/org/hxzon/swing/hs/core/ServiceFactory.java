package org.hxzon.swing.hs.core;

import java.util.HashMap;
import java.util.Map;

import org.hxzon.validate.core.ValidatorFactory;

public class ServiceFactory {

	private static Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

	public static <S> S getService(Class<S> clazz) {
		return (S) services.get(clazz);
	}

	public static <S> void putService(Class<S> clazz, S s) {
		services.put(clazz, s);
	}

	static {
		services.put(ValidatorFactory.class, new ValidatorFactory());
	}
}
