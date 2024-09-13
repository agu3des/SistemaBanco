package appconsole;

import regras_negocio.Fachada;

public class Alterar {

	public Alterar() {
		try {
			Fachada.inserirCorrentistaConta(1, "0001");
			Fachada.inserirCorrentistaConta(3, "1111");
			Fachada.inserirCorrentistaConta(4, "2222");
			Fachada.removerCorrentistaConta(2, "1111");
			

			Fachada.apagarConta(4);
			

			System.out.println("adicionou correntistas a contas");
		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Alterar();
	}
}
