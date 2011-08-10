package test.hxzon.swing;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.hxzon.swing.components.ext.HDatePanel;
import org.hxzon.swing.util.HChangeListener;

public class TestHDatePanel {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		HDatePanel datePanel = new HDatePanel();
		datePanel.addChangeListener(new HChangeListener<HDatePanel>() {
			@Override
			public void change(HDatePanel source) {
				System.out.println(source.getDateAsString());

			}

		});
		f.add(datePanel);

		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
