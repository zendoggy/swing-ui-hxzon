package org.hxzon.swing.hs.core;

import javax.swing.JComponent;

import org.hxzon.validate.core.ErrorMsg;
import org.hxzon.validate.core.Validator;

public interface HSwingField {
	public HSwingField setName(String name);

	public String getName();

	public HSwingField setDesc(String name);

	public String getDesc();

	public HSwingField setLabel(JComponent label);

	public JComponent getLabel();

	public HSwingField setNoLabel(boolean b);

	public boolean isNoLabel();

	public JComponent getInput();

	public boolean isEditable();

	public HSwingField setEditable(boolean b);

	public HSwingField setValidator(Validator validater);

	public Validator getValidator();

	public Object getValue();

	public Object getPreValue();

	public HSwingField setRestrict(String restrict);

	public HSwingField setValidateWhenKeyUp(boolean b);

	public HSwingField setValidateWhenBlur(boolean b);

	public boolean isValidateWhenKeyUp();

	public boolean isValidateWhenBlur();

	public HSwingField setErrorMsg(ErrorMsg msg);

	public ErrorMsg getErrorMsg();
	
	public void apply();
	
	public void validate();
}
