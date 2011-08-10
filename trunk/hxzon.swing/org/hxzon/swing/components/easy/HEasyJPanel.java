package org.hxzon.swing.components.easy;

import javax.swing.JPanel;

import org.hxzon.swing.util.HBorderUtil;

public class HEasyJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public void useTitleBorder(String title) {
		HBorderUtil.useTitleBorder(this, title);
	}

	public void useEmptyBorder(int top, int left, int bottom, int right) {
		HBorderUtil.useEmptyBorder(this, top, left, bottom, right);
	}

	public void useEmptyBorder(int margin) {
		HBorderUtil.useEmptyBorder(this, margin);
	}
}
