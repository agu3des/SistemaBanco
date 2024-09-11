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
	public void adicionar(Conta co){
		contas.add(co);
	}
	public void remover(Conta co){
		contas.remove(co);
	}
	public Conta localizar(int id){
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
	/*
	public double getTotalPago() {
	double total = 0;
	for(Conta co : contas) {
	total += co.getPago(this.preco);
	}
	return total;
	}

	public int getIdadeMedia() {
	if (participantes.size()==0)
	return 0;

	double soma=0;
	for(Participante p : participantes) {
	soma += p.getIdade();
	}
	return (int) Math.round(soma/participantes.size());
	}

	public ArrayList<Participante> getParticipantesPorIdade(int idade) {
	ArrayList<Participante> lista = new ArrayList<>();

	for(Participante p : participantes) {
	if (p.getIdade()==idade)
	lista.add(p);
	}
	return lista;
	}
	public int contarConvidados() {
	int cont=0;
	for(Participante p : participantes) {
	if (p instanceof Convidado)
	cont++;
	}
	return cont;
	}
	*/
	/*
	public ArrayList<Convidado> getConvidados() {
	ArrayList<Convidado> convidados = new ArrayList<>();
	for(Participante p : participantes) {
	if (p instanceof Convidado c)
	convidados.add(c);
	}
	return convidados;
	}

	public ArrayList<Convidado> getConvidados(String empresa) {
	ArrayList<Convidado> convidados = new ArrayList<>();

	for(Participante p : participantes) {
	if (p instanceof Convidado c && c.getEmpresa().equals(empresa))
	convidados.add(c);
	}
	return convidados;
	}
	*/
	/*
	public int contarGratuidades() {
	int cont=0;
	for(Participante p : participantes) {
	if (p.getPago(this.preco) == 0)
	cont++;
	}
	return cont;
	}
	*/

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/*
	public double getPreco() {
	return preco;
	}
	public void setPreco(double preco) {
	this.preco = preco;
	}*/
	public ArrayList<Conta> getContas() {
		return contas;
	}



}
