package pers.leo.myPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PaintPanel extends MyPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color faceColor = Color.gray;
	private int xCenter;
	private int yCenter;
	private int radius;
	private int xLeftEye;
	private int yLeftEye;
	private int xRightEye;
	private int yRightEye;
	private int eyesOutterSize;
	private int xInnerLeftEye;
	private int yInnerLeftEye;
	private int xInnerRightEye;
	private int yInnerRightEye;
	private int eyesInnerSize;

	public PaintPanel(int w, int h, Color c) {
		super(w, h, c);
		this.xCenter = getWidth() / 2;
		this.yCenter = getHeight() / 2;
		this.radius = (int) (Math.min(getWidth(), getHeight()) * 0.45);
		this.setxLeftEye((int) (xCenter - radius * 0.4));
		this.setyLeftEye((int) (yCenter - radius * 0.35));
		this.setxRightEye((int) (xCenter + radius * 0.4));
		this.setyRightEye((int) (yCenter - radius * 0.35));
		this.setEyesOutterSize((int) (radius * 0.35));
		this.setxInnerLeftEye(getxLeftEye());
		this.setyInnerLeftEye(getyLeftEye());
		this.setxInnerRightEye(getxRightEye());
		this.setyInnerRightEye(getyRightEye());
		this.setEyesInnerSize(getEyesOutterSize() / 2);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// ¿¹¾â³Ý
		// draw face
		g.setColor(faceColor);
		drawCircle(g, xCenter, yCenter, radius, true);
		// draw eyes
		g.setColor(Color.WHITE);
		drawCircle(g, getxLeftEye(), getyLeftEye(), getEyesOutterSize(), true);
		drawCircle(g, getxRightEye(), getyRightEye(), getEyesOutterSize(), true);
		g.setColor(Color.BLACK);
		drawCircle(g, getxInnerLeftEye(), getyInnerLeftEye(), getEyesInnerSize(), true);
		drawCircle(g, getxInnerRightEye(), getyInnerRightEye(), getEyesInnerSize(), true);
	}

	public Color getFaceColor() {
		return faceColor;
	}

	public void setFaceColor(Color faceColor) {
		this.faceColor = faceColor;
	}

	public int getxInnerLeftEye() {
		return xInnerLeftEye;
	}

	public void setxInnerLeftEye(int xInnerLeftEye) {
		this.xInnerLeftEye = xInnerLeftEye;
	}

	public int getyInnerLeftEye() {
		return yInnerLeftEye;
	}

	public void setyInnerLeftEye(int yInnerLeftEye) {
		this.yInnerLeftEye = yInnerLeftEye;
	}

	public int getxInnerRightEye() {
		return xInnerRightEye;
	}

	public void setxInnerRightEye(int xInnerRightEye) {
		this.xInnerRightEye = xInnerRightEye;
	}

	public int getyInnerRightEye() {
		return yInnerRightEye;
	}

	public void setyInnerRightEye(int yInnerRightEye) {
		this.yInnerRightEye = yInnerRightEye;
	}

	public int getEyesOutterSize() {
		return eyesOutterSize;
	}

	public void setEyesOutterSize(int eyesOutterSize) {
		this.eyesOutterSize = eyesOutterSize;
	}

	public int getEyesInnerSize() {
		return eyesInnerSize;
	}

	public void setEyesInnerSize(int eyesInnerSize) {
		this.eyesInnerSize = eyesInnerSize;
	}

	public int getxLeftEye() {
		return xLeftEye;
	}

	public void setxLeftEye(int xLeftEye) {
		this.xLeftEye = xLeftEye;
	}

	public int getyLeftEye() {
		return yLeftEye;
	}

	public void setyLeftEye(int yLeftEye) {
		this.yLeftEye = yLeftEye;
	}

	public int getxRightEye() {
		return xRightEye;
	}

	public void setxRightEye(int xRightEye) {
		this.xRightEye = xRightEye;
	}

	public int getyRightEye() {
		return yRightEye;
	}

	public void setyRightEye(int yRightEye) {
		this.yRightEye = yRightEye;
	}

	// ¸ù¾ÝÔ²ÐÄ×ø±ê¼°°ë¾¶»­Ô²
	private void drawCircle(Graphics g, int x, int y, int r, boolean filled) {
		if (filled) {
			// g.fillArc(x - r, y - r, 2 * r, 2 * r, 0, 360);
			g.fillOval(x - r, y - r, 2 * r, 2 * r);
		} else {
			// g.drawArc(x - r, y - r, 2 * r, 2 * r, 0, 360);
			g.drawOval(x - r, y - r, 2 * r, 2 * r);
		}
	}
}
