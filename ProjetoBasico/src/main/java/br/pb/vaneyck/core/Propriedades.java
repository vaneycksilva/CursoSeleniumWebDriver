package br.pb.vaneyck.core;

public class Propriedades {
	
	// true pra fechar o browser e false para não fechar (false apenas se estiver sendo executados
	// todos os testes em um browser só)  
	public static boolean FECHAR_BROWSER = true;
	
	public static Browsers browser = Browsers.FIREFOX;
	
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.GRID;
	
	// sempre a executar os testes vai ser um numero diferente
	//public static String NOME_CONTA_ALTERADA = "Conta alterada " + System.nanoTime();
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
	
	public enum TipoExecucao {
		LOCAL,
		GRID,
		NUVEM
	}

}
