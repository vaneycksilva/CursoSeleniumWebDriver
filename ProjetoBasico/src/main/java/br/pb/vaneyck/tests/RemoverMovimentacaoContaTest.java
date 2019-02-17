package br.pb.vaneyck.tests;

import org.junit.Assert;
import org.junit.Test;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.Propriedades;
import br.pb.vaneyck.pages.ContasPage;
import br.pb.vaneyck.pages.MenuPage;

public class RemoverMovimentacaoContaTest extends BaseTest{

	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void testExcluirContaComMovimentacao() {
		
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta com movimentacao");
		
		
		Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}
	
}
