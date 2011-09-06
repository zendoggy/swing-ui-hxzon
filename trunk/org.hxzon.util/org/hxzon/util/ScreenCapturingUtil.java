package org.hxzon.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

    public static void output(BufferedImage image, String filepath) {
        try {
            if (filepath.endsWith("png")) {
                ImageIO.write(image, "png", new File(filepath));
            }
            if (filepath.endsWith("bmp")) {
                ImageIO.write(image, "bmp", new File(filepath));
            }
            if (filepath.endsWith("gif")) {
                ImageIO.write(image, "gif", new File(filepath));
            }
            if (filepath.endsWith("jpg")) {
                ImageIO.write(image, "jpg", new File(filepath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        BufferedImage image = ScreenCapturingUtil.capture(200, 200, 600, 600, 3000);
        if (image != null) {
            ScreenCapturingUtil.output(image, "screen.jpg");
            ScreenCapturingUtil.output(image, "screen.png");
        }
        image = ScreenCapturingUtil.capture(3000);
        if (image != null) {
            ScreenCapturingUtil.output(image, "fullScreen.jpg");
            ScreenCapturingUtil.output(image, "fullScreen.png");
        }
        System.out.println("ok");
    }
}
