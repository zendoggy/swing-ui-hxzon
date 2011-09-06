package org.hxzon.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class ScreenCapturingUtil {

    private static Robot robot;
    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage capture(int delay) {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            robot.delay(delay);
            BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static BufferedImage capture(int x, int y, int width, int height, int delay) {
        try {
            robot.delay(delay);
            BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
