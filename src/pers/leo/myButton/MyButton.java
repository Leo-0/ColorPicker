package pers.leo.myButton;

import java.awt.Font;

import javax.swing.JButton;

public class MyButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyButton() {

	}

	public MyButton(String text) {
		super(text);
		this.setFocusPainted(false);
	}

	public MyButton(String text, Font font) {
		this(text);
		setFont(font);
	}
}
