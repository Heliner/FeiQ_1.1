package Test;

import javax.swing.JOptionPane;

public class JOp {
	public static void main(String[] a) {
		Object[] options = {"文件","文档类型","退出"};
		int response=JOptionPane.showOptionDialog ( null, "   选择发送的文件类型","文件发送器",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE,
		null, options, options[0] ) ;
		if (response == 0)
		{;}
		else if(response == 1)
		{;}
		else if(response == 2)
		{JOptionPane.showMessageDialog(null,"您按下了取款按钮");}
	}
}
