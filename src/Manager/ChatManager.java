package Manager;



import java.util.HashMap;

import Chat.Chat;

/**
 * 用户聊天窗口管理
 * 
 * @author hf
 *
 */

public class ChatManager {
	static HashMap<String, Chat> chatmanager = new HashMap<String, Chat>();

	public static Chat getChat(String ip) {
		Chat chat = chatmanager.get(ip);
		if (chat != null)
			return chat;
		else {
			chat = addChat(ip);
			return chat;
		}

	}

	private static Chat addChat(String ip) {
		Chat chat = null;

		chat = new Chat(ip);
		chatmanager.put(ip, chat);
		return chat;

	}
	public static void removeChat(String ip) {
		if(chatmanager.containsKey(ip));
		chatmanager.remove(ip);
	}
}
