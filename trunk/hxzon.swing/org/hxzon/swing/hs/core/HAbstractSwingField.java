package org.hxzon.swing.hs.core;

import javax.swing.JComponent;

import org.hxzon.validate.core.ErrorMsg;
import org.hxzon.validate.core.Validator;

public abstract class HAbstractSwingField implements HSwingField {

	private String _name;
	private String _desc;
	private JComponent _label;
	private JComponent _input;
	private Object _value;
	private Object _preValue;
	private boolean _editable = true;
	private boolean _noLabel;
	private boolean _validateWhenKeyUp = true;
	private boolean _validateWhenBlur = true;
	private Validator _validator;
	private ErrorMsg _errorMsg;

	protected HAbstractSwingField() {
	}

	public void apply() {

	}

	public String getName() {
		return _name;
	}

	public HSwingField setName(String name) {
		_name = name;
		return this;
	}

	public String getDesc() {
		return _desc;
	}

	public HSwingField setDesc(String desc) {
		_desc = desc;
		return this;
	}

	public JComponent getLabel() {
		return _label;
	}

	public HSwingField setLabel(JComponent label) {
		_label = label;
		return this;
	}

	public JComponent getInput() {
		return _input;
	}

	public HSwingField setInput(JComponent input) {
		_input = input;
		return this;
	}

	public Object getValue() {
		return _value;
	}

	public HSwingField setValue(Object value) {
		_value = value;
		return this;
	}

	public Object getPreValue() {
		return _preValue;
	}

	public HSwingField setPreValue(Object preValue) {
		_preValue = preValue;
		return this;
	}

	public boolean isEditable() {
		return _editable;
	}

	public HSwingField setEditable(boolean editable) {
		_editable = editable;
		return this;
	}

	public boolean isNoLabel() {
		return _noLabel;
	}

	public HSwingField setNoLabel(boolean noLabel) {
		_noLabel = noLabel;
		return this;
	}

	public boolean isValidateWhenKeyUp() {
		return _validateWhenKeyUp;
	}

	public HSwingField setValidateWhenKeyUp(boolean validateWhenKeyType) {
		_validateWhenKeyUp = validateWhenKeyType;
		return this;
	}

	public boolean isValidateWhenBlur() {
		return _validateWhenBlur;
	}

	public HSwingField setValidateWhenBlur(boolean validateWhenBlur) {
		_validateWhenBlur = validateWhenBlur;
		return this;
	}

	public Validator getValidator() {
		return _validator;
	}

	public HSwingField setValidator(Validator validater) {
		_validator = validater;
		return this;
	}

	public ErrorMsg getErrorMsg() {
		return _errorMsg;
	}

	public HSwingField setErrorMsg(ErrorMsg errorMsg) {
		_errorMsg = errorMsg;
		return this;
	}

}
