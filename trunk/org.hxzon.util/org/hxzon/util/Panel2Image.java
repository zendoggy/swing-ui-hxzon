//Panel2Image   -panel转图片 -gcode 
package org.hxzon.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel2Image {
	public static BufferedImage exportImage(JPanel panel, int x, int y,
			int width, int height) {
		BufferedImage panelImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) panelImage.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.translate(-x, -y);
		panel.paint(g);
		g.dispose();
		return panelImage;
	}

	public static BufferedImage reduceImg(BufferedImage image, String imgdist,
			int newWidth, int newHeight) {
		BufferedImage newImage = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);
		// Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
		newImage.getGraphics().drawImage(
				image
						.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
		return newImage;
	}

	public static void writeImage(BufferedImage image, String formatName,
			String output) {
		try {
			ImageIO.write(image, formatName, new File(output));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeImage(JPanel panel, String formatName, String output) {
		writeImage(
				exportImage(panel, 0, 0, panel.getWidth(), panel.getHeight()),
				formatName, output);
	}

	public static void writeImage(JPanel panel, int x, int y, int width,
			int height, String formatName, String output) {
		writeImage(exportImage(panel, x, y, width, height), formatName, output);
	}

	public static void main(String args[]) {
		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawString("hxzon",100,200);
				g.setColor(Color.GREEN);
				for (int i = 0 + 20; i < getWidth(); i += 20) {
					g.drawLine(i, 0, i, getHeight());
				}

				for (int i = getHeight() - 15; i > 0; i -= 15) {
					g.drawLine(0, i, getWidth(), i);
				}// end for
			}
		};
		panel.setSize(new Dimension(700, 500));
		Panel2Image.writeImage(panel, "png", "d:/panel.png");
		System.out.println("ok");
		Panel2Image.writeImage(panel, 105,20,100,500,"png", "d:/panel2.png");
		System.out.println("ok");
	}
}
