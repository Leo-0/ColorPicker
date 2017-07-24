package pers.leo.myPanel;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;

public class ColorPanelContainer extends MyPanel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private PaintPanel pp;
	private ColorsPanel cp;

	public ColorPanelContainer() {

	}

	public ColorPanelContainer(int w, int h, Color c) throws AWTException {
		super(w, h, c);
		setLayout(new BorderLayout(2, 2));
		setPp(new PaintPanel(w, h / 2 - 2, c));
		setCp(new ColorsPanel(w, h / 2 - 2, c));
		add(getPp(), BorderLayout.NORTH);
		add(getCp(), BorderLayout.SOUTH);
	}

	public PaintPanel getPp() {
		return pp;
	}

	public void setPp(PaintPanel pp) {
		this.pp = pp;
	}

	public ColorsPanel getCp() {
		return cp;
	}

	public void setCp(ColorsPanel cp) {
		this.cp = cp;
	}
}
