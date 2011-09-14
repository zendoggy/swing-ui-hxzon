package other.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.openswing.swing.client.CurrencyControl;
import org.openswing.swing.client.NumericControl;

import com.l2fprod.common.swing.PercentLayout;

public class DateSpinner {

	public static void main(String args[]){
		JFrame f=new JFrame("jdate chooser");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 400);
		f.setLocation(300, 300);
		//
		JSpinner time = new JSpinner(new SpinnerDateModel());
		time.setPreferredSize(new Dimension(0, 20));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(time, "dd--HH:mm:ss");
		editor.getTextField().setHorizontalAlignment(JTextField.RIGHT);
		time.setEditor(editor);
		final JTextField field=editor.getTextField();
		//
		f.add(time,BorderLayout.NORTH);
		JButton button=new JButton(new AbstractAction("打印"){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(field.getText());
            }
		    
		});
		f.add(button,BorderLayout.SOUTH);
		f.setVisible(true);
	    
//		System.out.println(System.getProperty("os.name"));
	    
	    JFrame f2=new JFrame();
	    f2.setLayout(new PercentLayout());
	    f2.add(new JTextField("hello"),"*");
	    f2.add(new JButton("hellobt"),"10%");
	    f2.add(new JLabel("label"),"10%");
	    
	    f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f2.setSize(500, 400);
      f2.setLocation(300, 300);
	    f2.setVisible(true);
	    
	       JFrame f3=new JFrame();
	        f3.setLayout(new PercentLayout());
	        f3.add(new CurrencyControl(),"100");
	        f3.add(new CurrencyControl(),"100");
	        f3.add(new NumericControl(),"100");
	        f3.add(new test.swing.CurrencyBox(),"100");
	        
	        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      f3.setSize(500, 400);
	      f3.setLocation(300, 300);
	        f3.setVisible(true);
	}
	

}
