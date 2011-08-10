package org.hxzon.swing.model;

public class HEasyJModelValue<V> {
	public V value;
	public String display;
	public boolean isSel;

	public HEasyJModelValue(V value, String display, boolean preSel) {
		this.value = value;
		this.display = display;
		this.isSel = preSel;
	}

	public String toString() {
		return display;
	}
}
