package Object;

import java.util.ArrayList;

/**
 * User ���� ��ȡUser������User�����users����
 * 
 * @author hf
 *
 */
public class User {

	public String ip = null;
	public String username = "Ĭ��";
	public String sex = "�Ա�";
	public String underwrite = "��";
	public String address = "��";

	// public User(String ip) {
	// this.ip = ip;
	// }
	private User() {
		super();
	}

	public User(String ip) {
		this.ip = ip;
	}

	public User(String ip, String username, String sex, String underwrite, String address) {
		// TODO Auto-generated constructor stub
		this.ip = ip;
		this.username = username;
		this.sex = sex;
		this.underwrite = underwrite;
		this.address = address;
	}


	// ----------------------------------------------�˴����ܳ���
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // ����Ķ���������Լ�����s.equals(s)���϶�����ȵģ�
			return true;
		if (obj == null) // �������Ķ����ǿգ��϶������
			return false;
		if (getClass() != obj.getClass()) // �������ͬһ�����͵ģ���Studnet���Animal�࣬
											// Ҳ���ñȽ��ˣ��϶��ǲ���ȵ�
			return false;
		User other = (User) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip)) // ���name������ȣ������
			return false;
		return true;
		
	}

	public String getIp() {
		return this.ip;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public String getUnderwrite() {
		return underwrite;
	}

	public String getSex() {
		return sex;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
