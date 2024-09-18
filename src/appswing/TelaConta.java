
package appswing;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;
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



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaCorrentistas window = new TelaCorrentistas();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaConta() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

		label_1 = new JLabel("Digite parte do nome");
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

		button_1 = new JButton("Criar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cpf = textField_1.getText();
					String limite = textField_2.getText();
					if(id.isEmpty())
						Fachada.criarConta(cpf);
					else
						Fachada.criarContaEspecial(cpf, Double.parseDouble(limite));

					label.setText("participante criado: ");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_1.setBounds(535, 273, 86, 23);
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

		button_2 = new JButton("Apagar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String id = (String) table.getValueAt( table.getSelectedRow(), 1);
						Fachada.apagarConta(Integer.parseInt(id));
						label.setText("Deletou conta " +id);
						listagem();
					}
					else
						label.setText("Conta nao selecionada");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_2.setBounds(309, 213, 86, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Adicionar Correntista");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 1);
						String id = JOptionPane.showInputDialog(frame, "Digite o id");
						Conta co = Fachada.localizarConta(Integer.parseInt(id));
						Correntista cr = Fachada.localizarCorrentista(cpf);

						JOptionPane.showMessageDialog(frame, "CPF do correntista="+cr.getCpf()+ " - Conta associada =" +co.getId());

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma inserção do correntista "+cpf, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.inserirCorrentistaConta(Integer.parseInt(id), cpf);
							label.setText(co.getId()+ " será associada ao correntista de cpf "+cr.getCpf());
							listagem();
						}
						else
							label.setText("Não foi possível inserir correntista com CPF: " +cpf);

					}
					else
						label.setText("Correntista não selecionado!");
				}
				catch(NumberFormatException ex) {
					label.setText("Formato de ID ou CPF inválido!");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_3.setBounds(411, 213, 137, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Remover Correntista");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String cpf = (String) table.getValueAt( table.getSelectedRow(), 1);
						String id = JOptionPane.showInputDialog(frame, "Digite o id");
						Conta co = Fachada.localizarConta(Integer.parseInt(id));
						Correntista cr = Fachada.localizarCorrentista(cpf);

						JOptionPane.showMessageDialog(frame, "CPF do correntista="+cr.getCpf()+ " - Conta associada =" +co.getId());

						Object[] options = { "Confirmar", "Desistir" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirma remoção do correntista "+cpf, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
						if(escolha == 0) {
							Fachada.removerCorrentistaConta(Integer.parseInt(id), cpf);
							label.setText("Remoção feita com sucesso!");
							listagem();
						}
						else
							label.setText("Nao foi possível remover o correntista de CPF: " +cpf);

					}
					else
						label.setText("Correntista não selecionado!");
				}
				catch(NumberFormatException ex) {
					label.setText("Formato de ID ou CPF inválido!");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_4.setBounds(558, 213, 137, 23);
		frame.getContentPane().add(button_4);

		button_5 = new JButton("Limpar");
		button_5.addActionListener(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						textField.setText("");
						textField.requestFocus();
					}
				}
				);
		button_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		button_5.setBounds(402, 10, 89, 23);
		frame.getContentPane().add(button_5);
	}

	public void listagem() {
		try{
			List<Conta> lista = Fachada.listarContas(textField.getText());

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("saldo");
			model.addColumn("correntistas");

			//linhas
			String texto;
			for(Conta co : lista) {

				if(co.getCorrentistas().isEmpty())
					texto="Sem correntistas ";
				else {
					texto=" ";
					for(Correntista cr : co.getCorrentistas()) 
						texto += cr.getCpf()+ " " ;
				}

				if(co instanceof ContaEspecial ce)
					model.addRow(new Object[]{co.getId(), co.getData(), co.getSaldo(), texto, ce.getLimite()});
				else
					model.addRow(new Object[]{co.getId(), co.getData(), co.getSaldo(), texto});

			}

			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " contas   - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}


}
