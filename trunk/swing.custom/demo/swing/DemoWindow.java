package demo.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DemoWindow extends JFrame implements MouseListener, MouseMotionListener, ComponentListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private Icon northwestIcon;

	private Icon northeastIcon;

	private Icon northIcon;

	private Icon southwestIcon;

	private Icon southeastIcon;

	private Icon southIcon;

	private Icon westIcon;

	private Icon eastIcon;

	private Icon closeIcon;

	private Icon closeoverIcon;

	private Icon closedownIcon;

	// point

	private Point location;

	private Point startLocationForResize;

	// cursor
	private Cursor sizeCursor = new Cursor(Cursor.SE_RESIZE_CURSOR);

	private Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);

	private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

	//
	private ContentPane contentPane;

	private JButton closeButton;

	private JTextField textField;

	// color

	private Color color1 = new Color(254, 254, 254);

	private Color color2 = new Color(204, 220, 247);

	private Color titleColor = new Color(128, 128, 128);

	// title bar
	private String title;

	private Icon icon;

	/**
	 * Create the frame
	 */
	public DemoWindow() {
		super();
		setUndecorated(true);
		initIcon();
		initComponent();
		contentPane.addComponentListener(this);
		contentPane.addMouseListener(this);
		contentPane.addMouseMotionListener(this);
		//
	}

	public void setTitle(String title) {
		this.title = title;
		contentPane.repaint();
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	private void initComponent() {
		contentPane = new ContentPane();
		setContentPane(contentPane);
		closeButton = new JButton();
		closeButton.setBorder(BorderFactory.createEmptyBorder());
		closeButton.setIcon(closeIcon);
		closeButton.setRolloverIcon(closeoverIcon);
		closeButton.setPressedIcon(closedownIcon);
		closeButton.addActionListener(this);
		contentPane.add(closeButton);
		textField = new JTextField();
		contentPane.add(textField);
	}

	private void initIcon() {
		northwestIcon = new ImageIcon(DemoWindow.class.getResource("northwest.png"));
		northeastIcon = new ImageIcon(DemoWindow.class.getResource("northeast.png"));
		northIcon = new ImageIcon(DemoWindow.class.getResource("north.png"));
		southwestIcon = new ImageIcon(DemoWindow.class.getResource("southwest.png"));
		southeastIcon = new ImageIcon(DemoWindow.class.getResource("southeast.png"));
		southIcon = new ImageIcon(DemoWindow.class.getResource("south.png"));
		westIcon = new ImageIcon(DemoWindow.class.getResource("west.png"));
		eastIcon = new ImageIcon(DemoWindow.class.getResource("east.png"));
		closeIcon = new ImageIcon(DemoWindow.class.getResource("close.png"));
		closeoverIcon = new ImageIcon(DemoWindow.class.getResource("closeover.png"));
		closedownIcon = new ImageIcon(DemoWindow.class.getResource("closedown.png"));
	}

	private class ContentPane extends JComponent {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			int w = getWidth();
			int h = getHeight();
			northwestIcon.paintIcon(this, g, 0, 0);
			northeastIcon.paintIcon(this, g, w - northeastIcon.getIconWidth(), 0);
			g.drawImage(((ImageIcon) northIcon).getImage(), northwestIcon.getIconWidth(), 0, w - northeastIcon.getIconWidth(), northIcon.getIconHeight(), 0, 0, northIcon.getIconWidth(),
					northIcon.getIconHeight(), this);
			southwestIcon.paintIcon(this, g, 0, h - southwestIcon.getIconHeight());
			southeastIcon.paintIcon(this, g, w - southeastIcon.getIconWidth(), h - southeastIcon.getIconHeight());
			g.drawImage(((ImageIcon) southIcon).getImage(), southwestIcon.getIconWidth(), h - southIcon.getIconHeight(), w - southeastIcon.getIconWidth(), h, 0, 0, southIcon.getIconWidth(),
					southIcon.getIconHeight(), this);
			g.drawImage(((ImageIcon) westIcon).getImage(), 0, northeastIcon.getIconHeight(), westIcon.getIconWidth(), h - southwestIcon.getIconHeight(), 0, 0, westIcon.getIconWidth(),
					westIcon.getIconHeight(), this);
			g.drawImage(((ImageIcon) eastIcon).getImage(), w - eastIcon.getIconWidth(), northeastIcon.getIconHeight(), w, h - southeastIcon.getIconHeight(), 0, 0, eastIcon.getIconWidth(),
					eastIcon.getIconHeight(), this);
			GradientPaint gp = new GradientPaint(0, 0, color1, 0, h - 1, color2);
			((Graphics2D) g).setPaint(gp);
			g.fillRect(westIcon.getIconWidth(), northIcon.getIconHeight(), w - westIcon.getIconWidth() - eastIcon.getIconWidth(), h - northIcon.getIconHeight() - southIcon.getIconHeight());
			// title
			if (title == null) {
				title = "";
			}
			g.setColor(titleColor);
			FontMetrics fm = getFontMetrics(getFont());
			g.drawString(title, (getWidth() - fm.stringWidth(title)) / 2, northIcon.getIconHeight() / 2 + fm.getHeight() / 4);
			if (icon != null) {
				icon.paintIcon(contentPane, g, getWidth() / 2 - fm.stringWidth(title) / 2 - icon.getIconWidth() - 10, northIcon.getIconHeight() / 2 - icon.getIconHeight() / 2);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int x = e.getX();
			int y = e.getY();
			int w = contentPane.getWidth();
//			int h = contentPane.getHeight();
			if (x > northwestIcon.getIconWidth() && x < w - northeastIcon.getIconWidth() - 30 && y > 0 && y < northIcon.getIconHeight()) {
				setSize(200, 300);
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int w = contentPane.getWidth();
		int h = contentPane.getHeight();
		if (x > northwestIcon.getIconWidth() && x < contentPane.getWidth() - northeastIcon.getIconWidth() && y > 0 && y < northIcon.getIconHeight()) {
			location = e.getLocationOnScreen();
		} else if (x > w - southeastIcon.getIconWidth() && x < w && y > h - southeastIcon.getIconHeight() && y < h) {
			startLocationForResize = e.getLocationOnScreen();
		}

	}

	public void mouseReleased(MouseEvent e) {
		location = null;
		if (startLocationForResize != null) {
			try {
				Point newPoint = e.getLocationOnScreen();
				Dimension d = new Dimension(getSize().width + newPoint.x - startLocationForResize.x, getSize().height + newPoint.y - startLocationForResize.y);
				if (d.width > 100 && d.height > 50) {
					setSize(d);
				}
			} finally {
				startLocationForResize = null;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (location != null) {
			Point newPoint = e.getLocationOnScreen();
			setLocation(getLocation().x + newPoint.x - location.x, getLocation().y + newPoint.y - location.y);
			location = newPoint;
		}
	}

	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int w = contentPane.getWidth();
		int h = contentPane.getHeight();
		Cursor newCursor = null;
		if (x > w - southeastIcon.getIconWidth() && x < w && y > h - southeastIcon.getIconHeight() && y < h) {
			newCursor = sizeCursor;
		} else if (x > northwestIcon.getIconWidth() && x < w - northeastIcon.getIconWidth() - 30 && y > 0 && y < northIcon.getIconHeight()) {
			newCursor = moveCursor;
		} else {
			newCursor = defaultCursor;
		}
		if (contentPane.getCursor() != newCursor) {
			contentPane.setCursor(newCursor);
		}
	}

	//
	public void componentHidden(ComponentEvent e) {

	}

	public void componentMoved(ComponentEvent e) {

	}

	public void componentResized(ComponentEvent e) {
		closeButton.setBounds(contentPane.getWidth() - northeastIcon.getIconWidth() - closeIcon.getIconWidth(), 0, closeIcon.getIconWidth(), closeIcon.getIconHeight());
		textField.setBounds(contentPane.getWidth() / 2 - 50, contentPane.getHeight() / 2 - 12, 100, 24);
	}

	public void componentShown(ComponentEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		dispose();
	}

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					DemoWindow win = new DemoWindow();
					win.setBounds(100, 100, 400, 300);
					win.setTitle("打造专业外观-Swing");
					win.setIcon(new ImageIcon(DemoWindow.class.getResource("title.png")));
					win.setVisible(true);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
