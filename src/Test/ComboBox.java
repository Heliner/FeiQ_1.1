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
		box.addItem("����1");
		box.addItem("����2");
		box.addItem("����3");
		box.addItem("����4");
		frame.setBounds(0, 0, 300, 300);
		frame.add(box);
		frame.setVisible(true);
	}
}
