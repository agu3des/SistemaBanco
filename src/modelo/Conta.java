package modelo;

import java.util.ArrayList;

public class Conta {
	protected int id;
	protected String data;
	protected double saldo;

	private ArrayList<Correntista> correntistas = new ArrayList<>();

	public Conta(int id, String data, double saldo) {
		super();
		this.id = id;
		this.data = data;
		this.saldo = saldo;
	}

    public void creditar(double valor) {
        saldo = saldo + valor; //altera o objeto, pois é void
    }

    public void debitar(double valor) throws Exception{
        if(valor<0) throw new Exception("Quantia invalida!");
        if(valor>saldo) throw new Exception("Quantia incorreta, voce nao possui saldo suficiente!");
        saldo = saldo - valor;
    }

    public void transferir(double valor, Conta destino) throws Exception{
        this.debitar(valor);                   //recebe e desvia 
        destino.creditar(valor);
    }

	public Correntista localizar(String cpf){
		for(Correntista c : correntistas){
			if(c.getCpf() == cpf)
				return c;
			}
			return null;
	}

	public int getPercentual() {
		//percentual de desconto de acordo com a idade
		if(id<18) //crian�a
			return 50; //50% de desconto
		else
			if(id>=60) //idoso
				return 20; //20% de desconto
			else
				return 0; //adulto nao tem desconto
	}

	public double getPago(double preco) {
	//preco do evento - desconto calculado
		return preco - preco*getPercentual()/100;
	}

	@Override
		public String toString() {
		String texto =  "id=" + id + ", data=" + data + ", saldo=" + saldo + ", percentual="+getPercentual() ;

		texto += ", eventos:";
		for(Correntista c : correntistas)
			texto += c.getCpf() + ",";
			return texto;
	}

	//gets
	public String getData() {
		return data;
	}
	public double getSaldo() {
		return saldo;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}

	//sets
	public void setData(String data) {
		this.data = data;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void setId(int id) {
		this.id = id;
	}


}



