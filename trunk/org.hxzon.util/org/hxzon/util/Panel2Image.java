//Panel2Image   -panel转图片 -gcode 
package org.hxzon.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Panel2Image {
    public static BufferedImage exportImage(JComponent panel, int x, int y, int width, int height) {
        BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) panelImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.translate(-x, -y);
        panel.paint(g);
        g.dispose();
        return panelImage;
    }

    public static void main(String args[]) {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawString("hxzon", 100, 200);
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
        BufferedImage image = Panel2Image.exportImage(panel, 0, 0, panel.getWidth(), panel.getHeight());
        ImageUtil.writeImage(image, "panel.png");
        image = Panel2Image.exportImage(panel, 105, 20, 100, 500);
        ImageUtil.writeImage(image, "panel2.png");
        System.out.println("ok");
    }
}
