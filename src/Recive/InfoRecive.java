package Recive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Client.Client;
import Manager.ChatManager;
import Manager.UserManager;
import Object.Heading;
import Object.User;
import Send.SendMySelf;

/**
 * �����߳� Э��UDP �˿�9595
 * 
 * @author hf
 *
 */
public class InfoRecive implements Runnable {

	private Client client;

	public InfoRecive(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DatagramSocket ds = new DatagramSocket(9595);
			String ip, data, head, innermessage, myip;
			myip = InetAddress.getLocalHost().getHostAddress();
			while (true) {
				byte[] bys = new byte[1024];
				// -------------------------------------
				DatagramPacket p = new DatagramPacket(bys, bys.length);

				ds.receive(p);
				ip = p.getAddress().getHostAddress();
				// equals------------------------------
				if (!ip.equals(myip)) {
					bys = p.getData();
					data = new String(bys, 0, bys.length).trim();// new String()
					head = data.split("=", 2)[0];
					innermessage = data.substring(head.length() + 1);

					if (head.equals(Heading.MESSAGE)) {
						ChatManager.getChat(ip).updatemessage(ip, innermessage);

					} else if (head.equals(Heading.BYTEFILE) || head.equals(Heading.TEXTFILE)) {
						FileReciveThread frt = new FileReciveThread(head, innermessage);
						Thread th = new Thread(frt);
						th.start();
						ChatManager.getChat(ip).updatemessage(ip, "�ļ����ܳɹ�");
						// �Զ�����----------------------

					} else if (head.equals(Heading.COMMESSAGE)) {
						ChatManager.getChat("255.255.255.255").updatemessage(ip, innermessage);

					} else if (head.equals(Heading.ONLINE)) {
						unpackedandadd(innermessage);

					} else if (head.equals(Heading.WHOONLINE)) {
						// ���ܲ����������User���󲢷����Լ�
						unpackedandadd(innermessage);
						// SendObject; ����Online�����ͷ
						SendMySelf sms = new SendMySelf(Heading.ONLINE);
						Thread th = new Thread(sms);
						th.start();
					} else if (head.equals(Heading.OFFLINE)) {
						// User.users.get);
						// User.users.remove(0);
						unpackedandremove(innermessage);

					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ����������µĶ���
	 * 
	 * @param innermessage
	 *            ��Ϣ������
	 */
	private void unpackedandadd(String innermessage) {
		String[] mess = innermessage.split("=");
		UserManager.addUser(mess[0], mess[1], mess[2], mess[3], mess[4]);
		client.update();

	}

	private void unpackedandremove(String innermessage) {
		String[] mess = innermessage.split("=");
		User u = new User(mess[0], mess[1], mess[2], mess[3], mess[4]);
		UserManager.removeUser(u);
		// ----------------------------�˴����ܳ���
		client.update();
	}
}
