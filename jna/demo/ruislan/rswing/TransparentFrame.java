package demo.ruislan.rswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.jna.platform.WindowUtils;


/**
 * Transparent JFrame use JNA
 * 
 * @author ruislan <a href="mailto:z17520@126.com"/>
 * @version 0.1.0
 */
public class TransparentFrame {
    private JFrame frame;
    private JPanel container;
    private JSlider slider;
    private JPanel titleBar;
    private JLabel titleLabel;
    private JButton closeButton;

    public static void main(String[] args) {
        new TransparentFrame().launch();
    }

    private void launch() {
        createUI();
        launchUI();
    }

    protected void launchUI() {
        frame.setVisible(true);
    }

    protected void createUI() {
        System.setProperty("sun.java2d.noddraw", "true");
        frame = new JFrame();
        frame.setSize(200, 150);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);

        container = new JPanel();
        frame.setContentPane(container);
        container.setLayout(new BorderLayout());
        container.add(new JLabel("Ubunto vs Vista, I like both."),
                BorderLayout.CENTER);
        container.setBorder(new LineBorder(Color.BLACK));

        titleBar = new JPanel();
        titleBar.setLayout(new BorderLayout());
        titleLabel = new JLabel("JNA is great!");
        titleBar.add(titleLabel, BorderLayout.CENTER);
        titleBar.setBorder(new LineBorder(Color.GRAY));
        closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        titleBar.add(closeButton, BorderLayout.EAST);
        container.add(titleBar, BorderLayout.NORTH);

        slider = new JSlider(0, 100);
        slider.setValue(100);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float value = slider.getValue();
                WindowUtils.setWindowAlpha(frame, value * 0.01f);
            }
        });
        container.add(slider, BorderLayout.SOUTH);
        RoundRectangle2D.Float mask = new RoundRectangle2D.Float(0, 0, frame
                .getWidth(), frame.getHeight(), 20, 20);
        WindowUtils.setWindowMask(frame, mask);
        centerWindow(frame);
    }

    public static void centerWindow(Container window) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = window.getSize().width;
        int h = window.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        window.setLocation(x, y);
    }
}