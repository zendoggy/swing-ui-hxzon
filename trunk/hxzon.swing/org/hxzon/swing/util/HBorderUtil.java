package org.hxzon.swing.util;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class HBorderUtil {

	public static void useTitleBorder(JComponent comp, String title) {
		comp.setBorder(BorderFactory.createTitledBorder(title));
	}

	public static void useTitleBorder(JComponent comp, String title, int margin) {
		useTitleBorder(comp, title, margin, margin);
	}

	public static void useTitleBorder(JComponent comp, String title, int topBottom, int leftRight) {
		useTitleBorder(comp, title, topBottom, leftRight, topBottom, leftRight);
	}

	public static void useTitleBorder(JComponent comp, String title, int top, int left, int bottom, int right) {
		comp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(top, left, bottom, right), title));
	}

	public static void addTitleBorder(JComponent comp, String title) {
		comp.setBorder(BorderFactory.createTitledBorder(comp.getBorder(), title));
	}

	public static void addTitleBorder(JComponent comp, String title, int margin) {
		addTitleBorder(comp, title, margin, true);
	}

	public static void addTitleBorder(JComponent comp, String title, int margin, boolean origBorderInside) {
		comp.setBorder(BorderFactory.createTitledBorder(createCompoundBorder(createEmptyBorder(margin), comp.getBorder(), origBorderInside), title));
	}

	public static void useEmptyBorder(JComponent comp, int top, int left, int bottom, int right) {
		comp.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}

	public static void useEmptyBorder(JComponent comp, int margin) {
		useEmptyBorder(comp, margin, margin, margin, margin);
	}

	public static Border createEmptyBorder(int margin) {
		return BorderFactory.createEmptyBorder(margin, margin, margin, margin);
	}

	public static Border createCompoundBorder(Border border1, Border border2, boolean border2Inside) {
		if (border2Inside) {
			return BorderFactory.createCompoundBorder(border1, border2);
		} else {
			return BorderFactory.createCompoundBorder(border2, border1);
		}
	}

}
