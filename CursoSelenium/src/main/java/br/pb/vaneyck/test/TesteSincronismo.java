package br.pb.vaneyck.test;
import static br.pb.vaneyck.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TesteSincronismo {
	
	private DSL dsl;
	
	@Before  
	public void inicializa() {
		
		System.setProperty("webgetDriver().gecko.driver", "/home/van/Downloads/driver/geckodriver");
	
		// ja deixa o arquivo html dentro da raiz do eclipse
		getDriver().get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After 
	public void finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000); // tem uma espera fixa. Valor em milisegundos
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // desconfigura o ambiente
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		
		// deixa explicito qual campo deve esperar aparecer
		
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
	}
}
