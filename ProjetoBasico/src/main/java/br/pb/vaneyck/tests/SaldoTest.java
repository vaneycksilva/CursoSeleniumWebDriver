package br.pb.vaneyck.tests;

import org.junit.Assert;
import org.junit.Test;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.Propriedades;
import br.pb.vaneyck.pages.HomePage;
import br.pb.vaneyck.pages.MenuPage;


public class SaldoTest extends BaseTest {
	
	private HomePage homePage = new HomePage();
	private MenuPage home = new MenuPage();
	
	@Test
	public void testSaldoConta() {
		
		home.acessarTelaPrincipal();
		Assert.assertEquals("534.00", homePage.obterSaldoConta("Conta para saldo"));
		
	}

}
