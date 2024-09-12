package regras_negocio;
/**********************************
 * POO - Fausto Ayres
 **********************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import modelo.ContaEspecial;
import modelo.Correntista;
import modelo.Conta;
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
	/*public static Evento localizarEvento(String data) 	{
		return repositorio.localizarEvento(data);
	}*/
	
	public static void criarConta (String cpf) throws Exception {
		/*data = data.trim();
		descricao = descricao.trim();*/

		//localizar Correntista no repositorio, usando o cpf 
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr==null)
			throw new Exception("criar Conta: " + cpf + " -> Nao ha correntista titular com esse cpf");
		
		/*if (preco <0)
			throw new Exception("criar evento: " + descricao + " preco nao pode ser negativo " + preco);*/

		//gerar id no repositorio
		int id = repositorio.gerarIdConta();
		
		//gerar data no repositorio
		String data = getDataAtual();
		
		Conta co = new Conta(id, data);	
		
		//adicionar conta no reposit�rio
		repositorio.adicionarConta(co);
		//gravar reposit�rio
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
		
		if (senha.length() > 4 || senha.length() < 4)
			throw new Exception("A senha deve ser de 4 dígitos");

		//criar objeto Correntista 
		cr = new Correntista (cpf, nome, senha);
		

		//adicionar correntista no repositorio
		repositorio.adicionarCorrentista(cr);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}	

	public static void criarContaEspecial(String cpf, double limite) throws Exception {
		cpf = cpf.trim();
		
		//localizar Correntista no repositorio, usando o cpf 
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr==null)
			throw new Exception("criar Conta: " + cpf + " -> Nao ha correntista titular com esse cpf");
		
		//o limite minimo eh de 50 reais
		if (limite < 50)
			throw new Exception("O limite minimo é de 50 reais");

		int id = repositorio.gerarIdConta();
		
		//gerar data no repositorio
		String data = getDataAtual();
		
		//criar objeto ContaEspecial
		ContaEspecial ce = new ContaEspecial (id, data, limite);
		
		//adicionar Conta Especial no reposit�rio
		repositorio.adicionarConta(ce);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}
	
	public static void inserirCorrentistaConta (int id, String cpf) throws Exception {
		//localizar conta no repositorio, usando id
		Conta c = repositorio.localizarConta(id);
		if(c == null) 
			throw new Exception("inserir CorrentistaConta: Conta " + id + " inexistente");
		
		//localizar correntista no repositorio, usando cpf
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if(cr == null) 
			throw new Exception("inserir CorrentistaConta:  " + cpf + " inexistente");
		
		//localizar o correntista na conta, usando o id
		Conta caux = cr.localizarConta(id);
		if(caux != null) 
			throw new Exception("Nao inseriu correntista: " + cpf + " ja participa da conta " + id);

		//adicionar a conta ao correntista
		cr.adicionarConta(c);
		//adicionar o correntista à conta
		c.adicionarCorrentista(cr);
		//gravar reposit�rio
		repositorio.salvarObjetos();

	}

	public static void removerCorrentistaConta(int id, String cpf) throws Exception {
		cpf = cpf.trim();
		
		//localizar conta no repositorio usando id
		Conta c = repositorio.localizarConta(id);
		if(c == null) 
			throw new Exception("remover CorrentistaConta: conta " + id + " inexistente");
		
		//localizar correntista no repositorio, usando cpf
		Correntista cr = repositorio.localizarCorrentista(cpf);
		if (cr == null)
			throw new Exception("remover CorrentistaConta: correntista" + cpf + " inexistente");
		
		//localizar correntista na conta, usando o id
		Conta caux = cr.localizarConta(id);
		if(caux == null) 
			throw new Exception("Nao removeu correntista" + cpf + " nao participa da conta " + id);

		//remover a conta do correntista
		cr.remover(c);
		//remover o correntista da conta
		c.removerCorrentista(cr);
		//gravar reposit�rio
		repositorio.salvarObjetos();

	}

	public static void apagarConta(int id) throws Exception	{
		//localizar conta no repositorio, usando id 
		Conta c = repositorio.localizarConta(id);
		if (c == null)
			throw new Exception("apagar conta: id " + id + " inexistente");

		//Remover todos os correntista desta conta
		for(Correntista cr : c.getCorrentistas()) {
			cr.remover(c);
		}
		c.getCorrentistas().clear();
		
		//remover conta do reposit�rio
		repositorio.removerConta(c);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}
	
	/*public static void 	apagarParticipante(String nome) throws Exception {
		nome = nome.trim();

		//localizar participante no repositorio, usando o nome 
		Participante p = repositorio.localizarParticipante(nome);
		if(p == null) 
			throw new Exception("apagar participante: participante " + nome + " inexistente");

		//participante nao pode ser deletado caso participe de algum evento
		if(!p.getEventos().isEmpty()) 
			throw new Exception("apagar participante: participante " + nome + " ainda tem evento");

		//remover o participante do repositorio
		repositorio.remover(p);
		//gravar reposit�rio
		repositorio.salvarObjetos();
	}*/
	
    // Método auxiliar para obter a data atual
    private static String getDataAtual() {
        return java.time.LocalDate.now().toString();
    }

}
