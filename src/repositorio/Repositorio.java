
package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.Correntista;

public class Repositorio {
	private ArrayList<Conta> contas = new ArrayList<>();
	private ArrayList<Correntista> correntistas = new ArrayList<>(); 

	public Repositorio() {
		carregarObjetos();
	}
	
	
	
	public void adicionarConta(Conta c)	{
		contas.add(c);
	}

	public void removerConta(Conta c)	{
		contas.remove(c);
	}
	
	public Conta localizarConta(int id)	{
		for(Conta c : contas)
			if(c.getId() == id)
				return c;
		return null;
	}

	

	public void adicionarCorrentista(Correntista cr)	{
		correntistas.add(cr);
	}
	
	public void removerCorrentista(Correntista cr)	{
		correntistas.remove(cr);
	}

	public Correntista localizarCorrentista(String cpf)	{
		for(Correntista cr : correntistas)
			if(cr.getCpf().equals(cpf))
				return cr;
		return null;
	}
	
	public Correntista localizarCorrentistaNome(String nome)	{
		for(Correntista cr : correntistas)
			if(cr.getNome().equals(nome))
				return cr;
		return null;
	}

	
	
	public ArrayList<Conta> getContas() 	{
		return contas;
	}
	

	public ArrayList<Correntista> getCorrentistas() 	{
		return correntistas;
	}

	
	
	public int getTotalContas()	{
		return contas.size();
	}

	public int getTotalCorrentistas()	{
		return correntistas.size();
	}

	
	public int gerarIdConta() {
		if (contas.isEmpty())
			return 1;
		else {
			Conta ultimo = contas.get(contas.size()-1);
			return ultimo.getId() + 1;
		}
	}
	

	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\correntistas.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\contas.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		String linha;	
		String[] partes;
		Conta co;
		Correntista corr;

		try	{
			String cpf, nome, senha, contas;
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);	 
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				contas = partes[3];
				corr = new Correntista(cpf, nome, senha);
				this.adicionarCorrentista(corr);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de correntistas:"+ex.getMessage());
		}

		try	{
			String tipo, id, data, saldo, correntistas;
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];
				correntistas="";
				
				co = null;
				
				
				if(tipo.equals("CONTA")) {
					co = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionarConta(co);
					if(partes.length>4)
						correntistas = partes[4];		//cpfs dos correntistas separados por ","
					//relacionar correntista com as suas contas
					if(!correntistas.isEmpty() && co != null) {	
						for(String cpfCorrentista : correntistas.split(",")){	//converter string em array
							corr = this.localizarCorrentista(cpfCorrentista);

							corr.adicionarConta(co);
							co.adicionarCorrentista(corr);
						}
					}
				}
				else if (tipo.equals("CONTA ESPECIAL")) {
					String limite = partes[4];
					ContaEspecial ce = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo), Double.parseDouble(limite));
					this.adicionarConta(ce);
					if(partes.length>5)
						correntistas = partes[5];		//cpfs dos correntistas separados por ","
					//relacionar correntista com as suas contas
					if(!correntistas.isEmpty() && ce != null) {	
						for(String cpfCorrentista : correntistas.split(",")){	//converter string em array
							corr = this.localizarCorrentista(cpfCorrentista);

							corr.adicionarConta(ce);
							ce.adicionarCorrentista(corr);
						}
					}
				}


			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de contas:"+ex.getMessage());
		}
	}

	//--------------------------------------------------------------------
	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que est�o no reposit�rio
		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f); 
			for(Correntista corr : correntistas) 	{
				arquivo1.write(corr.getCpf()+";"+corr.getNome()+";"+corr.getSenha()+";"+corr.getContas()+"\n");	
			} 
			arquivo1.close();
		}
		catch(Exception e){
			throw new RuntimeException("problema na criacao do arquivo correntistas "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ; 
			ArrayList<String> lista ;
			String listaCpf;
			for(Conta co : contas) {
				//montar uma lista com os cpfs dos correntistas da conta
				lista = new ArrayList<>();
				for(Correntista corr : co.getCorrentistas()) {
					lista.add(corr.getCpf()+"");
				}
				listaCpf = String.join(",", lista);

				if(co instanceof ContaEspecial ce )
					arquivo2.write("CONTA ESPECIAL;" +co.getId() +";" + co.getData() +";" 
							+ co.getSaldo() +";"+ ce.getLimite() +";"+ listaCpf +"\n");	
				else
					arquivo2.write("CONTA;" +co.getId() +";" + co.getData() +";" 
							+ co.getSaldo() +";"+ listaCpf +"\n");

			} 
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na criacao do arquivo  participantes "+e.getMessage());
		}

	}
}

