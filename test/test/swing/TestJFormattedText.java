package test.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import java.text.*;

public class TestJFormattedText {
	public static void main(String args[]) throws ParseException {
		JFrame f = new JFrame("JFormattedTextField Sample");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		// Four-digit year, followed by month name and day of month,
		// each separated by two dashes (--)
		DateFormat format = new SimpleDateFormat("yyyy--MM--dd");
		DateFormatter df = new DateFormatter(format);
		JFormattedTextField ftf1 = new JFormattedTextField(df);
		ftf1.setValue(new Date());
		content.add(ftf1);
		// US Social Security number
		MaskFormatter mf1 = new MaskFormatter("###-##-####");
		mf1.setPlaceholderCharacter('_');
		JFormattedTextField ftf2 = new JFormattedTextField(mf1);
		content.add(ftf2);
		// US telephone number
		MaskFormatter mf2 = new MaskFormatter("(###) ###-####");
		JFormattedTextField ftf3 = new JFormattedTextField(mf2);
		content.add(ftf3);
		f.setSize(300, 100);
		f.setVisible(true);
	}
}
