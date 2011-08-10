package org.hxzon.swing.hs.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import org.hxzon.validate.core.ErrorMsg;

public class HSwingForm {

	private final Map<String, HSwingField> fieldsMap = new HashMap<String, HSwingField>();
	private final List<HSwingField> fields = new ArrayList<HSwingField>();

	public void addField(String name, String desc, Class<? extends HSwingField> compType, String restrict) {
		try {
			HSwingField field = compType.newInstance();
			field.setName(name).setDesc(desc).setRestrict(restrict);
			field.setLabel(new JLabel(name)).setErrorMsg(new ErrorMsg());
			field.apply();
			fields.add(field);
			fieldsMap.put(name, field);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public List<HSwingField> getFields() {
		return fields;
	}
}
