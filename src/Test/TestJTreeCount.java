package Test;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TestJTreeCount {

	private JFrame jframeFriendList;
	private JPanel jpanelFrendList;

	private JButton jbtnOnlineFriendList, jbtnMyGroupChat;

	private JList jlistOnlineFriendList;

	private DefaultListModel defaultListModel;

	private JScrollPane jscrollPaneOnlineFriendList;
	private JScrollPane jscrollPaneOnlineFriendListJTree;

	// 设置树
	private JTree jtree;
	// 设置节点(此节点为跟节点)
	private DefaultMutableTreeNode dmtnRoot = new DefaultMutableTreeNode();

	private DefaultMutableTreeNode dmtnOnlineUsersRoot = new DefaultMutableTreeNode(
			"我的好友");

	private DefaultMutableTreeNode dmtnMyGroup = new DefaultMutableTreeNode(
			"我的群");

	private DefaultMutableTreeNode dmtnUnknownUsers = new DefaultMutableTreeNode(
			"陌生人");

	private DefaultMutableTreeNode dmtnBlackName = new DefaultMutableTreeNode(
			"黑名单");

	// 设置节点(此节点为好友列表节点)
	private DefaultMutableTreeNode dmtnLeaf;

	private DefaultMutableTreeNode dmtnGroup = new DefaultMutableTreeNode("群聊");

	public TestJTreeCount() {
		// TODO Auto-generated constructor stub
		initView();
		acctionListener();
		setOnlineList();
	}
	
	public static void main(String[] args) {
		new TestJTreeCount();
	}

	// 视图
	public void initView() {
		jframeFriendList = new JFrame();
		jpanelFrendList = new JPanel();
		defaultListModel = new DefaultListModel();
		// jlistOnlineFriendList = new JList(defaultListModel);
		jlistOnlineFriendList = new JList();

		jbtnOnlineFriendList = new JButton("在线用户");
		jbtnOnlineFriendList.setEnabled(false);
		jbtnMyGroupChat = new JButton("模拟QQ设计");
		jbtnMyGroupChat.setEnabled(false);
		jpanelFrendList.setLayout(null);
		// 列表显示
		// dmtnRoot.add(dmtnLeaf);
		jtree = new JTree(dmtnRoot);
		// 设置根节点是否显示
		jtree.setRootVisible(false);
		jtree.putClientProperty("JTree.lineStyle", "None");// 清除线
		jtree.setShowsRootHandles(true);// 设置图标
		dmtnRoot.add(dmtnOnlineUsersRoot);
		dmtnRoot.add(dmtnMyGroup);
		dmtnRoot.add(dmtnUnknownUsers);
		dmtnRoot.add(dmtnBlackName);
		dmtnMyGroup.add(dmtnGroup);

		jscrollPaneOnlineFriendListJTree = new JScrollPane(jtree);
		jscrollPaneOnlineFriendList = new JScrollPane(jlistOnlineFriendList);
		// jscrollPaneOnlineFriendList.getViewport()
		// .setView(jlistOnlineFriendList);
		jbtnOnlineFriendList.setBounds(0, 0, 250, 30);
		jscrollPaneOnlineFriendList.setBounds(0, 30, 250, 570);
		jscrollPaneOnlineFriendListJTree.setBounds(0, 30, 250, 570);
		jbtnMyGroupChat.setBounds(0, 595, 250, 30);

		jpanelFrendList.add(jbtnOnlineFriendList);

		jpanelFrendList.add(jscrollPaneOnlineFriendListJTree);
		// jpanelFrendList.add(jscrollPaneOnlineFriendList);

		jpanelFrendList.add(jbtnMyGroupChat);
		jframeFriendList.add(jpanelFrendList);

		jframeFriendList.setSize(250, 650);
		jframeFriendList.setResizable(false);
		jframeFriendList.setLocationRelativeTo(null);
		jframeFriendList
				.setDefaultCloseOperation(jframeFriendList.EXIT_ON_CLOSE);
		jframeFriendList.setVisible(true);
	}

	// 触发事件
	public void acctionListener() {
		// 选择好友聊天
		jtree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// super.mouseClicked(e);
				int count = jtree.getRowForLocation(e.getX(), e.getY());
				if (e.getClickCount() == 1)
					System.out.println(count);
				if (count != -1) {
					String strFriendUsername = jtree
							.getLastSelectedPathComponent().toString();
					if (e.getClickCount() == 2 && count != 0
							&& !strFriendUsername.equals("陌生人")
							&& !strFriendUsername.equals("黑名单")
							&& !strFriendUsername.equals("我的群")) {
						if (jtree.isRowSelected(jtree.getRowForLocation(
								e.getX(), e.getY()))) {
							System.out.println("你双击了：" + strFriendUsername);
						}
					}
				}
			}
		});

		// 关闭事件
		jframeFriendList.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				// super.windowClosing(e);
				System.exit(0);
			}
		});
	}

	// 设置在线列表
	public void setOnlineList() {
		String onlineUsers = null;
		for (int i = 0; i < 5; i++) {
			onlineUsers += "go";
			dmtnLeaf = new DefaultMutableTreeNode(onlineUsers);
			dmtnOnlineUsersRoot.add(dmtnLeaf);
		}
		jtree.updateUI();
	}
}
