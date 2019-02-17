package br.pb.vaneyck.test;

import static br.pb.vaneyck.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TesteAjax {
	
	private DSL dsl;
	
	@Before  // antes de cada teste, executa o conteudo desse metodo
	public void inicializa() {
		
		System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		// ja deixa o arquivo html dentro da raiz do eclipse
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After // é executado apos cada um dos testes
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testAjax() throws InterruptedException{
		dsl.escrever("j_idt691:name", "teste");
		dsl.clicarBotao("j_idt691:j_idt694");
		
		WebDriverWait wait = new WebDriverWait(getDriver(), 0);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt691:display"), "teste"));
		
		// espera pela imagem do processamento
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt706")));
		
		Assert.assertEquals("teste", dsl.obterTexto("j_idt691:display"));
	}
	
}
