package pers.leo.myPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyPanel() {

	}

	public MyPanel(int w, int h, Color c) {
		this.setPreferredSize(new Dimension(w, h));// 有布局时可用
		this.setSize(new Dimension(w, h));// 布局为null时可用
		this.setBorder(new LineBorder(c));
	}
}
