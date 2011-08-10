package org.hxzon.swing.components.ext;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HMessagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String Message_Empty = " ";
	private JLabel message = new JLabel(Message_Empty);
	private ClearMessageTimer timer;
	private Color normalColor = Color.BLUE;
	private Color importantColor = Color.RED;
	private Color errorColor = Color.RED;

	public HMessagePanel() {
//		super(new MigLayout());
		add(message);
	}

	public void showText(String text, Color color, int timeout) {
		message.setForeground(color);
		message.setText(text);
		if (timeout != -1) {
			if (timer == null) {
				timer = new ClearMessageTimer(timeout);
				timer.start();
			} else {
				timer.setDelay(timeout);
				timer.restart();
			}
		} else {
			if (timer != null) {
				timer.stop();
			}
		}
	}

	public void showText(String text, Color color) {
		this.showText(text, color, -1);
	}

//	public void showText(String text, boolean important) {
//		if (text == null || text.isEmpty()) {
//			text = " ";
//		}
//		text = "<html><u><font color=red>" + text + "</font></u></html>";
//		if (important) {
//			message.setForeground(Color.red);
//		}
//		message.setForeground(Color.blue);
//		message.setText(text);
//	}

	public void showMessage(String text) {
		showText(text, normalColor);
	}

	public void showMessage(String text, int timeout) {
		showText(text, normalColor, timeout);
	}

	public void showErrorMessage(String text) {
		showText(text, errorColor);
	}

	public void showErrorMessage(String text, int timeout) {
		showText(text, errorColor, timeout);
	}

	public void showImportantMessage(String text) {
		showText(text, importantColor);
	}

	public void showImportantMessage(String text, int timeout) {
		showText(text, importantColor, timeout);
	}

	public void clearMessage() {
		showText(Message_Empty, normalColor);
	}

	private void clearMessageByTimer() {
		clearMessage();
		timer.stop();
	}

	public JLabel getLabel() {
		return message;
	}

	public Color getNormalColor() {
		return normalColor;
	}

	public void setNormalColor(Color normalColor) {
		this.normalColor = normalColor;
	}

	public Color getImportantColor() {
		return importantColor;
	}

	public void setImportantColor(Color importantColor) {
		this.importantColor = importantColor;
	}

	public Color getErrorColor() {
		return errorColor;
	}

	public void setErrorColor(Color errorColor) {
		this.errorColor = errorColor;
	}

	private class ClearMessageTimer extends Timer {
		private static final long serialVersionUID = 1L;

		public ClearMessageTimer(int timeout) {
			super(timeout, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clearMessageByTimer();
				}

			});
		}
	}
}
