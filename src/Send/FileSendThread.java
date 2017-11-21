package Send;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Manager.ChatManager;
import Object.Heading;

/**
 * �ļ����Ͷ˿� 9590
 * 
 * @author hf
 *
 */
public class FileSendThread implements Runnable {
	String path, fname;
	private String ip;

	// �������͵�Socket��
	public FileSendThread(String ip) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
	}

	@Override
	public void run() {

		try {

			// �����Ǵ���JFileChooser �������������������ʾĬ�ϴ򿪵�Ŀ¼��������Ĭ�ϴ򿪵�ǰ�ļ����ڵ�Ŀ¼��
			JFileChooser file = new JFileChooser("D://");
			// ���������ȥ����ʾ�����ļ������������
			file.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter;

			Object[] options = { "�ļ�", "�ĵ�" };
			int response = JOptionPane.showOptionDialog(null, "   ѡ���͵��ļ�����", "�ļ�������", JOptionPane.YES_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (response == 0) {
				filter = new FileNameExtensionFilter("exe", "docx", "doc","jpg","jpeg");
				file.setFileFilter(filter);

				int result = file.showOpenDialog(null);
				if (result == 0) {
					path = file.getSelectedFile().getAbsolutePath();
					fname = file.getSelectedFile().getName();
					ChatManager.getChat(ip).messagesend(Heading.BYTEFILE, fname, this.ip);
					System.out.println(path);
					System.out.println(fname);
					// �ȷ���֪ͨ��Ϣ��ȥ
					// �������͵�Socket��
					Socket s = new Socket(ip, 9590);

					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
					BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
					byte[] bys = new byte[1024];
					// �������÷����ֽ������ļ���ȡ��
					int len = 0;
					while ((len = bis.read(bys)) != -1) {
						bos.write(bys, 0, len);
						bos.flush();
					}
					bis.close();
					bos.close();
					s.close();

					ChatManager.getChat(ip).updatemessage(InetAddress.getLocalHost().toString(),
							"�ļ�����: " + fname + "���");
					System.out.println("�ļ��������");
				}

			} else if(response==2){

				// ��׺��������
				filter = new FileNameExtensionFilter("txt", "ini", "class");
				file.setFileFilter(filter);

				int result = file.showOpenDialog(null);
				if (result == 0) {
					path = file.getSelectedFile().getAbsolutePath();
					fname = file.getSelectedFile().getName();
					ChatManager.getChat(ip).messagesend(Heading.TEXTFILE, fname, this.ip);
					System.out.println(path);
					System.out.println(fname);
					// �ȷ���֪ͨ��Ϣ��ȥ
					// �������͵�Socket��
					Socket s = new Socket(ip, 9590);

					// �������÷����ֽ������ļ���ȡ��

					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					BufferedReader br = new BufferedReader(new FileReader(path));

					// ����������ַ���
					String sb = null;
					while ((sb = br.readLine()) != null) {
						bw.write(sb);
						bw.newLine();
						bw.flush();
						System.out.println("���ڶ�д");
					}
					s.shutdownInput();

					bw.close();
					br.close();
					s.close();

					ChatManager.getChat(ip).updatemessage(InetAddress.getLocalHost().toString(),
							"�ļ�����: " + fname + "���");
					System.out.println("�ļ��������");
				}
			}

		} catch (Exception e) {

		}
	}
}
