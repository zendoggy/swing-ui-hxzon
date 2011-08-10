package layout.dslLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TestDslLayout {

	public static void main(String args[]) {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ComponentProxy gapProxy=new Empty();
					ComponentProxy label1Proxy = new ComponentProxy(new JLabel("label 1:"));
					ComponentProxy text1Proxy = new ComponentProxy(new JTextField("text 1"));
					ComponentProxy label2Proxy=new ComponentProxy(new JLabel("label 2:"));
					ComponentProxy text2Proxy=new ComponentProxy(new JTextField("text 2"));
					ComponentProxy line1Proxy=new Beside(new Beside(label1Proxy,gapProxy,0.6),text1Proxy,0.5);
					ComponentProxy line2Proxy=new Beside(new Beside(label2Proxy,gapProxy,0.6),text2Proxy,0.5);
					ComponentProxy buttonProxy=new ComponentProxy(new JButton("button"));
					ComponentProxy line3Proxy=new Beside(gapProxy,buttonProxy,0.5);
					ComponentProxy allProxy=new Above(new Above(line1Proxy,line2Proxy,0.5),line3Proxy,0.6);
					allProxy.at(10, 10, 300, 100).in(frame);
//					ComponentProxy beside = new Beside(buttonProxy, new Empty(), 0.6);
//					beside.at(10, 10, 200, 100).in(frame);
//					ComponentProxy above=new Above(label1Proxy,text1Proxy,0.6);
//					above.at(10,10,200,100).in(frame);
//					ComponentProxyFactory.center(buttonProxy, 0.2, 0.3).at(0,0,400,200).in(frame);
					frame.setSize(500, 300);
					frame.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
