package Test;

import javax.swing.JOptionPane;

public class JOp {
	public static void main(String[] a) {
		Object[] options = {"�ļ�","�ĵ�����","�˳�"};
		int response=JOptionPane.showOptionDialog ( null, "   ѡ���͵��ļ�����","�ļ�������",JOptionPane.YES_OPTION ,JOptionPane.PLAIN_MESSAGE,
		null, options, options[0] ) ;
		if (response == 0)
		{;}
		else if(response == 1)
		{;}
		else if(response == 2)
		{JOptionPane.showMessageDialog(null,"��������ȡ�ť");}
	}
}
