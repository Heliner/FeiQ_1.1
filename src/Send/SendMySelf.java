package Send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Manager.UserManager;
import Object.Heading;
import Object.User;

/**
 * 登陆时发送自己的信息 端口9595 协议UDP
 * @author hf
 *
 */
public class SendMySelf implements Runnable {

	String head;

	public SendMySelf(String head) {
		this.head = head;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			byte[] bys;
			String line;
			StringBuilder sb;
			sb = new StringBuilder(head);
			User me = UserManager.users.get(0);
			line = sb.append("=").append(me.ip).append("=").append(me.username).append("=").append(me.sex).append("=")
					.append(me.underwrite).append("=").append(me.address).toString();

			DatagramSocket ds = new DatagramSocket();
			bys = line.getBytes();
			DatagramPacket dp = new DatagramPacket(bys, 0, bys.length, InetAddress.getByName("255.255.255.255"), 9595);
			ds.send(dp);
			System.out.println("登陆消息发送了");
			ds.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
