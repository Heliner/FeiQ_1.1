package Manager;

import java.util.ArrayList;
import java.util.Iterator;

import Object.User;

/**
 * 用于存储User列表
 * @author hf
 *
 */
public class UserManager {

	public static ArrayList<User> users = new ArrayList<User>();

	public static void addUser(String ip, String username, String sex, String underwrite, String address) {
		User u = new User(ip, username, sex, underwrite, address);
		if(!users.contains(u))
		users.add(u);
	}

	public static void addUser(String ip) {
		User u = new User(ip);
		users.add(u);
	}

	public static void removeUser(User u) {
		
		if (users.contains(u))
			if (users.remove(u))
				System.out.println("元素移除成功!");
		;
	}
	// public static void removeUser(User u) {
	// if (users.contains(u)) {
	// users.remove(u);
	// }
	// ;
	// }

	public static User getUser(String ip) {
		User u = null;
		for (int i = 0; i < users.size(); i++) {
			if (ip.equals(users.get(i).getIp())) {
				u = users.get(i);
				break;
			}
		}
		return u;
	}

}
