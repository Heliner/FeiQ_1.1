package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Manager.ChatManager;
import Manager.UserManager;
import Object.User;

public class Info extends JFrame {
	private JPanel contentPane;
	private String ip;
	private Info info;

	public Info(String ip) {
		setAlwaysOnTop(true);
		this.ip = ip;
		this.info = this;
		init();
	}

	
	private void init() {
		User u  = UserManager.getUser(ip);
		String ip = u.getIp();
		String name  = u.getName();
		String address = u.getAddress();
		String udw = u.getUnderwrite();
		String sex =u.getSex();
		u = null;
		
		setVisible(true);
		addWindowListener(new winAdapter());

		setBounds(100, 100, 355, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel iconlabel = new JLabel(new ImageIcon("Images/qqhead.jpg"));
		iconlabel.setBackground(Color.MAGENTA);
		iconlabel.setBounds(31, 68, 120, 115);
		contentPane.add(iconlabel);

		
		JLabel namelabel = new JLabel();
		namelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 18));
		namelabel.setBounds(159, 68, 164, 35);
		contentPane.add(namelabel);

		JButton messagebtn = new JButton("\u53D1\u6D88\u606F");
		messagebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatManager.getChat(ip);
				info.dispose();
			}
		});
		messagebtn.setBounds(178, 322, 120, 40);
		contentPane.add(messagebtn);

		JButton resumebtn = new JButton("\u8FD4\u56DE");
		resumebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				info.dispose();
			}
		});
		resumebtn.setBounds(38, 322, 113, 40);
		contentPane.add(resumebtn);

		JTextArea udwjta = new JTextArea();
		udwjta.setEditable(false);
		udwjta.setText("\u4ED6\u5F88\u61D2\u6CA1\u6709\u7B7E\u540D\u54E6\u3002\u3002\u3002\u3002\u3002\u3002");
		udwjta.setWrapStyleWord(true);
		udwjta.setLineWrap(true);
		udwjta.setBounds(159, 115, 164, 68);
		contentPane.add(udwjta);

		
	}

	class winAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent windowEvent) {
			info.dispose();
		}
	}
}