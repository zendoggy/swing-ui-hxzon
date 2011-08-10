package test.hxzon.swing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.hxzon.swing.components.ext.HMultiComboBox;
import org.hxzon.swing.model.HEasyJModelValue;

public class TestHMultiComboBox {
	public static class S {
		public S(int i) {

		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		List<HEasyJModelValue<S>> values = new ArrayList<HEasyJModelValue<S>>();
		for (int i = 0; i < 10; i++) {
			values.add(new HEasyJModelValue<S>(new S(i), i + "xx", true));
		}
		HMultiComboBox<S> box = new HMultiComboBox<S>();
		box.setItems(values);
		f.add(box);
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
