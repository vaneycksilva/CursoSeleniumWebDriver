package br.pb.vaneyck.core;

import static br.pb.vaneyck.core.DriverFactory.getDriver;
import static br.pb.vaneyck.core.DriverFactory.killDriver;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.pb.vaneyck.pages.LoginPage;

public class BaseTest {
	
	@Rule
	public TestName testName = new TestName();
	
	private LoginPage page = new LoginPage();
	
	// assim cada teste vai ser executado em um browser
	@Before
	public void inicializa() {
		
		page.acessarPagina();
		page.setEmail("vaneyck23@gmail.com");
		page.setSenha("123456");
		page.entrar();
		
	}
	
	@After
	public void finaliza() throws IOException{
		
		TakesScreenshot sc = (TakesScreenshot)getDriver();
		File arquivo = sc.getScreenshotAs(OutputType.FILE);
		
									// pra onde o arquivo deve ir, com o nome de cada método
		FileUtils.copyFile(arquivo, new File("target"+File.separator+"screenshot"+File.separator+
				testName.getMethodName()+".jpg"));
		
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
	
//	TakesScreenshot ss = (TakesScreenshot) getDriver();
//	File arquivo = ss.getScreenshotAs(OutputType.FILE);
//	FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
//	File.separator + testName.getMethodName() + ".jpg"));
	

}
