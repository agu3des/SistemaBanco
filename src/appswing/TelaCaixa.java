package appswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TelaCaixa {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixa window = new TelaCaixa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCaixa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Caixa");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label.setBounds(184, 31, 63, 30);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("Creditar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		button.setBounds(298, 67, 104, 30);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Debitar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		button_1.setBounds(298, 108, 104, 30);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Transferir");
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		button_2.setBounds(298, 150, 104, 30);
		frame.getContentPane().add(button_2);
		
		JLabel label_1 = new JLabel("Correntista:");
		label_1.setBounds(20, 108, 63, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_1_1 = new JLabel("Conta:");
		label_1_1.setBounds(20, 78, 46, 14);
		frame.getContentPane().add(label_1_1);
		
		textField = new JTextField();
		textField.setBounds(101, 102, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 72, 86, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel label_2 = new JLabel("Valor:");
		label_2.setBounds(20, 136, 46, 14);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(101, 133, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(20, 208, 382, 30);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Conta Destino:");
		label_4.setBounds(20, 161, 73, 14);
		frame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(101, 160, 86, 20);
		frame.getContentPane().add(textField_3);
	}
}
