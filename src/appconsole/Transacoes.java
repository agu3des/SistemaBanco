package appconsole;

import regras_negocio.Fachada;

public class Transacoes {

	public Transacoes() {
		try {
			Fachada.creditarValor(1, "1111", "1234", 1000);
			Fachada.creditarValor(7, "2222", "1234", 1000);
			Fachada.debitarValor(1, "1111", "1234", 100);
			Fachada.transferirValor(1, 7, "1111", "1234", 100);
			

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public static void main (String[] args) 
	{
		new Transacoes();
	}

}
