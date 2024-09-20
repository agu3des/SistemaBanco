package appconsole;


import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			
			Fachada.criarCorrentista("1111","joao da silva", "1234");
			Fachada.criarCorrentista("2222", "maria de fatima", "1234");
			Fachada.criarCorrentista("3333", "jose de ribamar", "5678");
			Fachada.criarCorrentista("4444", "ana da cruz", "9999");
			Fachada.criarCorrentista("6987", "tavares lima", "9999");

			Fachada.criarConta("1111"); //joao virou o titular
			Fachada.criarConta("4444"); //ana virou o titular
			Fachada.criarConta("2222"); //maria virou o titular
			Fachada.criarConta("3333"); //jose virou o titular
			
			Fachada.criarContaEspecial("2756",68); //malu virou o titular
			
			
			//teste de ordenação do listar
			Fachada.criarCorrentista("2756", "malu borges", "8743"); 

			//teste para a criação de uma conta especial
			Fachada.criarContaEspecial("3333", 200);
			
			Fachada.criarConta("6987");

			//demonstrar que não pode criar uma conta sem correntista associado
			Fachada.criarConta("8743"); 
			
			//teste de verificação da titularidade
			Fachada.criarConta("2222"); //maria virou o titular			

			System.out.println("Cadastrou");

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public static void main (String[] args) 
	{
		new Cadastrar();
	}
}


