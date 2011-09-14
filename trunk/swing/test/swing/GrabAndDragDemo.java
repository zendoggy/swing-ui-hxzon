package test.swing;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.MouseInputAdapter;

public class GrabAndDragDemo extends JFrame {
	public GrabAndDragDemo() {
		super("Grab-and-drag Demo");
		ImageIcon ii = new ImageIcon("earth.jpg");
		JScrollPane jsp = new JScrollPane(new GrabAndScrollLabel(ii));
		getContentPane().add(jsp);
		setSize(300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GrabAndDragDemo();
	}
}

class GrabAndScrollLabel extends JLabel {
	public GrabAndScrollLabel(ImageIcon i) {
		super(i);
		MouseInputAdapter mia = new MouseInputAdapter() {
			int m_XDifference, m_YDifference;
			Container c;

			public void mouseDragged(MouseEvent e) {
				c = GrabAndScrollLabel.this.getParent();
				if (c instanceof JViewport) {
					JViewport jv = (JViewport) c;
					Point p = jv.getViewPosition();
					int newX = p.x - (e.getX() - m_XDifference);
					int newY = p.y - (e.getY() - m_YDifference);
					int maxX = GrabAndScrollLabel.this.getWidth() - jv.getWidth();
					int maxY = GrabAndScrollLabel.this.getHeight() - jv.getHeight();
					if (newX < 0)
						newX = 0;
					if (newX > maxX)
						newX = maxY;
					if (newY < 0)
						newY = 0;
					if (newY > maxY)
						newY = maxY;
					jv.setViewPosition(new Point(maxX, maxY));
				}
			}

			public void mousePressed(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				m_XDifference = e.getX();
				m_YDifference = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};
		addMouseMotionListener(mia);
		addMouseListener(mia);
	}
}
