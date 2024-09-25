package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;
import repositorio.Repositorio;

public class Fachada {
	private Fachada() {}		
	private static Repositorio repositorio = new Repositorio();	
	
	public static ArrayList<Correntista> listarCorrentistas() {
	    ArrayList<Correntista> lista = repositorio.getCorrentistas(); // Pega todos os correntistas

	    // Ordena a lista com base no CPF usando Comparator
	    Collections.sort(lista, new Comparator<Correntista>() {
	        @Override
	        public int compare(Correntista c1, Correntista c2) {
	            return c1.getCpf().compareTo(c2.getCpf()); // Compara os CPFs
	        }
	    });
	    
	    return lista;
	}
	
	public static ArrayList<Conta> listarContas() {
		//lista as contas que contem o nome ou
		//lista todos participantes, caso nome for vazio, 
		return repositorio.getContas();
	}
	
	public static Conta localizarConta(int id) {
		return repositorio.localizarConta(id);
	}
	
	public static Correntista localizarCorrentista(String cpf) 	{
		return repositorio.localizarCorrentista(cpf);
	}
	
	public static void criarConta(String cpf) throws Exception {
		cpf = cpf.trim();

	    // Localizar Correntista no repositorio, usando o CPF
	    Correntista cr = repositorio.localizarCorrentista(cpf);
	    if (cr == null) {
	        throw new Exception("Criar Conta: " + cpf + " -> Não há correntista titular com esse CPF");
	    }

	    // Verificar se o CPF já é titular de outra conta no repositorio
	    for (Conta contaExistente : repositorio.getContas()) {
	        // Verifica se o primeiro correntista é o titular com o mesmo CPF
	        if (contaExistente.getCorrentistas().size() > 0 && 
	            contaExistente.getCorrentistas().get(0).getCpf().equals(cpf)) {
	            throw new Exception("Criar Conta: " + cpf + " -> Esse CPF já é titular de outra conta");
	        }
	    }

	    // Gerar ID no repositorio
	    int id = repositorio.gerarIdConta();
	    
	    // Gerar data no repositorio
	    String data = getDataAtual();
	    
	    // Criar nova conta
	    Conta co = new Conta(id, data);

	    // Adicionar a nova conta ao repositório
	    repositorio.adicionarConta(co);

		//Associar a conta ao correntista
		co.adicionarCorrentista(cr);
		
		//Associar a conta ao correntista
		cr.adicionarConta(co);
		
	    // Gravar repositório
	    repositorio.salvarObjetos();
	}
	
	public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
		cpf = cpf.trim();
		nome = nome.trim();
		senha = senha.trim();

		//localizar correntista no repositorio, usando o cpf 
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr!=null)
			throw new Exception("Nao criou correntista: " + cpf + " ja cadastrado(a)");
		
		if (senha.length() != 4 || !senha.matches("\\d+"))
			throw new Exception("A senha deve ser de 4 dígitos e ser numérica!");

		//criar objeto Correntista 
		cr = new Correntista (cpf, nome, senha);
		

		//adicionar correntista no repositorio
		repositorio.adicionarCorrentista(cr);

		
		//gravar repositorio
		repositorio.salvarObjetos();
	}	

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();
		
		//localizar Correntista no repositorio, usando o cpf 
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr==null)
			throw new Exception("Criar Conta: " + cpf + " -> Nao ha correntista titular com esse cpf!");
		
		//o limite minimo é de 50 reais
		if (limite < 50)
			throw new Exception("O limite minimo é de 50 reais!");

		int id = repositorio.gerarIdConta();
		
		//gerar data no repositorio
		String data = getDataAtual();
		
		//criar objeto ContaEspecial
		ContaEspecial ce = new ContaEspecial (id, data, limite);
		
		//adicionar Conta Especial no repositorio
		repositorio.adicionarConta(ce);
		
		//associar a conta ao correntista
		ce.adicionarCorrentista(cr);
		
		//associar correntista a conta especial
		cr.adicionarConta(ce);
		
		//gravar repositorio
		repositorio.salvarObjetos();
		
	}
	
	public static void inserirCorrentistaConta (int id, String cpf) throws Exception {
		//localizar conta no repositorio, usando id
		Conta co = repositorio.localizarConta(id);
		if(co == null) 
			throw new Exception("Inserir CorrentistaConta: Conta " + id + " inexistente");
		
		//localizar correntista no repositorio, usando cpf
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if(cr == null) 
			throw new Exception("Inserir CorrentistaConta:  " + cpf + " inexistente");
		
		//localizar o correntista na conta, usando o id para verificar se já existe o relacionamento
		Conta caux = cr.localizarConta(id);
		if(caux != null) 
			throw new Exception("Nao inseriu correntista: " + cpf + " ja participa da conta " + id);


		//adicionar a conta ao correntista
		cr.adicionarConta(co);
		
		//adicionar o correntista à conta
		co.adicionarCorrentista(cr);
		
		
		//gravar repositorio
		repositorio.salvarObjetos();

	}

	
	
	
	
	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
	    cpf = cpf.trim();
	    
	    // localizar conta no repositorio usando id
	    Conta co = repositorio.localizarConta(id);
	    if(co == null) 
	        throw new Exception("Remover CorrentistaConta: conta " + id + " inexistente");
	    
	    // localizar correntista no repositorio, usando cpf
	    Correntista cr = repositorio.localizarCorrentista(cpf);
	    if (cr == null)
	        throw new Exception("Remover CorrentistaConta: correntista " + cpf + " inexistente");

	    // Localizar correntista dentro da conta usando o cpf 
	    //(para garantir que estamos lidando com a mesma instância)
	    Correntista crAux = null;
	    for (Correntista c : co.getCorrentistas()) {
	        if (c.getCpf().equals(cpf)) { //se o cpf do momento do for é igual ao que eu passei
	            crAux = c; // encontramos a instância exata do correntista na conta
	            break;
	        }
	    }

	    if (crAux == null) {
	        throw new Exception("Não removeu correntista: " + cpf + " não participa da conta " + id);
	    }

	    // Remover o correntista da conta e a conta do correntista
	    co.removerCorrentista(crAux);  // Remover a instância exata do correntista na conta
	    cr.remover(co);  // Remover a conta do correntista no repositório

	    // Salvar alterações no repositório
	    repositorio.salvarObjetos();
	}


	public static void apagarConta(int id) throws Exception	{
		//localizar conta no repositorio, usando id 
		Conta co = repositorio.localizarConta(id);
		if (co == null)
			throw new Exception("Erro ao apagar conta: " + id + " id inexistente!");

		if (co.getSaldo() != 0) {
			throw new Exception("Erro ao apagar conta com id " + id + ", pois saldo diferente de zero.");
		}
		
		//Remover todos os correntista desta conta
		for(Correntista cr : co.getCorrentistas()) {
			cr.remover(co);
		}
		co.getCorrentistas().clear();
		
		//remover conta do repositorio
		repositorio.removerConta(co);
		
		//gravar repositorio
		repositorio.salvarObjetos();
	}
	
	
	public static void creditarValor(int id, String cpf, String senha, double valor)  throws Exception {
		Conta co = repositorio.localizarConta(id);
		if (co == null) {
			throw new Exception("Erro ao localizar conta com id: " + id);
		}
		
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr == null) {
			throw new Exception("Erro ao localizar correntista com cpf: " + cpf);
		}
		
		if (!cr.getSenha().equals(senha)) {
			throw new Exception("Senha Incorreta!");
		}

		co.creditar(valor);
		
		//gravar repositorio
		repositorio.salvarObjetos();
	}

	public static void debitarValor(int id, String cpf, String senha, double valor)  throws Exception {
		Conta co = repositorio.localizarConta(id);
		
		if (co == null) {
			throw new Exception("Erro ao localizar conta com id: " + id);
		}
		
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr == null) {
			throw new Exception("Erro ao localizar correntista com cpf: " + cpf);
		}
		
		if (!cr.getSenha().equals(senha)) {
			throw new Exception("Senha Incorreta!");
		}
		
		co.debitar(valor);
		
		//gravar repositorio
		repositorio.salvarObjetos();
	}
	
	public static void transferirValor(int id1, int id2, String cpf, String senha, double valor) throws Exception {
		Conta co1 = repositorio.localizarConta(id1);
		Conta co2 = repositorio.localizarConta(id2);
		
		if (co1 == null || co2 == null) {
			throw new Exception("Erro ao localizar conta com id: " + id1 + " ou " + id2);
		}
		
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr == null) {
			throw new Exception("Erro ao localizar correntista com cpf: " + cpf);
		}
		
		if (!cr.getSenha().equals(senha)) {
			throw new Exception("Senha Incorreta!");
		}
		
		co1.transferir(valor, co2);
		
		//gravar repositorio
		repositorio.salvarObjetos();
	}
	
	
    // Método auxiliar para obter a data atual
	private static String getDataAtual() {
	    DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate d1 = LocalDate.now(); // Obter a data atual
	    return d1.format(f1); // Formatar a data
	}


}
