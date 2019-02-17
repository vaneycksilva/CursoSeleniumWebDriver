package br.pb.vaneyck.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;
	
	private DriverFactory() {}
	
	// quem quiser acessar essa classe, tem que ser através desse método
	public static WebDriver getDriver() {
		
	
		if (driver == null) {
			
			//System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
			driver = new FirefoxDriver();
		}
		
		return driver;
	}
	
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
