package layout.formlayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestFormLayout {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 150);
		frame.getContentPane().setLayout(new FormLayout());
		JButton button = new JButton();
		button.setText("button");
		FormData formData = new FormData();
		formData.top = new FormAttachment(20, 0);
		formData.left = new FormAttachment(50, 0);
		formData.bottom = new FormAttachment(100, 30);
		formData.right = new FormAttachment(100, 50);
		frame.getContentPane().add(button, formData);
		frame.setVisible(true);
	}
}
