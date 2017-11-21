package Object;

import java.util.ArrayList;

/**
 * User 对象 存取User和所有User对象的users数组
 * 
 * @author hf
 *
 */
public class User {

	public String ip = null;
	public String username = "默认";
	public String sex = "性别";
	public String underwrite = "无";
	public String address = "无";

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


	// ----------------------------------------------此处可能出错
	@Override
	public boolean equals(Object obj) {
		if (this == obj) // 传入的对象就是它自己，如s.equals(s)；肯定是相等的；
			return true;
		if (obj == null) // 如果传入的对象是空，肯定不相等
			return false;
		if (getClass() != obj.getClass()) // 如果不是同一个类型的，如Studnet类和Animal类，
											// 也不用比较了，肯定是不相等的
			return false;
		User other = (User) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip)) // 如果name属性相等，则相等
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
