package br.pb.vaneyck.core;

import static br.pb.vaneyck.core.DriverFactory.getDriver;

import java.io.File;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.OutputType;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import java.io.File;
//import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	
	TakesScreenshot ts = (TakesScreenshot)getDriver();
	File arquivo = ts.getScreenshotAs(OutputType.FILE);
	
	
	
 	@After // é executado apos cada um dos testes
	public void finaliza() {
		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();	
		}
		
	}
	
}
