package br.pb.vaneyck.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.pb.vaneyck.core.Propriedades.TipoExecucao;

public class DriverFactory {
	
	//private static WebDriver driver;
	
	// como se cada instancia fosse executada por uma thread diferente
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};
	
	private DriverFactory() {}
	
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public static WebDriver initDriver(){
		
		//System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		WebDriver driver = null;
		
		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			
			switch (Propriedades.browser) {
			case FIREFOX: driver = new FirefoxDriver(); break;
			case CHROME: driver = new ChromeDriver(); break;
			}
		}
		
		// fazendo a conexao com o GRID
		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			
			switch (Propriedades.browser) {
			case FIREFOX: cap = DesiredCapabilities.firefox(); break;
			case CHROME: cap = DesiredCapabilities.chrome(); break;	
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.out.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		
		// fazendo a conexao com a nuvem do saucelabs
		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			DesiredCapabilities cap = null;
			
			switch (Propriedades.browser) {
			case FIREFOX: cap = DesiredCapabilities.firefox(); break;
			case CHROME: cap = DesiredCapabilities.chrome(); break;	
			}
			
			try {			// url do saucelabs composta por: login+chave+url       
				driver = new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.out.println("Falha na conexão com a nuvem");
				e.printStackTrace();
			}
		}
			
			//driver.manage().window().setSize(new Dimension(1200, 765));			
		
		return driver;
	}

	public static void killDriver(){
		WebDriver driver = getDriver();
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		
		// se a thread existir... limpa o drive que a thread estava gerenciando
		if(threadDriver != null) {
			threadDriver.remove();
		}
		
	}
}
