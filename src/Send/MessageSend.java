package Send;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Object.Heading;
import Object.User;

/**
 * 普通消息发送
 * @author hf
 *
 */
public class MessageSend implements Runnable{

	String message,head;
	private String ip;
	public MessageSend(String head,String m,String ip) {
		// TODO Auto-generated constructor stub
		this.message=m;
		this.head=head;
		this.ip = ip;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] bys;
			String line;
			line = new StringBuilder(head).append("=").append(message).toString();
			DatagramSocket ds = new DatagramSocket();
			bys = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bys, 0, bys.length, InetAddress.getByName(ip), 9595);
			ds.send(dp);
			System.out.println("消息发送了");
			ds.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
