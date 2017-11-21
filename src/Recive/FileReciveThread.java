package Recive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Object.Heading;

/**
 * 文件接收线程 使用TCP 端口9590
 * 
 * @author hf
 *
 */
public class FileReciveThread implements Runnable {

	String Type;
	private String fname;

	public FileReciveThread(String Type, String fname) {
		this.Type = Type;
		this.fname = fname;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String string;
		// 创建服务端Socket对象-----------------------------------------------------多线程可优化
		ServerSocket ss;
		BufferedWriter bw;
		BufferedReader br;
		Socket s;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		try {
			ss = new ServerSocket(9590);

			// 监听客户端连接
			s = ss.accept();
			System.out.println(fname + ": 文件名字在前面");// -----------------------

			if (Type.equals(Heading.TEXTFILE)) { // 创建字符接受流，创建文件输出流
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));

				File file = new File(fname);
				if (!file.exists())
					file.createNewFile();

				bw = new BufferedWriter(new FileWriter(file));
				while ((string = br.readLine()) != null) {
					bw.write(string);
					bw.newLine();
					bw.flush();
				}

				bw.close();
				br.close();

			} else { // 接受字节型的文件
				File file = new File(fname);
				if (!file.exists())
					file.createNewFile();

				bis = new BufferedInputStream(s.getInputStream());
				bos = new BufferedOutputStream(new FileOutputStream(file));

				byte[] bys = new byte[1024];
				int len;
				while ((len = bis.read(bys)) != -1) {
					bos.write(bys, 0, len);
					bos.flush();
				}
				bis.close();
				bos.close();
			}

			System.out.println("文件接受完毕");
			ss.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
