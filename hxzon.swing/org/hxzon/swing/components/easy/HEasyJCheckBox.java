package org.hxzon.swing.components.easy;

import javax.swing.JCheckBox;

import org.hxzon.swing.model.HEasyJModelValue;

public class HEasyJCheckBox<V> extends JCheckBox {
	private static final long serialVersionUID = 1L;
	private HEasyJModelValue<V> value;

	public HEasyJCheckBox(HEasyJModelValue<V> value) {
		super(value.display, value.isSel);
		this.value = value;
	}

	public HEasyJCheckBox(V value, String display, boolean check) {
		this(new HEasyJModelValue<V>(value, display, check));
	}

	public HEasyJModelValue<V> getValueWrap() {
		return value;
	}

	public V getValue() {
		return value.value;
	}
}
