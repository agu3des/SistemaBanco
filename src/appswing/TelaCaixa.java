package appswing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener; 

import javax.swing.JButton;
//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import regras_negocio.Fachada;

public class TelaCaixa {

    private JFrame frame;
    private JTextField textField; // Correntista (CPF)
    private JTextField textField_1; // ID da conta
    private JTextField textField_2; // Valor
    /*
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
    }*/

    public TelaCaixa() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 370, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Caixa");
        

        JLabel label = new JLabel("Caixa");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label.setBounds(160, 31, 63, 30);
        frame.getContentPane().add(label);

        JButton button = new JButton("Creditar");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idConta = Integer.parseInt(textField_1.getText().trim());
                    String cpf = textField.getText().trim();
                    double valor = Double.parseDouble(textField_2.getText().trim());

                    String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:", "Autenticação", JOptionPane.PLAIN_MESSAGE);
                    Fachada.creditarValor(idConta, cpf, senha, valor);

                    JOptionPane.showMessageDialog(frame, "Valor creditado com sucesso.");
                } catch (Exception ex) {
                	JOptionPane.showMessageDialog(frame, "Não foi possível creditar devido ao seguinte erro: " + ex.getMessage(), "\nErro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        button.setBounds(217, 62, 104, 30);
        frame.getContentPane().add(button);

        JButton button_1 = new JButton("Debitar");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idConta = Integer.parseInt(textField_1.getText().trim());
                    String cpf = textField.getText().trim();
                    double valor = Double.parseDouble(textField_2.getText().trim());

                    String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:", "Autenticação", JOptionPane.PLAIN_MESSAGE);
                    Fachada.debitarValor(idConta, cpf, senha, valor);

                    JOptionPane.showMessageDialog(frame, "Valor debitado com sucesso.");
                } catch (Exception ex) {
                	JOptionPane.showMessageDialog(frame, "Não foi possível debitar devido ao seguinte erro: " + ex.getMessage(), "\nErro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        button_1.setBounds(217, 103, 104, 30);
        frame.getContentPane().add(button_1);

        JButton button_2 = new JButton("Transferir");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idContaOrigem = Integer.parseInt(textField_1.getText().trim());
                    String cpf = textField.getText().trim();
                    double valor = Double.parseDouble(textField_2.getText().trim());

                    String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:", "Autenticação", JOptionPane.PLAIN_MESSAGE);
                    String contaDestinoStr = JOptionPane.showInputDialog(frame, "Digite a conta de destino:", "Transferência", JOptionPane.PLAIN_MESSAGE);
                    int idContaDestino = Integer.parseInt(contaDestinoStr.trim());

                    Fachada.transferirValor(idContaOrigem, idContaDestino, cpf, senha, valor);

                    JOptionPane.showMessageDialog(frame, "Transferência realizada com sucesso.");
                } catch (Exception ex) {
                	JOptionPane.showMessageDialog(frame, "Não foi possível transferir devido ao seguinte erro: " + ex.getMessage(), "\nErro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        button_2.setBounds(217, 144, 104, 30);
        frame.getContentPane().add(button_2);

        JLabel label_1 = new JLabel("Correntista:");
        label_1.setBounds(20, 108, 76, 14);
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
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Quando a janela for fechada, mostre a tela principal novamente
                frame.setVisible(false);
                new TelaPrincipal(); // Ou se você tiver uma referência à TelaPrincipal, use ela
            }
        });
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public static void main(String[] args) {
        TelaCaixa telaCaixa = new TelaCaixa();
        telaCaixa.getFrame().setVisible(true); // Exibe a tela corretamente
    }
}
