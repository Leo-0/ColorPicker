package pers.leo.myButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseButton extends MyButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CloseButton() {
		addActionListener(this);
	}

	public CloseButton(String text) {
		super(text);
		addActionListener(this);
	}

	public CloseButton(String text, Font font) {
		super(text, font);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
