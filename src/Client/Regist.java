package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Regist extends JFrame {
	private static final long serialVersionUID = 4070735293537430173L;
	
	private JPasswordField passwordField;
	private JTextField textField;
	JFrame mainframe;

	public Regist(JFrame f, Connection conn) {
		setTitle("×¢²á");
		this.mainframe = f;
		addWindowListener(new winAdapter());

		setBounds(100, 100, 492, 327);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 120, 286);
		contentPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(null);

		JLabel label_1 = new JLabel("ÃÜÂë");
		label_1.setBounds(39, 136, 102, 37);
		label_1.setOpaque(false);
		panel.add(label_1);

		JLabel label_2 = new JLabel("ÕËºÅ");
		label_2.setBounds(39, 81, 87, 27);
		label_2.setOpaque(false);
		panel.add(label_2);

		JButton button_1 = new JButton("·µ»Ø");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainframe.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(153, 237, 79, 27);
		button_1.setOpaque(false);
		contentPane.add(button_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(153, 142, 191, 25);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setBounds(153, 86, 191, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		JButton button = new JButton("×¢²á");
		button.setOpaque(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ppp = passwordField.getText();
				System.out.println(ppp);
				System.out.println(textField.getText());
				try {
					PreparedStatement pstmt;
					String addsql;
					addsql = "INSERT INTO User (count,pwd) VALUES (?,?)";
					pstmt = conn.prepareStatement(addsql);
					pstmt.setString(1, textField.getText());
					pstmt.setString(2, passwordField.getText());
					pstmt.executeUpdate();
					textField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "×¢²á³É¹¦");
					dispose();
					mainframe.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "ÕË»§ÒÑ´æÔÚ");
				}
			}
		});
		button.setBounds(259, 237, 79, 27);
		contentPane.add(button);
		this.setVisible(true);
		this.setResizable(false);

	}

	class winAdapter extends WindowAdapter {

		public void windowClosing(WindowEvent windowEvent) {
			dispose();
			mainframe.setVisible(true);
		}
	}
}
