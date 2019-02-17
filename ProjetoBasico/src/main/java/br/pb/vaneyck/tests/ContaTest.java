package br.pb.vaneyck.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.Propriedades;
import br.pb.vaneyck.pages.ContasPage;
import br.pb.vaneyck.pages.MenuPage;

// com essa notacao, define a ordem em que os metodos serao executados
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTest extends BaseTest {
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void test1_InserirConta() {
		
		menuPage.acessarTelaInserirConta();
		contasPage.setNome("conta do teste");
		contasPage.salvar();
		
		Assert.assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
		
	}
	
	@Test
	public void test2_AlterarConta() {
		
		menuPage.acessarTelaListarConta();
		
		// seleciona a coluna
		contasPage.clicarAlterarConta("Conta para alterar");
		contasPage.setNome("conta alterada");
		contasPage.salvar();
		
		Assert.assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());
		
	}
	
	@Test
	public void test3_InserirContaMesmoNome() {
		
		menuPage.acessarTelaInserirConta();
		contasPage.setNome("Conta mesmo nome");
		contasPage.salvar();
		
		Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());
		
	}

}
