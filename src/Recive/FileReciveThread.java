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
 * �ļ������߳� ʹ��TCP �˿�9590
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
		// ���������Socket����-----------------------------------------------------���߳̿��Ż�
		ServerSocket ss;
		BufferedWriter bw;
		BufferedReader br;
		Socket s;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		try {
			ss = new ServerSocket(9590);

			// �����ͻ�������
			s = ss.accept();
			System.out.println(fname + ": �ļ�������ǰ��");// -----------------------

			if (Type.equals(Heading.TEXTFILE)) { // �����ַ��������������ļ������
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

			} else { // �����ֽ��͵��ļ�
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

			System.out.println("�ļ��������");
			ss.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
