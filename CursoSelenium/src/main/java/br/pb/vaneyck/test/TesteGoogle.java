package br.pb.vaneyck.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
private WebDriver driver;
	
	@Before  // antes de cada teste, executa o conteudo desse metodo
	public void inicializa() {
		
		System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		driver = new FirefoxDriver();
		
		// ja deixa o arquivo html dentro da raiz do eclipse
		driver.get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
	}
	
	@After // é executado apos cada um dos testes
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void teste() {
	//	System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
	  System.setProperty("webdriver.chrome.driver", "/home/van/Downloads/driver/chromedriver");	
	//	WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		driver.get("http://google.com");
		Assert.assertEquals("Google",driver.getTitle());
		driver.quit();
	}

}
