package br.pb.vaneyck.test;
import static br.pb.vaneyck.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TesteFramesEJanelas {
	
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
	public void deveInteragirComFrames() {
		
		// muda o foco para o frame
		//driver.switchTo().frame("frame1");
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		
		// muda o foco para o alert
		//Alert alert = driver.switchTo().alert();
		
		String msg = dsl.alertaObterTextoEAceita();
		
		Assert.assertEquals("Frame OK!", msg);
	
	//	driver.switchTo().defaultContent();
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
		
	}	
	
	@Test
	public void deveInteragirComFrameEscondido() {
		
		WebElement frame = getDriver().findElement(By.id("frame2"));
		
		// desce a página até onde está o botão
		dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		
		dsl.entrarFrame("frame2");
		
		dsl.clicarBotao("frameButton");
		
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		
		dsl.clicarBotao("buttonPopUpEasy");
		
		//trocar o foco para uma popup na qual conheco o titulo
		//driver.switchTo().window("Popup");
		dsl.trocarJanela("Popup");
		
		//escreve na textarea do popup
		//driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		dsl.escrever(By.tagName("textarea"), "Deu certo!");
		
		// fecha apenas a janela do popup
		getDriver().close();
		
		//volta o foco na pagina principal
		//driver.switchTo().window("");
		dsl.trocarJanela("");
		
		//escreve na textarea da pagina principal
		//driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
		dsl.escrever(By.tagName("textarea"), "e agora?");
		//Assert.assertEquals("e agora?", dsl.obterValueElemento("textarea"));
	}
	

	@Test
	public void deveInteragirComJanelasSemTitulo() {
		
		dsl.clicarBotao("buttonPopUpHard");
		
		System.out.println(getDriver().getWindowHandle());  // retorna a janela atual
		System.out.println(getDriver().getWindowHandles()); // retorna todas as janelas que estão sendo gerenciadas
		
		// muda o foco para a popup sem titulo atraves do id
		//driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[1]);
		
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		
		// volta o foco na pagina principal
		//driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[0]);
		
		dsl.escrever("elementosForm:sugestoes", "e agora?");
		
	}
}
