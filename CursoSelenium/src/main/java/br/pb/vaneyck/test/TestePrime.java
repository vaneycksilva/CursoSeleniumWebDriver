package br.pb.vaneyck.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static br.pb.vaneyck.core.DriverFactory.getDriver;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TestePrime {

	private DSL dsl;
	
	@Before  // antes de cada teste, executa o conteudo desse metodo
	public void inicializa() {
		
		System.setProperty("webgetDriver()gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		// ja deixa o arquivo html dentro da raiz do eclipse
		
		dsl = new DSL();
	}
	
	@After // é executado apos cada um dos testes
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
										// com esse id é ruim pois no desenvolvimento da página
										// esse id foi gerado de forma aleatoria
		dsl.clicarRadio(By.xpath("//input[@id='j_idt692:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt692:console:0"));
		
		
		// faz a busca pela label, evitando o id aleatório
		dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt692:console:1"));
		
	}
	
	@Test
	public void deveIntagirComSelectPrime() {
		
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		
		dsl.selecionarComboPrime("j_idt692:console", "Xbox One");
				
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt692:console_label"));
		//Assert.assertEquals("PS4", dsl.obterTexto("j_idt692:console_label"));
	}
	
}
