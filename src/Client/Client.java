package Client;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Manager.ChatManager;
import Manager.UserManager;
import Object.Heading;
import Object.User;
import Recive.InfoRecive;
import Send.SendMySelf;
import javax.swing.JButton;

/**
 * 包含好友列表的客户端窗口
 * 
 * @author hf
 *
 *
 */
public class Client extends JFrame {
	private JPanel contentPane;
	JPanel jphy1;
	JPanel jphy2;
	JScrollPane jsp1;
	JPanel panel_1;
	JLabel[] jbls;
	static ImageIcon qqhead = new ImageIcon("Images/qqhead.jpg");
	public Client() throws UnknownHostException {
		setResizable(false);
		InfoRecive re = new InfoRecive(this);// ------------
		Thread th = new Thread(re);
		init();
		th.start();
		this.EntryMessageSend();
	}

	private void init() throws UnknownHostException {

		// ------------------------
		// 实现界面后点击东西实现初始化一个chat-------------
		this.setTitle("FeiQ");
		this.addWindowListener(new winAdapter());
		this.setVisible(true);
		setBounds(100, 100, 453, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel IP = new JLabel("IP\uFF1A");
		IP.setFont(new Font("宋体", Font.PLAIN, 25));
		GridBagConstraints gbc_IP = new GridBagConstraints();
		gbc_IP.gridwidth = 3;
		gbc_IP.insets = new Insets(0, 0, 5, 5);
		gbc_IP.gridx = 5;
		gbc_IP.gridy = 1;
		contentPane.add(IP, gbc_IP);

		JLabel ipaddress = new JLabel("ip");
		GridBagConstraints gbc_ipaddress = new GridBagConstraints();
		gbc_ipaddress.insets = new Insets(0, 0, 5, 0);
		gbc_ipaddress.gridx = 9;
		gbc_ipaddress.gridy = 1;
		contentPane.add(ipaddress, gbc_ipaddress);
		ipaddress.setText(UserManager.getUser(InetAddress.getLocalHost().getHostAddress()).getIp());
		ipaddress.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("Images/qqhead.jpg"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 4;
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel uwlabel = new JLabel("\u7B7E\u540D\uFF1A");
		GridBagConstraints gbc_uwlabel = new GridBagConstraints();
		gbc_uwlabel.gridwidth = 3;
		gbc_uwlabel.gridheight = 2;
		gbc_uwlabel.insets = new Insets(0, 0, 5, 5);
		gbc_uwlabel.gridx = 5;
		gbc_uwlabel.gridy = 2;
		contentPane.add(uwlabel, gbc_uwlabel);
		uwlabel.setFont(new Font("宋体", Font.PLAIN, 25));
		
		JTextArea uwtextArea = new JTextArea();
		uwtextArea.setEditable(false);
		uwtextArea.setText("12134");
		GridBagConstraints gbc_uwtextArea = new GridBagConstraints();
		gbc_uwtextArea.gridheight = 2;
		gbc_uwtextArea.insets = new Insets(0, 0, 5, 0);
		gbc_uwtextArea.fill = GridBagConstraints.BOTH;
		gbc_uwtextArea.gridx = 9;
		gbc_uwtextArea.gridy = 2;
		contentPane.add(uwtextArea, gbc_uwtextArea);
		uwtextArea.setFont(new Font("宋体", Font.PLAIN, 20));
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 10;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 6;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton refreshbtn = new JButton("刷新");
		refreshbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Thread(new SendMySelf(Heading.WHOONLINE)).start();
				update();
			}
		});
		GridBagConstraints gbc_refreshbtn = new GridBagConstraints();
		gbc_refreshbtn.gridx = 9;
		gbc_refreshbtn.gridy = 7;
		contentPane.add(refreshbtn, gbc_refreshbtn);
		UserManager.addUser("255.255.255.255");

		update();

	}

	/**
	 * 根据User.users来刷新
	 */
	public void update() {
		jphy2 = new JPanel();
		jphy2.setLayout(new GridLayout(20, 1));
		jbls = new JLabel[20];
		// for (int i = 0; i < 30; i++) {
		// jbls[i] = new JLabel("");
		// jbls[i].setEnabled(false);
		// }

		Iterator<User> it = UserManager.users.iterator();

		int i = 0;

		while (it.hasNext()) {
			String ip = ((User) it.next()).getIp();
			jbls[i] = new JLabel(ip, qqhead, JLabel.LEFT);
			jbls[i].setFont(new Font("宋体", Font.PLAIN, 30));
			jbls[i].setEnabled(true);
			// jbls[i].setText();
			System.out.println("初始化列表完成");
			jbls[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//creatchatwind123ow(ip);
					creatinfowindow(ip);
				}

			});
			jphy2.add(jbls[i]);
			i++;
			if (i > 10)
				break;

		}
		jsp1 = new JScrollPane(jphy2);
		panel_1.removeAll();
		// panel_1.add(jsp1, BorderLayout.CENTER);
		panel_1.add(jsp1, BorderLayout.CENTER);

		panel_1.validate();// ------------------
		this.setVisible(true);
		System.gc();

	}



	public void EntryMessageSend() {
		SendMySelf sms = new SendMySelf(Heading.WHOONLINE);
		Thread th = new Thread(sms);
		th.start();
	}

	/**
	 * 离线消息发送
	 */
	public void OfflineMessageSend() {
		SendMySelf sms = new SendMySelf(Heading.OFFLINE);
		Thread th = new Thread(sms);
		th.start();
	}

	public void creatchatwindow(String ip) {
		ChatManager.getChat(ip);
	}
	
	protected void creatinfowindow(String ip) {
		Info info = new Info(ip);
	}
	class winAdapter extends WindowAdapter {
		
		public void windowClosing(WindowEvent windowEvent) {
			OfflineMessageSend();
			// 保存修改的文件然后退出
			// User u =User.users.get(0);

			System.exit(0);
		}
	}

}