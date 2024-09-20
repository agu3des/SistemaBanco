package appswing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
	public TelaPrincipal() {
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
		
		JLabel label = new JLabel("Bem Vindo ao Banco!!");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		label.setBounds(100, 41, 237, 31);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("O que deseja fazer?");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		label_1.setBounds(165, 83, 97, 14);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("Conta");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button.setBounds(139, 123, 137, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Correntista");
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_1.setBounds(139, 194, 137, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Caixa");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(139, 159, 137, 23);
		frame.getContentPane().add(button_2);
	}
}
