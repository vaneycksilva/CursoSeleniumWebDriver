package br.pb.vaneyck.test;
import static br.pb.vaneyck.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TesteAlert {
	
	private DSL dsl;
		
		@Before  // antes de cada teste, executa o conteudo desse metodo
		public void inicializa() {
			
			System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
			// ja deixa o arquivo html dentro da raiz do eclipse
			getDriver().get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
			dsl = new DSL();
		}
		
		@After // é executado apos cada um dos testes
		public void finaliza() {
			DriverFactory.killDriver();
		}
	
	
	@Test
	public void deveInteragirComAlertSimples() {
		
		dsl.clicarBotao("alert");
		
		// altera foco para alert
	//	Alert alert = driver.switchTo().alert();
		
		String texto = dsl.alertaObterTextoEAceita();
		
		// checa a mensagem do alert
		Assert.assertEquals("Alert Simples", texto);
		
		// escreva a mensagem que veio no alert em um campo do formulario
		dsl.escrever("elementosForm:nome", texto);
		
	}
	
	@Test
	@Ignore
	public void deveInteragirComConfirm() {
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
	
		// NEGADO
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
		
		/*
		driver.findElement(By.id("confirm")).click();

		String negado = alert.getText();
		
		Assert.assertEquals("Confirm Simples", negado);
		
		wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
		
		Assert.assertEquals("Negado", negado);
		
		wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();*/
		
	}
	
	@Test
	@Ignore
	public void deveInteragirComPrompt() {
		
		dsl.clicarBotao("prompt");		
		
		//Alert alerta = driver.switchTo().alert();
		
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		
		dsl.alertaEscrever("12");
		
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
		
	}
}
