package pers.leo.myPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import pers.leo.myButton.MyButton;

public class ColorInfoPanel extends MyPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField jtf1, jtf2, jtfRe1, jtfRe2;
	private JButton copy1, copy2;
	private JButton pickBtn;
	private JCheckBox jchk;
	private JLabel jlXnow = new JLabel();
	private JLabel jlYnow = new JLabel();

	public ColorInfoPanel(int w, int h, Color c) {
		super(w, h, c);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
		Font font = new Font("微软雅黑", Font.PLAIN, 12);
		jtf1 = new MyTextField(10, 16);
		jtf2 = new MyTextField(10, 16);
		setCopy1(new MyButton("复制", font));
		setCopy2(new MyButton("复制", font));
		JLabel jl = new JLabel("补色");
		jl.setFont(font);
		jtfRe1 = new MyTextField(10, 12);
		jtfRe2 = new MyTextField(6, 12);
		setPickBtn(new MyButton("拾取颜色", font));
		setJchk(new JCheckBox("取色时隐藏"), font);
		JLabel jlTip = new JLabel("点击‘拾取颜色’按钮即可取色");
		jlTip.setFont(font);
		JLabel jlP = new JLabel("当前位置：");
		jlP.setFont(font);
		JLabel jlX = new JLabel("X");
		JLabel jlY = new JLabel("Y");
		add(jtf1);
		add(getCopy1());
		add(jtf2);
		add(getCopy2());
		add(jl);
		add(jtfRe1);
		add(jtfRe2);
		add(getPickBtn());
		add(getJchk());
		add(jlTip);
		add(jlP);
		add(jlX);
		add(getJlXnow());
		add(jlY);
		add(getJlYnow());
		getCopy1().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable text = new StringSelection(jtf1.getText());
				clip.setContents(text, null);
			}

		});
		getCopy2().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable text = new StringSelection(jtf2.getText());
				clip.setContents(text, null);
			}

		});
	}

	public JButton getCopy1() {
		return copy1;
	}

	public void setCopy1(JButton copy1) {
		this.copy1 = copy1;
	}

	public JButton getCopy2() {
		return copy2;
	}

	public void setCopy2(JButton copy2) {
		this.copy2 = copy2;
	}

	public String getJtf1Text() {
		return jtf1.getText();
	}

	public void setJtf1Text(String s, Color color, Color rColor) {
		jtf1.setText(s);
		jtf1.setBackground(color);
		jtf1.setForeground(rColor);
	}

	public String getJtf2Text() {
		return jtf2.getText();
	}

	public void setJtf2Text(String s, Color color, Color rColor) {
		jtf2.setText(s);
		jtf2.setBackground(color);
		jtf2.setForeground(rColor);
	}

	public void setJtfRe1Text(String s, Color color, Color rColor) {
		jtfRe1.setText(s);
		jtfRe1.setBackground(color);
		jtfRe1.setForeground(rColor);
	}

	public void setJtfRe2Text(String s, Color color, Color rColor) {
		jtfRe2.setText(s);
		jtfRe2.setBackground(color);
		jtfRe2.setForeground(rColor);
	}

	public void setJtfText(String text1, String text2, String rText1, String rText2, Color color, Color rColor) {
		setJtf1Text(text1, color, rColor);
		setJtf2Text(text2, color, rColor);
		setJtfRe1Text(rText1, rColor, color);
		setJtfRe2Text(rText2, rColor, color);
	}

	public JButton getPickBtn() {
		return pickBtn;
	}

	public void setPickBtn(JButton pickBtn) {
		this.pickBtn = pickBtn;
	}

	public JCheckBox getJchk() {
		return jchk;
	}

	public void setJchk(JCheckBox jchk, Font font) {
		this.jchk = jchk;
		if (font != null) {
			this.jchk.setFont(font);
		}
	}

	public JLabel getJlXnow() {
		return jlXnow;
	}

	public void setJlXnow(JLabel jlXnow) {
		this.jlXnow = jlXnow;
	}

	public JLabel getJlYnow() {
		return jlYnow;
	}

	public void setJlYnow(JLabel jlYnow) {
		this.jlYnow = jlYnow;
	}

	class MyTextField extends JTextField {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Font ft;

		public MyTextField(int w, int fs) {
			super(w);
			ft = new Font("TimesRoman", Font.BOLD, fs);
			setFont(ft);
			setBorder(null);
		}

		public void setMyFont(Font ft) {
			setFont(ft);
		}
	}
}
