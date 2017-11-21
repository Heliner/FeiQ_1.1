package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import Main.Main;
import Manager.UserManager;
import Object.User;

public class Connect extends JFrame {
	// JDBC �����������ݿ� URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/Feiq";
	// ���ݿ���û��������룬��Ҫ�����Լ�������
	static final String USER = "root";
	static final String PASS = "h303567469";
	static Connection conn = null;
	static {
		try {
			// ע�� JDBC ����
			Class.forName("com.mysql.jdbc.Driver");
			// ������
			System.out.println("�������ݿ�...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// ִ�в�ѯ
			System.out.println(" ʵ����Statement��...");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private Connect c;
	JFrame MainFrame;
	// �ļ���ȡ�û���Ϣ
	public Connect() {
		c = this;
		MainFrame = this;
		initframe();
	}

	private void initframe() {
		setTitle("��½");
		
		c = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 327);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 120, 286);
		contentPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("����");
		label_1.setBounds(39, 136, 102, 37);
		label_1.setOpaque(false);
		panel.add(label_1);

		JLabel label_2 = new JLabel("�˺�");
		label_2.setBounds(39, 81, 87, 27);
		label_2.setOpaque(false);
		panel.add(label_2);

		JButton button_1 = new JButton("ע��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			Regist rg = new Regist(MainFrame,conn);
			}
		});
		button_1.setBounds(153, 237, 79, 27);
		button_1.setOpaque(false);
		contentPane.add(button_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(153, 142, 191, 25);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setBounds(153, 86, 191, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		JButton button = new JButton("��¼");
		button.setOpaque(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println((passwordField.getPassword()).toString());
				System.out.println(textField.getText());
				try {
					c.connect(textField.getText(), passwordField.getText());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(259, 237, 79, 27);
		contentPane.add(button);
		this.setVisible(true);
		this.setResizable(false);
	}

	// ���������ȷ���ʹ���User���󲢴���Client��
	public void connect(String count, String pwd) throws UnknownHostException {
		try {
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT count, pwd ,name ,sex,underwrite,address FROM User";
			// stmt.executeUpdate("INSERT INTO User VALUES (1234,1235,'hf','��','','')");
			// stmt.executeUpdate("delete FROM User WHERE count = 1234");
			ResultSet rs = stmt.executeQuery(sql);
			// չ����������ݿ�
			while (rs.next()) {
				// ͨ���ֶμ���
				String count2 = rs.getString("count");
				String pwd2 = rs.getString("pwd");
				if (count.equals(count2) && pwd2.equals(pwd)) {
					UserManager.addUser(InetAddress.getLocalHost().getHostAddress().toString(), rs.getString("name"),
							rs.getString("sex"), rs.getString("underwrite"), rs.getString("address"));
					
					// ��ɺ�ر�
					rs.close();
					stmt.close();
					conn.close();
					

					break;
				}
				// �������
				System.out.print("ID: " + count);
				System.out.print(", վ������: " + pwd);
				System.out.print("\n");
			}
			if(!rs.isClosed()) {
				textField.setText("");
				passwordField.setText("");
				JOptionPane.showMessageDialog(null, "�˺Ż������д���!");
			}else {
				JOptionPane.showMessageDialog(null, "��½�ɹ�");
				this.dispose();
				Client client = new Client();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
