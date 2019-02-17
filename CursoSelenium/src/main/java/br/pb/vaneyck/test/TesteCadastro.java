package br.pb.vaneyck.test;

import static br.pb.vaneyck.core.DriverFactory.getDriver;
import static br.pb.vaneyck.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;
import br.pb.vaneyck.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest{
	
	//private WebDriver driver;
	//private DSL dsl;
	private CampoTreinamentoPage page;
		
	@Before  // antes de cada teste, executa o conteudo desse metodo
	public void inicializa() {
		
	//	System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		// ja deixa o arquivo html dentro da raiz do eclipse
		getDriver().get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		//dsl = new DSL(driver);
		page = new CampoTreinamentoPage();
	}
	
	@Test
	public void deveRealizarCadastroUsuario() {
		
		page.setNome("van eyck");
		page.setSobrenome("silva");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Futebol");
		page.cadastrar();
		
		/*
		Assert.assertEquals("van eyck", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("silva", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
		Assert.assertEquals("Futebol", escolha.getFirstSelectedOption().getText());*/
		
//		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
//		
//		Assert.assertEquals("Cadastrado!\n" + 
//				"Nome: van eyck\n" + 
//				"Sobrenome: silva\n" + 
//				"Sexo: Masculino\n" + 
//				"Comida: Pizza\n" + 
//				"Escolaridade: superior\n" + 
//				"Esportes: Futebol\n" + 
//				"Sugestoes:", driver.findElement(By.id("resultado")).getText());
//		
		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		Assert.assertEquals("van eyck", page.obterNomeCadastro());
		Assert.assertEquals("silva", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Futebol", page.obterEsporteCadastro());
	
		
	}
	
	/*
	@Test
	public void deveValidarNomeObrigatorio() {
		
		page.cadastrar();
		
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
//		Alert alert = driver.switchTo().alert();
//		String msg = alert.getText();
//		
//		Assert.assertEquals("Nome eh obrigatorio", msg);
//		alert.accept();
	
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		
		page.setNome("algo");
		
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("");
		
		page.cadastrar();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
//		Alert alert = driver.switchTo().alert();
//		String msg = alert.getText();
//		
//		Assert.assertEquals("Sobrenome eh obrigatorio", msg);
//		alert.accept();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		
		page.setNome("alguma");
		page.setSobrenome("coisa");
		page.cadastrar();
		
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
//		Alert alert = driver.switchTo().alert();
//		String msg = alert.getText();
//		
//		Assert.assertEquals("Sexo eh obrigatorio", msg);
//		alert.accept();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		
		page.setNome("alguma");
		page.setSobrenome("coisa");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		
		page.setNome("alguma");
		page.setSobrenome("coisa");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");

//		WebElement findElement = driver.findElement(By.id("elementosForm:esportes"));
//		
//		Select combo = new Select(findElement);
//		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
//		
//		combo.selectByVisibleText("Futebol");
//		combo.selectByVisibleText("O que eh esporte?");
		page.cadastrar();
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}*/
}
