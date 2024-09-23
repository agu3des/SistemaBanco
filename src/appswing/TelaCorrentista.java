package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Conta;
import modelo.Correntista;
import regras_negocio.Fachada;

public class TelaCorrentista {
    private JDialog frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton buttonCriar;
    private JButton buttonVerContas;
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JTextField textFieldSenha; // Campo de senha
    private JLabel labelMensagem;

    public TelaCorrentista() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setTitle("Correntistas");
        frame.setBounds(100, 100, 912, 400);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 42, 844, 120);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setGridColor(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        buttonCriar = new JButton("Criar");
        buttonCriar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonCriar.setBounds(281, 310, 95, 23);
        frame.getContentPane().add(buttonCriar);
        buttonCriar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (textFieldNome.getText().isEmpty() || textFieldCpf.getText().isEmpty() || textFieldSenha.getText().isEmpty()) {
                        labelMensagem.setText("Campo vazio");
                        return;
                    }

                    String cpf = textFieldCpf.getText();
                    String nome = textFieldNome.getText();
                    String senha = textFieldSenha.getText(); // Captura a senha
                    Fachada.criarCorrentista(cpf, nome, senha); // Passa a senha para criarCorrentista
                    labelMensagem.setText("Correntista criado: " + nome);
                    listagem();
                } catch (Exception ex) {
                    labelMensagem.setText(ex.getMessage());
                }
            }
        });

        labelMensagem = new JLabel("");
        labelMensagem.setForeground(Color.BLUE);
        labelMensagem.setBounds(26, 347, 830, 14);
        frame.getContentPane().add(labelMensagem);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setHorizontalAlignment(SwingConstants.LEFT);
        labelNome.setFont(new Font("Dialog", Font.PLAIN, 12));
        labelNome.setBounds(26, 214, 71, 14);
        frame.getContentPane().add(labelNome);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setHorizontalAlignment(SwingConstants.LEFT);
        labelCpf.setFont(new Font("Dialog", Font.PLAIN, 12));
        labelCpf.setBounds(26, 242, 71, 14);
        frame.getContentPane().add(labelCpf);
        
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setHorizontalAlignment(SwingConstants.LEFT);
        labelSenha.setFont(new Font("Dialog", Font.PLAIN, 12));
        labelSenha.setBounds(26, 268, 71, 14);
        frame.getContentPane().add(labelSenha);

        textFieldNome = new JTextField();
        textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 12));
        textFieldNome.setBounds(92, 214, 169, 20); // Ajustado para a linha do nome
        frame.getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        textFieldCpf = new JTextField();
        textFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 12));
        textFieldCpf.setBounds(92, 239, 169, 20); // Ajustado para a linha do CPF
        frame.getContentPane().add(textFieldCpf);
        textFieldCpf.setColumns(10);

        textFieldSenha = new JTextField();
        textFieldSenha.setFont(new Font("Dialog", Font.PLAIN, 12));
        textFieldSenha.setBounds(92, 265, 169, 20); // Ajustado para a linha da senha
        frame.getContentPane().add(textFieldSenha);
        textFieldSenha.setColumns(10);

        buttonVerContas = new JButton("Ver contas");
        buttonVerContas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        buttonVerContas.setBounds(410, 310, 95, 23);
        frame.getContentPane().add(buttonVerContas);
        buttonVerContas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                        String cpf = table.getValueAt(table.getSelectedRow(), 0).toString();
                        Correntista correntista = Fachada.localizarCorrentista(cpf);
                        StringBuilder contas = new StringBuilder("IDs das contas:\n");
                        for (Conta c : correntista.getContas()) {
                            contas.append(c.getId()).append("\n");
                        }
                        JOptionPane.showMessageDialog(frame, contas.toString());
                    } else {
                        labelMensagem.setText("Selecione uma linha");
                    }
                } catch (Exception erro) {
                    labelMensagem.setText(erro.getMessage());
                }
            }
        });

        listagem(); // Chama a listagem inicial ao abrir a tela
    }

    private void listagem() {
        try {
            List<Correntista> correntistas = Fachada.listarCorrentistas();
            DefaultTableModel model = new DefaultTableModel(new String[] { "CPF", "Nome" }, 0);
            for (Correntista c : correntistas) {
                model.addRow(new Object[] { c.getCpf(), c.getNome() });
            }
            table.setModel(model);
        } catch (Exception e) {
            labelMensagem.setText(e.getMessage());
        }
    }

    /*public static void main(String[] args) {
        // Inicia a tela de correntistas
        new TelaCorrentista();
    }*/
}
