package appswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import regras_negocio.Fachada;

public class TelaConta {
    private JDialog frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel label;
    private JLabel label_6;
    private JLabel label_1;
    private JLabel label_2;
    private JTextField textField;
    private JTextField textField_1;
    private JButton button;
    private JButton button_1;
    private JLabel label_3;
    private JTextField textField_2;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    
    private JLabel labelSaldoTotal;


    public TelaConta() {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JDialog();
        frame.setModal(true);
        frame.setResizable(false);
        frame.setTitle("Conta/ContaEspecial");
        frame.setBounds(100, 100, 729, 385);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                listagem();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 43, 674, 148);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setGridColor(Color.BLACK);
        table.setRequestFocusEnabled(false);
        table.setFocusable(false);
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        label = new JLabel("");
        label.setForeground(Color.BLUE);
        label.setBounds(21, 321, 688, 14);
        frame.getContentPane().add(label);

        label_6 = new JLabel("selecione");
        label_6.setBounds(21, 190, 431, 14);
        frame.getContentPane().add(label_6);

        label_1 = new JLabel("Digite parte do id:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label_1.setBounds(21, 14, 128, 14);
        frame.getContentPane().add(label_1);

        textField = new JTextField();
        textField.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField.setColumns(10);
        textField.setBackground(Color.WHITE);
        textField.setBounds(159, 11, 137, 20);
        frame.getContentPane().add(textField);

        label_2 = new JLabel("Limite:");
        label_2.setHorizontalAlignment(SwingConstants.LEFT);
        label_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label_2.setBounds(21, 269, 71, 14);
        frame.getContentPane().add(label_2);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField_1.setColumns(10);
        textField_1.setBounds(68, 264, 195, 20);
        frame.getContentPane().add(textField_1);

        button_1 = new JButton("Criar Conta");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = textField_2.getText();
                    if (cpf.isEmpty()) {
                        label.setText("CPF não pode ser vazio.");
                        return;
                    }
                    if (textField_1.getText().isEmpty()) {
                        Fachada.criarConta(cpf);
                        label.setText("Conta criada com sucesso!");
                    } else {
                        double limite = Double.parseDouble(textField_1.getText());
                        Fachada.criarContaEspecial(cpf, limite);
                        label.setText("Conta Especial criada com sucesso!");
                    }
                    listagem();
                } catch (Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button_1.setBounds(285, 227, 110, 23);
        frame.getContentPane().add(button_1);

        button = new JButton("Listar");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listagem();
            }
        });
        button.setBounds(306, 9, 89, 23);
        frame.getContentPane().add(button);

        label_3 = new JLabel("CPF:");
        label_3.setHorizontalAlignment(SwingConstants.LEFT);
        label_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label_3.setBounds(21, 242, 63, 14);
        frame.getContentPane().add(label_3);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
        textField_2.setColumns(10);
        textField_2.setBounds(68, 238, 195, 20);
        frame.getContentPane().add(textField_2);

        button_2 = new JButton("Apagar Conta");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (table.getSelectedRow() >= 0) {
                    	String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                        //String id = (String) table.getValueAt(table.getSelectedRow(), 0);
                        Fachada.apagarConta(Integer.parseInt(id));
                        label.setText("Conta apagada com sucesso!");
                        listagem();
                    } else {
                        label.setText("Selecione uma conta para apagar.");
                    }
                } catch (Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
        button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button_2.setBounds(285, 265, 110, 23);
        frame.getContentPane().add(button_2);

        button_3 = new JButton("Adicionar Correntista");
	    button_3.addActionListener(e -> {
    	    if (table.getSelectedRow() >= 0) {
    	        String id = table.getValueAt(table.getSelectedRow(), 0).toString();
    	        String cpf = abrirPopupSelecaoCorrentista();
    	        if (cpf != null) {
    	            try {
    	                Fachada.inserirCorrentistaConta(Integer.parseInt(id), cpf);
    	                label.setText("Correntista adicionado à conta!");
    	                listagem();
    	            } catch (Exception ex) {
    	                label.setText(ex.getMessage());
    	            }
    	        } else {
    	            label.setText("Nenhum correntista selecionado.");
    	        }
    	    } else {
    	        label.setText("Selecione uma conta para adicionar o correntista.");
    	    }
        });
        button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button_3.setBounds(539, 227, 156, 23);
        frame.getContentPane().add(button_3);

        button_4 = new JButton("Remover Correntista");
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                    String cpf = abrirPopupSelecaoCorrentista();
                    if (cpf != null) {
                        try {
                            Fachada.removerCorrentistaConta(Integer.parseInt(id), cpf.trim());
                            label.setText("Correntista removido com sucesso!");
                            listagem();
                        } catch (Exception ex) {
                            label.setText(ex.getMessage());
                        }
                    } else {
                        label.setText("Nenhum correntista selecionado.");
                    }
                } else {
                    label.setText("Selecione uma conta para remover o correntista.");
                }
            }
        });
        button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button_4.setBounds(539, 264, 156, 23);
        frame.getContentPane().add(button_4);

        button_5 = new JButton("Limpar");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField.requestFocus();
            }
        });
        button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button_5.setBounds(402, 9, 86, 23);
        frame.getContentPane().add(button_5);
        
        labelSaldoTotal = new JLabel("Saldo Total: 0.0");
        labelSaldoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        labelSaldoTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        labelSaldoTotal.setBounds(400, 321, 295, 14);
        frame.getContentPane().add(labelSaldoTotal);

    }

    public void listagem() {
        try {
            // Obter o texto do campo de pesquisa
            String idPesquisa = textField.getText().trim();
            
            // Listar todas as contas
            List<Conta> lista = Fachada.listarContas();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Data");
            model.addColumn("Saldo");
            model.addColumn("Correntistas");
            model.addColumn("Limite");
            
            double saldoTotal = 0.0;  // Para calcular o saldo total

            for (Conta conta : lista) {
                // Verificar se o ID da conta contém o texto pesquisado
                if (String.valueOf(conta.getId()).contains(idPesquisa)) {
                    String correntistasTexto = "";
                    if (conta.getCorrentistas().isEmpty()) {
                        correntistasTexto = "Sem correntistas";
                    } else {
                        for (Correntista correntista : conta.getCorrentistas()) {
                            correntistasTexto += correntista.getCpf() + " ";
                        }
                    }
                    
                    saldoTotal += conta.getSaldo();

                    if (conta instanceof ContaEspecial ce) {
                        model.addRow(new Object[]{conta.getId(), conta.getData(), conta.getSaldo(), correntistasTexto, ce.getLimite()});
                    } else {
                        model.addRow(new Object[]{conta.getId(), conta.getData(), conta.getSaldo(), correntistasTexto, "-"});
                    }
                }
            }

            table.setModel(model);
            label_6.setText("Resultados: " + model.getRowCount() + " contas - Selecione uma linha.");
            labelSaldoTotal.setText("Saldo Total: " + saldoTotal);  // Atualiza o label com o saldo total
        } catch (Exception erro) {
            label.setText("Erro: " + erro.getMessage());
        }
    }
    
    private void carregarCorrentistas(JTable tableCorrentistas) {
        try {
            List<Correntista> correntistas = Fachada.listarCorrentistas();
            DefaultTableModel model = new DefaultTableModel(new String[]{"CPF", "Nome"}, 0);

            correntistas.forEach(c -> model.addRow(new Object[]{c.getCpf(), c.getNome()}));
            tableCorrentistas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar correntistas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private String abrirPopupSelecaoCorrentista() {
        JDialog dialog = new JDialog(frame, "Selecione o Correntista", true);
        dialog.setSize(400, 300);
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(frame);

        JTable tableCorrentistas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableCorrentistas);
        dialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnSelect = new JButton("Selecionar");
        JButton btnCancel = new JButton("Cancelar");
        buttonPanel.add(btnSelect);
        buttonPanel.add(btnCancel);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        carregarCorrentistas(tableCorrentistas);

        final String[] cpfSelecionado = {null};
        btnSelect.addActionListener(e -> {
            if (tableCorrentistas.getSelectedRow() >= 0) {
                cpfSelecionado[0] = tableCorrentistas.getValueAt(tableCorrentistas.getSelectedRow(), 0).toString();
                dialog.dispose();
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
        return cpfSelecionado[0];
    }

    
    public JDialog getFrame() {
        return frame;
    }
    
    public static void main(String[]args) {
        TelaConta telaConta = new TelaConta(); 
        telaConta.getFrame().setVisible(true); 
    }
}
