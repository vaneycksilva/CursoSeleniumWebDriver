package br.pb.vaneyck.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.pb.vaneyck.core.DriverFactory;
import br.pb.vaneyck.pages.LoginPage;
import br.pb.vaneyck.tests.ContaTest;
import br.pb.vaneyck.tests.MovimentacaoTest;
import br.pb.vaneyck.tests.RemoverMovimentacaoContaTest;
import br.pb.vaneyck.tests.ResumoTest;
import br.pb.vaneyck.tests.SaldoTest;

// define a ordem em que as classes serão executadas

@RunWith(Suite.class)
@SuiteClasses ({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class
})

/***
 * com os testes de forma sequenciada, todos os testes utilizavam o mesmo login, portanto executavam em um mesmo
 * browser
 * 
 * em paralelo, cada execução vai ter seu proprio browser
 * 
 * **/

public class SuiteGeral {
//	
	private static LoginPage page = new LoginPage();
//	
//	
//	// antes de executar a suite ele vai logar. Vai fazer com que seja executado todos os testes
//	// em apenas um browser
//	@BeforeClass
//	public static void inicializa() {
//		
//		page.acessarPagina();
//		page.setEmail("vaneyck23@gmail.com");
//		page.setSenha("123456");
//		page.entrar();
//		
//	}
//	
//	// quando termina todos os testes, fecha o browser
//	
//	@AfterClass
//	public static void finaliza() {
//		
//		DriverFactory.killDriver();
//	}
	
	
	// antes de executar os testes, reinicia os dados
	@BeforeClass
	public static void reset() {
		
		page.acessarPagina();
		
		page.setEmail("vaneyck23@gmail.com");
		page.setSenha("123456");
		page.entrar();
		
		page.resetar();
		
		DriverFactory.killDriver();
		
	}
	
}
