package Chat;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import Manager.ChatManager;
import Object.Heading;
import Send.FileSendThread;
import Send.MessageSend;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 聊天窗口 每个窗口有对应的ip
 * 
 * @author hf
 *
 */

public class Chat extends JFrame {
	private String ip;
	private JPanel jp;
	private JPanel jp1;
	private JTextArea ja1;
	private JTextArea ja2;
	private JButton jbexit;
	private JButton jbfile;
	private Chat chat;
	private ArrayList<String> MessageSummary = new ArrayList<String>();

	public Chat(String ip) {
		this.ip = ip;
		chat = this;
		init();
		// messageread();
	}

	/**
	 * 初始化基本的图形界面
	 */
	private void init() {

		this.setSize(816, 714);
		this.setVisible(true);
		this.addWindowListener(new winAdapter());
		this.setTitle("正在和" + ip + "  聊天");

		jp = new JPanel();

		jp.setVisible(true);
		jp.setSize(this.getWidth(), this.getWidth());
		jp.setLayout(null);

		ja1 = new JTextArea();
		ja1.setEditable(false);
		ja1.setRows(10);
		ja1.setColumns(10);
		ja1.setWrapStyleWord(true);
		ja1.setLineWrap(true);
		ja1.setCaretPosition(ja1.getText().length());

		ja2 = new JTextArea();
		ja2.setWrapStyleWord(true);
		ja2.setLineWrap(true);
		jbexit = new JButton("退出");
		jbfile = new JButton("文件");

		JScrollPane scrollPane = new JScrollPane(ja1);
		scrollPane.setBounds(0, 0, 798, 428);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jp1 = new JPanel();
		jp1.setBounds(0, 427, 798, 58);
		jp1.setLayout(null);

		ja2.setBounds(0, 482, 798, 129);
		jbfile.setBounds(0, 0, 99, 58);
		jbfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				chat.fileSend();
			}
		});
		ja1.setFont(new Font("宋体", Font.PLAIN, 30));
		ja2.setFont(new Font("宋体", Font.PLAIN, 30));
		ja1.setText(new String(ja1.getText()) + "a");
		ja1.setAutoscrolls(true);
		ja1.setVisible(true);
		ja2.setVisible(true);

		getContentPane().add(jp);
		jp.add(scrollPane);
		jp.add(jp1);
		jp.add(ja2);
		jp1.add(jbfile);

		JButton msbtn = new JButton("消息记录");
		msbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				messageread();

			}
		});
		msbtn.setBounds(661, 0, 108, 58);
		jp1.add(msbtn);

		JButton jbsend = new JButton("发送");
		jbsend.setBounds(690, 612, 108, 53);
		jbsend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chat.ip.equals("255.255.255.255"))
					chat.messagesend(Heading.COMMESSAGE, ja2.getText(), chat.ip);
				else
					chat.messagesend(Heading.MESSAGE, ja2.getText(), chat.ip);
			}
		});
		jp.add(jbsend);

	}

	/**
	 * 更新列表
	 */
	public void updatemessage(String ip, String innermessage) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String message1 = dateFormat.format(calendar.getTime()) + "  From  ：" + ip + " ：\n";
		String message2 = innermessage + "\n";
		MessageSummary.add(message1);
		MessageSummary.add(message2);

		ja1.append(message1 + message2);
		ja1.setCaretPosition(ja1.getText().length());
	}

	/**
	 * 文件发送
	 */
	private void fileSend() {
		FileSendThread fst = new FileSendThread(this.ip);
		Thread th = new Thread(fst);
		th.start();
	}

	public void messagesend(String head, String message, String ip) {

		// 发送到IP；
		ja2.setText("");
		ja2.requestFocus();
		MessageSend ms = new MessageSend(head, message, ip);
		Thread th = new Thread(ms);
		th.start();
		updatemessage(ip, message);
	}

	class winAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent windowEvent) {
			ChatManager.removeChat(ip);
			messagestore();
		}
	}

	private void messagestore() {
		// -------------------------------
		File file = new File(ip + ".txt");
		try {
			if (!file.exists())
				file.createNewFile();

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (String s : MessageSummary) {
				bw.write(s);
				bw.flush();
			}
			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void messageread() {
		MessageRead mr = new MessageRead(ip);
	}

	class MessageRead extends JFrame {
		MessageRead ms;

		public MessageRead(String ip) {
			ms = this;
			this.setTitle("消息记录");
			this.setBounds(chat.getX() + chat.getWidth(), chat.getY(), 200, 200);
			this.setVisible(true);
			JTextArea jams = new JTextArea();
			JScrollPane jsp = new JScrollPane(jams);
			jsp.setSize(200, 200);
			this.add(jsp, BorderLayout.CENTER);

			File file = new File(ip + ".txt");
			try (BufferedReader br = new BufferedReader(new FileReader(file));) {
				String s;
				// 将开头调至开头
				jams.setCaretPosition(0);
				while ((s = br.readLine()) != null) {
					// 多添加一个\n
					jams.append(s + "\n");
				}
				s = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent windowEvent) {
					ms.dispose();
				}
			});
		}

	}

}
