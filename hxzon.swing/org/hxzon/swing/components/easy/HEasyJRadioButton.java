package org.hxzon.swing.components.easy;

import javax.swing.JRadioButton;

import org.hxzon.swing.model.HEasyJModelValue;

public class HEasyJRadioButton<V> extends JRadioButton {
	private static final long serialVersionUID = 1L;
	private HEasyJModelValue<V> value;

	public HEasyJRadioButton(HEasyJModelValue<V> value) {
		super(value.display, value.isSel);
		this.value = value;
	}

	public HEasyJModelValue<V> getValueWrap() {
		return value;
	}

	public V getValue() {
		return value.value;
	}
}
