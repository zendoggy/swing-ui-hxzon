package org.hxzon.demo.swingx;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Insets;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hxzon.util.ImageUtil;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.AbstractLayoutPainter;
import org.jdesktop.swingx.painter.AlphaPainter;
import org.jdesktop.swingx.painter.CapsulePainter;
import org.jdesktop.swingx.painter.CheckerboardPainter;
import org.jdesktop.swingx.painter.GlossPainter;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.MattePainter;
import org.jdesktop.swingx.painter.PinstripePainter;

public class PainterDemo extends JXFrame {
    public PainterDemo() {
        super("painter demo");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        demo();
        this.setSize(600, 500);
    }

    public void demo() {
        final BufferedImage screenImage = ImageUtil.captureScreen(3);
        final JXPanel jxPanel = new JXPanel(new FlowLayout());
        jxPanel.setAlpha(0.6f);
        JXPanel buttonPanel = new JXPanel(new FlowLayout());
        buttonPanel.setAlpha(0.5f);
        JXButton button = new JXButton(new AbstractAction("ImagePainter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                jxPanel.setBackgroundPainter(new ImagePainter(screenImage));
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("CheckerboardPainter and GradientPaint") {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradientPaint gp = new GradientPaint(new Point2D.Double(0, 0), Color.BLACK, new Point2D.Double(0, 32), Color.GRAY);
                //象棋盘
                CheckerboardPainter p = new CheckerboardPainter();
                p.setDarkPaint(gp);
                p.setLightPaint(Color.WHITE);
                p.setSquareSize(32);
                jxPanel.setBackgroundPainter(p);
            }
        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("PinstripePainter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //细条纹
                PinstripePainter p = new PinstripePainter();
                p.setAngle(135);
                p.setPaint(Color.GRAY);
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("GlossPainter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //光泽
                GlossPainter p = new GlossPainter();
                p.setPaint(new Color(1.0f, 1.0f, 1.0f, 0.2f));
                p.setPosition(GlossPainter.GlossPosition.TOP);
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("MattePainter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //遮罩
//                MattePainter p = new MattePainter(Color.GREEN);
                TexturePaint paint = new TexturePaint(screenImage, new Rectangle2D.Double(0, 0, screenImage.getWidth(), screenImage.getHeight()));
                MattePainter p = new MattePainter(paint);
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("Capsule") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //胶囊
                CapsulePainter p = new CapsulePainter(CapsulePainter.Portion.Bottom);
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("Alpha") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Alpha
                AlphaPainter p = new AlphaPainter();
                p.setAlpha(0.4f);
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        button = new JXButton(new AbstractAction("AbstractLayoutPainter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Alpha
                ImagePainter p = new ImagePainter(screenImage);
                p.setVerticalAlignment(AbstractLayoutPainter.VerticalAlignment.BOTTOM);
                p.setHorizontalAlignment(AbstractLayoutPainter.HorizontalAlignment.RIGHT);
                p.setInsets(new Insets(0, 0, 5, 5));
                jxPanel.setBackgroundPainter(p);
            }

        });
        buttonPanel.add(button);
        jxPanel.add(buttonPanel);
        this.add(jxPanel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PainterDemo().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
