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
		this.setPreferredSize(new Dimension(w, h));// �в���ʱ����
		this.setSize(new Dimension(w, h));// ����Ϊnullʱ����
		this.setBorder(new LineBorder(c));
	}
}
