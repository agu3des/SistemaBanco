package appconsole;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 * 
 */

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			
			Fachada.criarCorrentista("1111","joao da silva", "1234");

			Fachada.criarCorrentista("2222", "maria de fatima", "1234");

			Fachada.criarCorrentista("3333", "jose de ribamar", "5678");

			Fachada.criarCorrentista("4444", "ana da cruz", "9999");

			Fachada.criarConta("1111"); //joao virou o titular

			Fachada.criarContaEspecial("3333", 200);

			System.out.println("Cadastrou ");

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public static void main (String[] args) 
	{
		new Cadastrar();
	}
}


