package appconsole;

import modelo.Correntista;

import modelo.Conta;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas (arquivo)-----");
			for(Conta c : Fachada.listarContas()) 
				System.out.println(c);

			System.out.println("\n---------listagem de correntistas (arquivo) ----");
			for(Correntista cr : Fachada.listarCorrentistas()) 
				System.out.println(cr);
			
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main (String[] args) 
	{
		new Listar();
	}
}


