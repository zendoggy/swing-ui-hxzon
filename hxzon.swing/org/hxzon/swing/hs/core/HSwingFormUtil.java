package org.hxzon.swing.hs.core;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HSwingFormUtil {

	public static void installValidatorWhenKeyEvent(final HSwingField field) {
		field.getInput().addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				field.validate();
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}

		});
	}

	public static void installValidatorWhenBlur(final HSwingField field) {
		field.getInput().addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				field.validate();
			}

		});
	}
}
