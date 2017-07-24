package pers.leo.myPanel;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import pers.leo.colorPicker.ColorGetter;

public class ColorsPanel extends MyPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color mainColor;
	private Point mainPoint;
	private ColorGetter cg;
	private static Color lineColor = Color.white;

	public ColorsPanel() throws AWTException {
		this(111, 111, lineColor);
	}

	public ColorsPanel(int w, int h) throws AWTException {
		this(w, h, lineColor);
	}

	public ColorsPanel(int w, int h, Color c) throws AWTException {
		super(w, h, c);
		this.setMainPoint(new Point());
		cg = new ColorGetter();
		mainColor = cg.pickColor();
	}

	public Color getMainColor() {
		return mainColor;
	}

	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}

	public Point getMainPoint() {
		return mainPoint;
	}

	public void setMainPoint(Point point) {
		this.mainPoint = point;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 设置抗锯齿
		/* 将距鼠标5个像素距离的范围绘制到面板 */
		int radius = 5;
		int x = mainPoint.x - radius;
		int y = mainPoint.y - radius;
		int count = 2 * radius + 1;
		g.drawImage(cg.getScreenCapture(x, y, count, count), 0, 0, getWidth(), getHeight(), this);
		/* 画网格线 */
		g.setColor(lineColor);
		for (int i = 0; i <= 10; i++) {
			g.drawLine(i * getWidth() / count, 0, i * getWidth() / count, getHeight());
			g.drawLine(0, i * getHeight() / count, getWidth(), i * getHeight() / count);
		}
		/* 画中心即鼠标处的红色方框 */
		g.setColor(Color.red);
		g.drawRect(radius * getWidth() / count, radius * getHeight() / count, getWidth() / count, getHeight() / count);
		mainColor = cg.pickColor();// 设置主颜色
	}
}
