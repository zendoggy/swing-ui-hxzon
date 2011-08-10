package test.hxzon.swing;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.hxzon.swing.hs.core.HSwingField;
import org.hxzon.swing.hs.core.HSwingForm;
import org.hxzon.swing.hs.field.HSwingTextField;

public class TestHSwingForm {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		HSwingForm form = new HSwingForm();
		form.addField("age", "年龄", HSwingTextField.class, "min=5;max=201");
		form.addField("email", "邮件", HSwingTextField.class, "minLength=4;maxLength=5");
		for (HSwingField field : form.getFields()) {
			if (!field.isNoLabel()) {
				f.add(field.getLabel());
			}
			f.add(field.getInput());
		}
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
