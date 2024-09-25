package appconsole;

import regras_negocio.Fachada;

public class Alterar {

	public Alterar() {
		try {

			Fachada.inserirCorrentistaConta(1, "0001");
			Fachada.inserirCorrentistaConta(3, "1111");
			Fachada.inserirCorrentistaConta(4, "2222");
			Fachada.removerCorrentistaConta(2, "1111");
			
			//teste de verificação do cotitular
			Fachada.inserirCorrentistaConta(1, "2222");
			Fachada.inserirCorrentistaConta(3, "1111");
			
			//teste para verificar a inexistencia de uma conta e a impossibilitação de associação de um correntista
			Fachada.inserirCorrentistaConta(5, "2756");
			
			//teste de verificação de que um cotitular pode ser removido
			Fachada.removerCorrentistaConta(1, "0001");
			
			//teste para verificar que não é possível remover um titular
			Fachada.removerCorrentistaConta(1, "1111");
			
			//associar cotitulares para verificar se todos são removidos após apagar a conta
			Fachada.inserirCorrentistaConta(5, "1111");
			Fachada.inserirCorrentistaConta(5, "2222");
			Fachada.inserirCorrentistaConta(5, "3333");
			Fachada.inserirCorrentistaConta(5, "4444");


			System.out.println("Ação realizada com sucesso!");
		} catch (Exception e) {
			System.out.println("--->" +e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
