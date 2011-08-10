package org.hxzon.goodcode;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public class Snap {

	private static String path;
	private static String mode;
	private static String nameFormat = "yyMMddhhmmss"; 
	private static Robot robot;
	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static boolean snap(String path, String mode, int delay) {
		try {
			Snap.mode = mode;
			Snap.path = path;
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int width = toolkit.getScreenSize().width;
			int height = toolkit.getScreenSize().height;
			robot.delay(delay);
			BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0,
					width, height));
			return output(image);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean snap(String path, String mode, int x1, int y1,
			int x2, int y2, int delay) {
		try {
			Snap.mode = mode;
			Snap.path = path;
			robot.delay(delay);
			BufferedImage image = robot.createScreenCapture(new Rectangle(x1,
					y1, (x2 - x1), (y2 - y1)));
			return output(image);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean output(BufferedImage image) {
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat(nameFormat);
		try {
			if (mode.contains("png")) {
				ImageIO.write(image, "png", new File(path + format.format(time)
						+ ".png"));
			}

			if (mode.contains("bmp")) {
				ImageIO.write(image, "bmp", new File(path + format.format(time)
						+ ".bmp"));
			}

			if (mode.contains("gif")) {
				ImageIO.write(image, "gif", new File(path + format.format(time)
						+ ".gif"));
			}
			if (mode.contains("jpg")) {
				ImageIO.write(image, "jpg", new File(path + format.format(time)
						+ ".jpg"));
			}
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		if (Snap.snap(".", "jpg", 200, 200, 600, 600, 3000) == true)
			System.out.println("ok");
		if (Snap.snap(".", "jpg", 3000) == true)
			System.out.println("ok");
	}
}
