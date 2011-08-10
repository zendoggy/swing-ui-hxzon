package test.hxzon.swing;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.hxzon.swing.components.ext.HDatePicker;

public class TestHDatePicker {
	public static class S {
		public S(int i) {

		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		HDatePicker datepicker = new HDatePicker();
		f.add(datepicker);
		f.add(new JButton("hello"));
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
