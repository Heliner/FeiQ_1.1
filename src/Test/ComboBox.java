package Test;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComboBox {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("box");
		JComboBox box = new JComboBox();
		box.addItem("好友1");
		box.addItem("好友2");
		box.addItem("好友3");
		box.addItem("好友4");
		frame.setBounds(0, 0, 300, 300);
		frame.add(box);
		frame.setVisible(true);
	}
}
