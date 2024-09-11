package modelo;

public class ContaEspecial extends Conta {
	private double limite;

	public ContaEspecial(int id, String data, double saldo, double limite) {
		super(id, data, saldo);
		this.limite = limite;
	}

	@Override
	public void debitar(double valor) throws Exception{
        if(valor<0) throw new Exception("Quantia invalida!");
        if(valor>saldo) throw new Exception("Quantia incorreta, voce nao possui saldo suficiente!");
        saldo = saldo - valor;//apenas executa uma tarefa
    }

	public String getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
		

}
