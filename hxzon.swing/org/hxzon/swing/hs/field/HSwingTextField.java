package org.hxzon.swing.hs.field;

import java.awt.Color;

import javax.swing.JTextField;

import org.hxzon.swing.hs.core.HAbstractSwingField;
import org.hxzon.swing.hs.core.HSwingField;
import org.hxzon.swing.hs.core.HSwingFormUtil;
import org.hxzon.swing.hs.core.ServiceFactory;
import org.hxzon.validate.core.Validator;
import org.hxzon.validate.core.ValidatorFactory;

public class HSwingTextField extends HAbstractSwingField {

	public void apply() {
		JTextField textField = new JTextField();
		this.setInput(textField);
		if (!this.isEditable()) {
			textField.setEditable(false);
		}
		if (this.isValidateWhenKeyUp()) {
			HSwingFormUtil.installValidatorWhenKeyEvent(this);
		}
		if (this.isValidateWhenBlur()) {
			HSwingFormUtil.installValidatorWhenBlur(this);
		}
	}

	@Override
	public HSwingField setRestrict(String restrict) {
		Validator validater = ServiceFactory.getService(ValidatorFactory.class).getValidator(restrict);
		if (validater != null) {
			this.setValidator(validater);
		}
		return this;
	}

	public JTextField getInput() {
		return (JTextField) super.getInput();
	}

	public void validate() {
		getErrorMsg().clearErrorMsg();
		if (this.getValidator() != null) {
			JTextField input = getInput();
			String value = input.getText();
			this.getValidator().validate(value, getDesc(), getErrorMsg());
			if (getErrorMsg().hasError()) {
				input.setBackground(Color.RED);
				input.setToolTipText(getErrorMsg().getFirstErrorMsg());
				System.out.println(getDesc() + ":" + value + "," + getErrorMsg().getFirstErrorMsg());
			} else {
				input.setBackground(Color.WHITE);
				input.setToolTipText(null);
			}
		}
	}

}
