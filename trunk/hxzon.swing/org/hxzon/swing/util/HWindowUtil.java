package org.hxzon.swing.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class HWindowUtil {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Rectangle fullScreenBounds;
	public static Map<Window, Rectangle> fullScreenWindowMap = new HashMap<Window, Rectangle>();
	static {
		JFrame jframe = new JFrame();
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setVisible(true);
		fullScreenBounds = jframe.getBounds();
	}

	public static void centerInScreen(Window window) {
//        window.pack();
		Dimension size = window.getSize();
		int y = (screenSize.height - size.height) / 2;
		int x = (screenSize.width - size.width) / 2;
		window.setLocation(x, y);
	}

	public static void fullScreen(Window window) {
		window.setBounds(fullScreenBounds);
	}

	public static void switchFullScreenState(Window window) {
		Rectangle size = window.getBounds();
		if (size.height == fullScreenBounds.height && size.width == fullScreenBounds.width) {
			Rectangle origBounds = fullScreenWindowMap.get(window);
			if (origBounds == null) {
				return;
			}
			window.setBounds(origBounds);
			fullScreenWindowMap.remove(window);
		} else {
			fullScreenWindowMap.put(window, window.getBounds());
			window.setBounds(fullScreenBounds);
		}
	}

	public static void addSwitchFullScreen(final Window window) {
		window.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					switchFullScreenState(window);
				}
			}
		});
	}
}
