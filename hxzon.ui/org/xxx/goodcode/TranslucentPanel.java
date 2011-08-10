package org.xxx.goodcode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TranslucentPanel extends JPanel {
	BufferedImage img;
	public TranslucentPanel() {
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		img = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.75f));// 设置合成规则
		g2d.setColor(Color.BLACK);// 设置背景色
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(img, 0, 0, null);
	}

	public static void main(String args[]) {
		JFrame f = new JFrame("translucent test panel");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new TranslucentPanel());
		f.setSize(400, 300);
		f.setVisible(true);
	}
}
