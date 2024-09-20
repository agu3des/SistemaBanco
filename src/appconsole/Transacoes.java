package appconsole;

import regras_negocio.Fachada;

public class Transacoes {

	public Transacoes() {
		try {
			Fachada.creditarValor(1, "1111", "1234", 1000);
			Fachada.creditarValor(3, "2222", "1234", 900);
			Fachada.creditarValor(2, "4444", "9999", 700);
			Fachada.creditarValor(4, "4444", "9999", 700);
			
			Fachada.debitarValor(1, "3333", "5678", 100);
			
			Fachada.transferirValor(1, 3, "1111", "1234", 100);
			
			//verificar se o limite está sendo usado da forma correta
			Fachada.transferirValor(5,2, "2756", "8743", 60); //dentro do limite
			Fachada.transferirValor(5,4, "2756", "8743", 9); //fora do limite
			
			System.out.println("Ação realizada com sucesso!");
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public static void main (String[] args) 
	{
		new Transacoes();
	}

}
