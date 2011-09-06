package org.hxzon.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

public class ImageUtil {

    public static BufferedImage createThumbnail(BufferedImage image, int newWidth, int newHeight) {
        return GraphicsUtilities.createThumbnail(image, newWidth, newHeight);
    }

    public static BufferedImage createThumbnail(BufferedImage image, int newSize) {
        return GraphicsUtilities.createThumbnail(image, newSize);
    }

    public static BufferedImage captureScreen(int delay) {
        return ScreenCapturingUtil.capture(delay);
    }

    public static BufferedImage captureScreen(int x, int y, int width, int height, int delay) {
        return ScreenCapturingUtil.capture(x, y, width, height, delay);
    }

    public static BufferedImage captureImage(BufferedImage image, int x, int y, int width, int height) {
        BufferedImage result = GraphicsUtilities.createCompatibleImage(image, width, height);
        Graphics2D g2 = result.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, width, height, x, y, width, height, null);
        return result;
    }

    public static void writeImage(BufferedImage image, String filepath) {
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

    @SuppressWarnings("unused")
    private static void testCaptureScreen() {
        BufferedImage image = ScreenCapturingUtil.capture(200, 200, 600, 600, 3000);
        if (image != null) {
            ImageUtil.writeImage(image, "screen.jpg");
            ImageUtil.writeImage(image, "screen.png");
        }
        image = ScreenCapturingUtil.capture(3000);
        if (image != null) {
            ImageUtil.writeImage(image, "fullScreen.jpg");
            ImageUtil.writeImage(image, "fullScreen.png");
        }
        System.out.println("ok");
    }

    @SuppressWarnings("unused")
    private static void testCaptureImage() {
        BufferedImage image = ScreenCapturingUtil.capture(3000);
        image = ImageUtil.captureImage(image, 30, 30, 800, 800);
        ImageUtil.writeImage(image, "captureImage.png");
        System.out.println("ok");
    }

    @SuppressWarnings("unused")
    private static void testThumbnail() {
        BufferedImage image = ScreenCapturingUtil.capture(3000);
        if (image != null) {
            ImageUtil.writeImage(image, "fullScreen.png");
            BufferedImage thumbnailImage;
            int width = image.getWidth();
            for (; width > 50;) {
                width /= 2;
//                width = width * 2 / 3;
                thumbnailImage = createThumbnail(image, width);
                ImageUtil.writeImage(thumbnailImage, "fullScreen-" + width + ".png");
                thumbnailImage = GraphicsUtilities.createThumbnailFast(image, width);
                ImageUtil.writeImage(thumbnailImage, "fullScreen-" + width + "-fast.png");
            }
        }
        System.out.println("ok");
    }

    public static void main(String[] args) {
//        testCaptureScreen();
//        testCaptureImage();
//        testThumbnail();
    }
}
