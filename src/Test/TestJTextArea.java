package Test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestJTextArea extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJTextArea frame = new TestJTextArea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestJTextArea() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextArea jta = new JTextArea();
		jta.setRows(10);
		jta.setColumns(10);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		jta.setFont(new Font("ËÎÌו", Font.PLAIN, 30));
		setBounds(100, 100, 797, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(jta);
		scrollPane.setBounds(0, 0, 761, 459);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jta.setText("fsdaffffffffffffffffffffffffffffffffffffffffffffffffff");
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 473, 744, 89);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jta.append(textArea.getText()+"\n");
				
			}
		});
		btnNewButton.setBounds(648, 576, 113, 27);
		contentPane.add(btnNewButton);
	}
}
