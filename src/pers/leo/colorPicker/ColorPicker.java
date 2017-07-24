package pers.leo.colorPicker;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pers.leo.myButton.CloseButton;
import pers.leo.myButton.MyButton;
import pers.leo.myPanel.ColorInfoPanel;
import pers.leo.myPanel.ColorPanelContainer;
import pers.leo.myPanel.ColorsPanel;
import pers.leo.myPanel.PaintPanel;

public class ColorPicker extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private int x, y;
	private Point p = new Point();
	private int width = 500;
	private int height = 300;
	private int lWidth = 250;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	private boolean show = true;
	private boolean isFull = false;
	private final Color color1 = new Color(255, 255, 255, 0);
	private final Color color2 = new Color(255, 255, 255, 1);

	public ColorPicker() throws AWTException {
		this("/icon/image/color_picker_32.png", "/icon/image/color_picker.png", false);
	}

	public ColorPicker(String icon, String trayIcon, boolean customCursor) throws AWTException {
		showUI();
		if (customCursor) {
			URL url = this.getClass().getResource("/icon/image/circle.png"); // 储存鼠标图片的位置
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image image = new ImageIcon(url).getImage();
			Cursor cursor = tk.createCustomCursor(image, new Point(16, 16), "pick");
			setCursor(cursor);
		}
		URL iconURL = this.getClass().getResource(icon);
		URL trayIconURL = this.getClass().getResource(trayIcon);
		Image iconImg = new ImageIcon(iconURL).getImage();
		Image trayIconImg = new ImageIcon(trayIconURL).getImage();
		setIconImage(iconImg);
		SystemTray st = SystemTray.getSystemTray();// 获得系统托盘图标实例
		PopupMenu popup = new PopupMenu();// 新建右键菜单对象
		/* 添加右键菜单功能项 */
		MenuItem defaultItem1 = new MenuItem("打开");
		defaultItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.NORMAL);
				setVisible(true);
			}

		});
		popup.add(defaultItem1);
		MenuItem defaultItem2 = new MenuItem("退出");
		defaultItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		popup.add(defaultItem2);
		TrayIcon ti = new TrayIcon(trayIconImg, "屏幕取色器-双击打开", popup);// 新建托盘图标对象
		st.add(ti);
		ti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// if (e.getButton() == MouseEvent.BUTTON1)
				if (e.getClickCount() == 2)// 双击托盘窗口再现
					setExtendedState(JFrame.NORMAL);
				setVisible(true);
			}
		});
	}

	public void showUI() throws AWTException {
		setLayout(null);
		setUndecorated(true);
		setBackground(color1);
		setSize(dim.width, dim.height);
		x = (dim.width - width) / 2;
		y = (dim.height - height) / 2;
		setLocation(0, 0);
		container.setLayout(new BorderLayout(2, 2));
		container.setLocation(x, y);
		container.setSize(width, height);
		container.setBackground(Color.gray);
		container.setBorder(new LineBorder(Color.gray, 1));
		ColorPanelContainer lPanel = new ColorPanelContainer(lWidth - 2, height, Color.gray);
		ColorInfoPanel rPanel = new ColorInfoPanel(width - lWidth - 2, height, Color.gray);
		rPanel.getJlXnow().setText("" + MouseInfo.getPointerInfo().getLocation().x);
		rPanel.getJlYnow().setText("" + MouseInfo.getPointerInfo().getLocation().y);
		Font font = new Font("微软雅黑", Font.PLAIN, 12);
		MyButton minimizeBtn = new MyButton("最小化到托盘区", font);
		minimizeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}

		});
		rPanel.add(minimizeBtn);
		CloseButton closeBtn = new CloseButton("退出", font);
		rPanel.add(closeBtn);
		container.add(lPanel, BorderLayout.WEST);
		container.add(rPanel, BorderLayout.EAST);
		add(container);
		JButton pickBtn = rPanel.getPickBtn();
		pickBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fullScreen();
			}

		});
		JCheckBox jchk = rPanel.getJchk();
		jchk.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				show = !jchk.isSelected();
				if (isFull) {
					container.setVisible(show);
				}
			}

		});
		container.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				p.x = e.getX();
				p.y = e.getY();
			}
		});
		container.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int left = x;
				int top = y;
				container.setLocation(left + e.getX() - p.x, top + e.getY() - p.y);
				x = container.getLocation().x;
				y = container.getLocation().y;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				followMouse(lPanel.getPp(), e.getX(), e.getY(), false);
			}

		});
		ColorsPanel cp2 = new ColorsPanel(111, 111);
		cp2.setVisible(false);
		add(cp2);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				pickBtn.requestFocus();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				dispose();// 窗口最小化时dispose该窗口
			}
		});
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				rPanel.getJlXnow().setText("" + e.getX());
				rPanel.getJlYnow().setText("" + e.getY());
				ColorsPanel colorPanel = lPanel.getCp();
				colorPanel.setMainPoint(e.getPoint());
				colorPanel.repaint();
				Color color = colorPanel.getMainColor();
				Color rColor = reverseColor(color);
				String text1 = toRgb(color);
				String text2 = toHexColor(color);
				String text3 = toRgb(rColor);
				String text4 = toHexColor(rColor);
				if (!show) {
					color = cp2.getMainColor();
					rColor = reverseColor(color);
					text1 = toRgb(color);
					text2 = toHexColor(color);
					text3 = toRgb(rColor);
					text4 = toHexColor(rColor);
					cp2.setVisible(false);
				}
				rPanel.setJtfText(text1, text2, text3, text4, color, rColor);
				lPanel.getPp().setFaceColor(color);
				lPanel.getPp().repaint();
				recoverSize();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!show) {
					cp2.setVisible(true);
					cp2.setLocation(e.getX() + 15, e.getY() + 15);
					cp2.setMainPoint(e.getPoint());
					cp2.repaint();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				followMouse(lPanel.getPp(), e.getX(), e.getY(), true);
				rPanel.getJlXnow().setText("" + e.getX());
				rPanel.getJlYnow().setText("" + e.getY());
				Color color, rColor;
				String text1, text2, text3, text4;
				if (show) {
					ColorsPanel colorPanel = lPanel.getCp();
					colorPanel.setMainPoint(e.getPoint());
					colorPanel.repaint();
					color = colorPanel.getMainColor();
					rColor = reverseColor(color);
					text1 = toRgb(color);
					text2 = toHexColor(color);
					text3 = toRgb(rColor);
					text4 = toHexColor(rColor);
					rPanel.setJtfText(text1, text2, text3, text4, color, rColor);
					lPanel.getPp().setFaceColor(color);
					lPanel.getPp().repaint();
				} else {
					cp2.setVisible(true);
					int x = e.getX() + 126 > dim.width ? dim.width - 111 : e.getX() + 15;
					int y = e.getY() + 126 > dim.height ? dim.height - 111 : e.getY() + 15;
					if (e.getX() + 111 > dim.width && e.getY() + 111 > dim.height) {
						x = e.getX() - 126;
					}
					cp2.setLocation(x, y);
					cp2.setMainPoint(e.getPoint());
					cp2.repaint();
					color = cp2.getMainColor();
					rColor = reverseColor(color);
					text1 = toRgb(color);
					text2 = toHexColor(color);
					text3 = toRgb(rColor);
					text4 = toHexColor(rColor);
					rPanel.setJtfText(text1, text2, text3, text4, color, rColor);
					lPanel.getPp().setFaceColor(color);
					lPanel.getPp().repaint();
				}
			}
		});
		setVisible(true);
	}

	// 一开始jframe就是全屏，设置背景色的透明度模拟全屏（alpha值为1）鼠标事件就能触发了
	private void fullScreen() {
		this.setBackground(color2);
		container.setVisible(show);
		isFull = true;
	}

	// 背景色透明度为0，就能点击到屏幕其他位置
	private void recoverSize() {
		this.setBackground(color1);
		container.setVisible(true);
		isFull = false;
	}

	// 转换成RGB(R,G,B)形式
	private String toRgb(Color color) {
		return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
	}

	// 转换成十六进制颜色值
	private String toHexColor(Color color) {
		String hexColor = "";
		// hexColor = "#" + Integer.toHexString(color.getRed()) +
		// Integer.toHexString(color.getGreen())
		// + Integer.toHexString(color.getBlue());
		hexColor = "#" + intToHex(color.getRed()) + intToHex(color.getGreen()) + intToHex(color.getBlue());
		return hexColor;
	}

	// 十进制转十六进制
	private String intToHex(int i) {
		String h = "";
		h = Integer.toHexString(i / 16).toUpperCase() + Integer.toHexString(i % 16).toUpperCase();
		return h;
	}

	// 计算补色
	private Color reverseColor(Color color) {
		int r = 255 - color.getRed();
		int g = 255 - color.getGreen();
		int b = 255 - color.getBlue();
		Color rc = new Color(r, g, b);
		return rc;
	}

	private void followMouse(PaintPanel paintPanel, int x, int y, boolean over) {
		int xLeft = paintPanel.getxLeftEye();
		int yLeft = paintPanel.getyLeftEye();
		int xRight = paintPanel.getxRightEye();
		int yRight = paintPanel.getyRightEye();
		int R = paintPanel.getEyesOutterSize();
		int r = paintPanel.getEyesInnerSize();
		double a = 0, b = 0;
		if (over) {
			a = Math.atan2((double) (y - yLeft - this.y), (double) (x - xLeft - this.x));
			b = Math.atan2((double) (y - yRight - this.y), (double) (x - xRight - this.x));
		} else {
			a = Math.atan2((double) (y - yLeft), (double) (x - xLeft));
			b = Math.atan2((double) (y - yRight), (double) (x - xRight));
		}
		int eyeX1 = Math.round((float) Math.cos(a) * (R - r) + xLeft);
		int eyeY1 = Math.round((float) Math.sin(a) * (R - r) + yLeft);
		int eyeX2 = Math.round((float) Math.cos(b) * (R - r) + xRight);
		int eyeY2 = Math.round((float) Math.sin(b) * (R - r) + yRight);
		paintPanel.setxInnerLeftEye(eyeX1);
		paintPanel.setyInnerLeftEye(eyeY1);
		paintPanel.setxInnerRightEye(eyeX2);
		paintPanel.setyInnerRightEye(eyeY2);
		paintPanel.repaint();
	}
}
