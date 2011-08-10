package org.hxzon.validate.core;

import java.util.ArrayList;
import java.util.List;

public class ErrorMsg {

	private final List<String> _errorMsg = new ArrayList<String>();

	public void addErrorMsg(String errorMsg) {
		_errorMsg.add(errorMsg);
	}

	public void clearErrorMsg() {
		_errorMsg.clear();
	}

	public String getFirstErrorMsg() {
		if (hasError()) {
			return _errorMsg.get(0);
		}
		return null;
	}

	public boolean hasError() {
		return !_errorMsg.isEmpty();
	}
}
