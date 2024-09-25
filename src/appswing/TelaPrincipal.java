package appswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPrincipal {
    private JFrame frame;

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

    public TelaPrincipal() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Principal");
        
        JLabel label = new JLabel("Bem Vindo ao Banco!!");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label.setBounds(100, 41, 237, 31);
        frame.getContentPane().add(label);
        
        JLabel label_1 = new JLabel("O que deseja fazer?");
        label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label_1.setBounds(165, 83, 97, 14);
        frame.getContentPane().add(label_1);
        
        JButton buttonConta = new JButton("Conta");
        buttonConta.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false); // Muda a visibilidade
                new TelaConta(); // Abre a tela de contas
            }
        });
        buttonConta.setBounds(139, 160, 137, 23);
        frame.getContentPane().add(buttonConta);
        
        JButton buttonCorrentista = new JButton("Correntista");
        buttonCorrentista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonCorrentista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false); // Muda a visibilidade
                new TelaCorrentista(); // Abre a tela de correntistas
            }
        });
        buttonCorrentista.setBounds(139, 194, 137, 23);
        frame.getContentPane().add(buttonCorrentista);
        
        JButton buttonCaixa = new JButton("Caixa");
        buttonCaixa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        /*buttonCaixa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); // Muda a visibilidade
                new TelaCaixa(); // Abre a tela do caixa
            }
        });*/
        buttonCaixa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false); // Oculta a tela principal
                TelaCaixa telaCaixa = new TelaCaixa(); // Instancia a TelaCaixa
                telaCaixa.getFrame().setVisible(true); // Torna a tela Caixa visível
            }
        });
        buttonCaixa.setBounds(139, 126, 137, 23);
        frame.getContentPane().add(buttonCaixa);
    }
    
}
