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
 * 文件发送端口 9590
 * 
 * @author hf
 *
 */
public class FileSendThread implements Runnable {
	String path, fname;
	private String ip;

	// 创建发送的Socket包
	public FileSendThread(String ip) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
	}

	@Override
	public void run() {

		try {

			// 首先是创建JFileChooser 对象，里面带个参数，表示默认打开的目录，这里是默认打开当前文件所在的目录。
			JFileChooser file = new JFileChooser("D://");
			// 下面这句是去掉显示所有文件这个过滤器。
			file.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter;

			Object[] options = { "文件", "文档" };
			int response = JOptionPane.showOptionDialog(null, "   选择发送的文件类型", "文件发送器", JOptionPane.YES_OPTION,
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
					// 先发送通知消息过去
					// 创建发送的Socket包
					Socket s = new Socket(ip, 9590);

					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
					BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
					byte[] bys = new byte[1024];
					// 创建套用发送字节流和文件读取流
					int len = 0;
					while ((len = bis.read(bys)) != -1) {
						bos.write(bys, 0, len);
						bos.flush();
					}
					bis.close();
					bos.close();
					s.close();

					ChatManager.getChat(ip).updatemessage(InetAddress.getLocalHost().toString(),
							"文件发送: " + fname + "完成");
					System.out.println("文件发送完毕");
				}

			} else if(response==2){

				// 后缀名过滤器
				filter = new FileNameExtensionFilter("txt", "ini", "class");
				file.setFileFilter(filter);

				int result = file.showOpenDialog(null);
				if (result == 0) {
					path = file.getSelectedFile().getAbsolutePath();
					fname = file.getSelectedFile().getName();
					ChatManager.getChat(ip).messagesend(Heading.TEXTFILE, fname, this.ip);
					System.out.println(path);
					System.out.println(fname);
					// 先发送通知消息过去
					// 创建发送的Socket包
					Socket s = new Socket(ip, 9590);

					// 创建套用发送字节流和文件读取流

					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					BufferedReader br = new BufferedReader(new FileReader(path));

					// 创建缓存的字符串
					String sb = null;
					while ((sb = br.readLine()) != null) {
						bw.write(sb);
						bw.newLine();
						bw.flush();
						System.out.println("正在读写");
					}
					s.shutdownInput();

					bw.close();
					br.close();
					s.close();

					ChatManager.getChat(ip).updatemessage(InetAddress.getLocalHost().toString(),
							"文件发送: " + fname + "完成");
					System.out.println("文件发送完毕");
				}
			}

		} catch (Exception e) {

		}
	}
}
