package test.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddComponent extends JFrame implements ActionListener {
	JPanel contentPanel = (JPanel) this.getContentPane();
	JButton jb = new JButton("add");
	JPanel jp1 = new JPanel(new FlowLayout());
	JPanel jp2 = new JPanel(new FlowLayout());

	public AddComponent() {
		super("myJFrame");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add("North", jp1);
		contentPanel.add("Center", jp2);
		jp1.add(jb);
		jb.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb) {
			JButton jb2 = new JButton("JButton" + (jp2.getComponentCount() + 1));
			jb2.addActionListener(this);
			jp2.add(jb2);
			this.validate();
		} else {
			jp2.remove((JButton) e.getSource());
			this.validate();
			this.repaint();
		}
	}

	public static void main(String args[]) {
		new AddComponent();
	}
}