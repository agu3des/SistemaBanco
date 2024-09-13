package modelo;

import java.util.ArrayList;

public class Correntista {
	private String cpf;
	private String nome;
	private String senha;
	private ArrayList<Conta> contas = new ArrayList<>();

	public Correntista(String cpf, String nome, String senha) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}
	public void adicionarConta(Conta co){
		contas.add(co);
	}
	public void remover(Conta co){
		contas.remove(co);
	}
	public Conta localizarConta(int id){
		for(Conta co : contas){
			if(co.getId() == (id))
				return co;
		}
		return null;
	}
	@Override
	public String toString() {
	String texto= "Correntista [cpf=" + cpf + ", nome=" + nome + "]";

	texto += ", contas:";
		for(Conta co : contas)
			texto += co.getId() + ",";
			return texto;
	}


	//gets
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	
	public ArrayList<Conta> getContas() {
		return contas;
	}

	
	//sets
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}


}