package br.pb.vaneyck.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.DriverFactory;
import br.pb.vaneyck.pages.MenuPage;
import br.pb.vaneyck.pages.ResumoPage;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	
	@Test
	public void test1_ExcluirMovimentacao() {
		
		menuPage.acessarTelaResumo();
		
		resumoPage.excluirMovimentacao();
				
		Assert.assertEquals("Movimentação removida com sucesso!", resumoPage.obterMensagemSucesso());
	}
	
	// import estatico crlt+shift+n
	//@Test(expected=NoSuchElementException.class) // pode-se tambem esperar por um erro ja conhecido,
	// nao eh recomendado pouco causa um falso positivo
	@Test
	public void test2_ResumoMensal() {
		
		menuPage.acessarTelaResumo();
		
		// verfifica apenas pelo titulo da pagina
		Assert.assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
		
		
		resumoPage.selecionarAno("2016");
		resumoPage.buscar();
		
		
		// verifica se uma tabela esta preenchida, para isso, coloca-se todos os elementos em uma lista
		// e depois verifica se o tamanho da lista é igual a 0
		List<WebElement> elementosEncontrados = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		
		
		Assert.assertEquals(0, elementosEncontrados.size());
	}
	
}
