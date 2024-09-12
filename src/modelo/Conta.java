package modelo;

import java.util.ArrayList;

public class Conta {
	protected int id;
	protected String data;
	protected double saldo;

	private ArrayList<Correntista> correntistas = new ArrayList<>();
	
	public Conta(int id, String data) {
		super();
		this.id = id;
		this.data = data;
		this.saldo = 0;
	}

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
    
    public void adicionarCorrentista(Correntista correntista) {
        if (this.correntistas.size() == 0) {
            this.correntistas.add(correntista); // Primeiro correntista é o titular
        } else {
            // Verifica se já é cotitular
            boolean isCotitular = this.correntistas.stream().anyMatch(c -> c.getCpf().equals(correntista.getCpf()));
            if (!isCotitular) {
                this.correntistas.add(correntista); // Adiciona como cotitular
            }
        }
    }
    
    public void removerCorrentista(Correntista correntista) throws Exception {
    	if (!correntistas.isEmpty() && correntistas.get(0).equals(correntista)) {
            throw new Exception("Não é possível remover o titular da conta.");
        }
        this.correntistas.remove(correntista);
    }

	public Correntista localizar(String cpf){
		for(Correntista c : correntistas){
			if(c.getCpf().equals(cpf))
				return c;
			}
			return null;
	}

	@Override
		public String toString() {
		String texto =  "id=" + id + ", data=" + data + ", saldo=" + saldo;

		texto += ", correntistas:";
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


