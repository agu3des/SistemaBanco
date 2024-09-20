package modelo;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}
	
	public ContaEspecial (int id, String data, double limite) {
		super(id, data);
		this.limite = limite;
	}

	@Override
	public void debitar(double valor) throws Exception {
	    if (valor < 0) throw new Exception("Quantia inválida!");
	    double saldoDisponivel = saldo + limite; // Conta o limite disponível
	    if (valor > saldoDisponivel) throw new Exception("Quantia incorreta, você não possui saldo suficiente!");
	    saldo -= valor; // Ajusta o saldo
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	@Override
	public String toString() {
	String texto =  "id=" + id + ", data=" + data + ", saldo=" + saldo + ", limite=" + limite;

	texto += ", correntistas:";
	for(Correntista c : correntistas)
		texto += c.getCpf() + ",";
		return texto;
}
		

}