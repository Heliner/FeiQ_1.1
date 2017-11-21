package Test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class FriendView extends JFrame implements ActionListener, MouseListener {
	// 背景图层
	ImageIcon background;
	JPanel buttom;
	JLabel imgLabel;
	JButton jb;

	// 上层北边
	JLabel head, name, sign;

	// 上层南边
	JPanel jp;// 卡片布局
	CardLayout cl;

	// 第一张卡片
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	// 第二张卡片
	JPanel jp2;
	JScrollPane jsp;
	JPanel jp_jsp;// 用来放jsp
	JButton jp2_jb1, jp2_jb2, jp2_jb3;

	// 第三张卡片
	JPanel jp3;
	JScrollPane jsp2;
	JPanel jp_jsp2;// 用来放jsp2
	JButton jp3_jb1, jp3_jb2, jp3_jb3;

	// 第四张卡片
	JPanel jp4;
	JScrollPane jsp3;
	JPanel jp_jsp3;
	JButton jp4_jb1, jp4_jb2, jp4_jb3;

	// 构造函数
	public FriendView() {
		// 处理背景
		backGround();

		// 处理北边的东西（头像，昵称，签名）
		head = new JLabel(new ImageIcon("Images/qqhead.jpg"));
		head.setBounds(10, 40, 50, 50);
		name = new JLabel("小Q机器人");
		name.setBounds(70, 42, 80, 20);
		name.setFont(new Font("宋体", Font.BOLD, 16));
		name.setForeground(Color.white);
		sign = new JLabel("个性签名");
		sign.setBounds(70, 70, 80, 20);
		sign.setForeground(Color.white);

		// 设置好友列表为卡片布局
		cl = new CardLayout();
		jp = new JPanel();
		jp.setOpaque(false);
		jp.setBounds(0, 205, background.getIconWidth(), background.getIconHeight());

		// 处理第一张卡片
		firstCard();
		// 处理第二张卡片
		secondCard();
		// 处理第三张卡片
		thirdCard();
		// 处理第四张卡片
		fourthCard();

		this.add(head);
		this.add(name);
		this.add(sign);
		jp.setLayout(cl);
		jp.add(jp1, "1");
		jp.add(jp2, "2");
		jp.add(jp3, "3");
		jp.add(jp4, "4");
		this.add(jp);
		this.getLayeredPane().setLayout(null);
		this.setLayout(null);
		this.setSize(283, 720);
		this.setLocation(800, 30);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 处理背景方法
	public void backGround() {
		background = new ImageIcon("Images/bg.jpg");
		imgLabel = new JLabel(background);
		imgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		buttom = (JPanel) this.getContentPane();
		// 将contentPane设置为透明的
		buttom.setOpaque(false);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	}

	// 处理第一张卡片方法
	public void firstCard() {
		jp1 = new JPanel();

		jp1_jb1 = new JButton("> 我的好友");
		jp1_jb1.addActionListener(this);
		jp1_jb1.setLayout(null);
		jp1_jb1.setSize(277, 35);
		jp1_jb1.setHorizontalAlignment(SwingConstants.LEFT);

		jp1_jb2 = new JButton("> 陌生人");
		jp1_jb2.addActionListener(this);
		jp1_jb2.setLayout(null);
		jp1_jb2.setBounds(0, 35, 277, 35);
		jp1_jb2.setHorizontalAlignment(SwingConstants.LEFT);

		jp1_jb3 = new JButton("> 黑名单");
		jp1_jb3.addActionListener(this);
		jp1_jb3.setLayout(null);
		jp1_jb3.setBounds(0, 70, 277, 35);
		jp1_jb3.setHorizontalAlignment(SwingConstants.LEFT);

		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		jp1.setLayout(null);
		jp1.setOpaque(false);
	}

	// 处理第二张卡片方法
	public void secondCard() {
		jp2 = new JPanel();

		jp2_jb1 = new JButton("↓ 我的好友");
		jp2_jb1.addActionListener(this);
		jp2_jb1.setLayout(null);
		jp2_jb1.setSize(277, 35);
		jp2_jb1.setHorizontalAlignment(SwingConstants.LEFT);

		jp2_jb2 = new JButton("> 陌生人");
		jp2_jb2.addActionListener(this);
		jp2_jb2.setLayout(null);
		jp2_jb2.setBounds(0, 354, 277, 35);
		jp2_jb2.setHorizontalAlignment(SwingConstants.LEFT);

		jp2_jb3 = new JButton("> 黑名单");
		jp2_jb3.addActionListener(this);
		jp2_jb3.setLayout(null);
		jp2_jb3.setBounds(0, 389, 277, 35);
		jp2_jb3.setHorizontalAlignment(SwingConstants.LEFT);

		// 假定30个好友
		jp_jsp = new JPanel(new GridLayout(30, 1));
		jsp = new JScrollPane(jp_jsp);

		// 初始化30个好友
		JLabel[] jbls = new JLabel[30];
		for (int i = 0; i < jbls.length; i++) {
			jbls[i] = new JLabel(i + 1 + "号机器人", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp.add(jbls[i]);
		}

		jsp.setBounds(1, 35, 275, 319);

		// jsp.setLayout(null);错误！，jsp本来就没有布局
		jp2.add(jsp);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jb2);
		jp2.add(jp2_jb3);
		jp2.setLayout(null);
		jp2.setOpaque(false);
	}

	// 处理第三张卡片方法
	public void thirdCard() {
		jp3 = new JPanel();

		jp3_jb1 = new JButton("> 我的好友");
		jp3_jb1.addActionListener(this);
		jp3_jb1.setLayout(null);
		jp3_jb1.setSize(277, 35);
		jp3_jb1.setHorizontalAlignment(SwingConstants.LEFT);

		jp3_jb2 = new JButton("↓ 陌生人");
		jp3_jb2.addActionListener(this);
		jp3_jb2.setLayout(null);
		jp3_jb2.setBounds(0, 35, 277, 35);
		jp3_jb2.setHorizontalAlignment(SwingConstants.LEFT);

		jp3_jb3 = new JButton("> 黑名单");
		jp3_jb3.addActionListener(this);
		jp3_jb3.setLayout(null);
		jp3_jb3.setBounds(0, 389, 277, 35);
		jp3_jb3.setHorizontalAlignment(SwingConstants.LEFT);

		// 假定30个好友
		jp_jsp2 = new JPanel(new GridLayout(10, 1));
		jsp2 = new JScrollPane(jp_jsp2);

		// 初始化30个好友
		JLabel[] jbls = new JLabel[10];
		for (int i = 0; i < jbls.length; i++) {
			jbls[i] = new JLabel(i + 1 + "号陌生人", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp2.add(jbls[i]);
		}

		jsp2.setBounds(1, 70, 275, 319);

		jp3.add(jsp2);
		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);
		jp3.add(jp3_jb3);
		jp3.setLayout(null);
		jp3.setOpaque(false);
	}

	// 处理第四张卡片方法
	public void fourthCard() {
		jp4 = new JPanel();

		jp4_jb1 = new JButton("> 我的好友");
		jp4_jb1.addActionListener(this);
		jp4_jb1.setLayout(null);
		jp4_jb1.setSize(277, 35);
		jp4_jb1.setHorizontalAlignment(SwingConstants.LEFT);

		jp4_jb2 = new JButton("> 陌生人");
		jp4_jb2.addActionListener(this);
		jp4_jb2.setLayout(null);
		jp4_jb2.setBounds(0, 35, 277, 35);
		jp4_jb2.setHorizontalAlignment(SwingConstants.LEFT);

		jp4_jb3 = new JButton("↓ 黑名单");
		jp4_jb3.addActionListener(this);
		jp4_jb3.setLayout(null);
		jp4_jb3.setBounds(0, 70, 277, 35);
		jp4_jb3.setHorizontalAlignment(SwingConstants.LEFT);

		// 假定30个好友
		jp_jsp3 = new JPanel(new GridLayout(10, 1));
		jsp3 = new JScrollPane(jp_jsp3);

		// 初始化30个好友
		JLabel[] jbls = new JLabel[5];
		for (int i = 0; i < jbls.length; i++) {
			jbls[i] = new JLabel(i + 1 + "号黑名单", new ImageIcon("Images/qqhead.jpg"), JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jp_jsp3.add(jbls[i]);
		}

		jsp3.setBounds(1, 105, 275, 319);

		jp4.add(jsp3);
		jp4.add(jp4_jb1);
		jp4.add(jp4_jb2);
		jp4.add(jp4_jb3);
		jp4.setLayout(null);
		jp4.setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 第一张卡片的按钮
		if (e.getSource() == jp1_jb1) {
			cl.show(jp, "2");
			;
		}
		if (e.getSource() == jp1_jb2) {
			cl.show(jp, "3");
			;
		}
		if (e.getSource() == jp1_jb3) {
			cl.show(jp, "4");
			;
		}

		// 第二张卡片的按钮
		if (e.getSource() == jp2_jb1) {
			cl.show(jp, "1");
			;
		}
		if (e.getSource() == jp2_jb2) {
			cl.show(jp, "3");
			;
		}
		if (e.getSource() == jp2_jb3) {
			cl.show(jp, "4");
			;
		}

		// 第三张卡片的按钮
		if (e.getSource() == jp3_jb1) {
			cl.show(jp, "2");
			;
		}
		if (e.getSource() == jp3_jb2) {
			cl.show(jp, "1");
			;
		}
		if (e.getSource() == jp3_jb3) {
			cl.show(jp, "4");
			;
		}

		// 第四张卡片的按钮
		if (e.getSource() == jp4_jb1) {
			cl.show(jp, "2");
			;
		}
		if (e.getSource() == jp4_jb2) {
			cl.show(jp, "3");
			;
		}
		if (e.getSource() == jp4_jb3) {
			cl.show(jp, "1");
			;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			String str = ((JLabel) e.getSource()).getText();
			System.out.println("你希望和" + str + "聊天。");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);
	}

}