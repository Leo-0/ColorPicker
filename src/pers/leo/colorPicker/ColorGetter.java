package pers.leo.colorPicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ColorGetter {
	private Robot robot;

	public ColorGetter() throws AWTException {
		this.robot = new Robot();
	}

	// ��ȡ��ǰ���λ����Ļ������
	public Color pickColor() {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		return robot.getPixelColor(x, y);
	}

	// ���������ȡ��Ļ������
	public Color pickColor(int x, int y) {
		return robot.getPixelColor(x, y);
	}

	// ��ȡָ���������Ļ��ͼ
	public BufferedImage getScreenCapture(int x, int y, int w, int h) {
		Rectangle rec = new Rectangle(x, y, w, h);
		BufferedImage bi = robot.createScreenCapture(rec);
		return bi;
	}
}
