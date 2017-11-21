package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestInfo extends JFrame {

	private JPanel contentPane;
	private String ip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestInfo frame = new TestInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestInfo() {
		setResizable(false);
		this.ip = ip;
		addWindowListener(new winAdapter());
		
		setBounds(100, 100, 343, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel iconlabel = new JLabel("New label");
		iconlabel.setBackground(Color.MAGENTA);
		iconlabel.setBounds(31, 68, 120, 115);
		contentPane.add(iconlabel);
		
		JLabel namelabel = new JLabel("\u540D\u5B57");
		namelabel.setBounds(159, 68, 164, 35);
		contentPane.add(namelabel);
		
		JButton messagebtn = new JButton("\u53D1\u6D88\u606F");
		messagebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chatmanager.chat.getIp(ip);
				this.dispose();
			}
		});
		messagebtn.setBounds(178, 322, 120, 40);
		contentPane.add(messagebtn);
		
		JButton resumebtn = new JButton("\u8FD4\u56DE");
		resumebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				this.dispose();
			}
		});
		resumebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		resumebtn.setBounds(38, 322, 113, 40);
		contentPane.add(resumebtn);
		
		JTextArea udwjta = new JTextArea();
		udwjta.setEditable(false);
		udwjta.setText("\u4ED6\u5F88\u61D2\u6CA1\u6709\u7B7E\u540D\u54E6\u3002\u3002\u3002\u3002\u3002\u3002");
		udwjta.setWrapStyleWord(true);
		udwjta.setLineWrap(true);
		udwjta.setBounds(159, 115, 115, 55);
		contentPane.add(udwjta);
	}
	class winAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent windowEvent) {
			this.dispose();
			System.exit(0);
		}
	}
}
