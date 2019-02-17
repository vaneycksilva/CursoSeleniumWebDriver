package br.pb.vaneyck.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static br.pb.vaneyck.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.core.DriverFactory;

public class TesteCampoTreinamento {
	
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
	public void testeTextField() {
		
		// escreve no html no id especifico
		dsl.escrever("elementosForm:nome", "teste de escrita");
		// retorna o texto que ta dentro do id
		// driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
		
		Assert.assertEquals("teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextFieldDuplo() {
		
		dsl.escrever("elementosForm:nome", "van");
		Assert.assertEquals("van", dsl.obterValorCampo("elementosForm:nome"));

		dsl.escrever("elementosForm:nome", "eyck");
		Assert.assertEquals("eyck", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	
	@Test
	public void deveInteragirComTextArea() {
		
		dsl.escrever("elementosForm:sugestoes", "testa textarea");
		Assert.assertEquals("testa textarea", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
	
		// clica no elemento
		dsl.clicarRadio("elementosForm:sexo:0");
		
		// verifica se o item foi realmente clicado
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));	
	}
	
	@Test
	public void deveInteragirComCheckBox() {
	
		// clica no elemento
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
		
		// verifica se o item foi realmente clicado
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	}
	
	@Test
	public void deveInteragirComComboBox() {

		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		
		// verifica se é esse elemento que esta realmente selecionado
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresComboBox() {
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
		
		/*
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		
		// objeto que seleciona uma opcao do combo baseado no id do objeto element
		Select combo = new Select(element);
		
		// retorna uma lista com todas as opcoes do combo
		List<WebElement> options = combo.getOptions();
		
		// quantidade de opcoes que possui
		Assert.assertEquals(8, options.size());
		
		// verificar se tem determinada opcao
		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		
		Assert.assertTrue(encontrou);*/
	}
	
	@Test
	public void deveVerificarValoresComboBoxMultiplasEscolhas() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", 
				"O que eh esporte?")));
		
		/*
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		
		// objeto que seleciona uma opcao do combo baseado no id do objeto element
		Select combo = new Select(element);
		
		// desmarcar uma opcao
		combo.deselectByVisibleText("Corrida");
		
		// retorna a lista dos elementos selecionados
		List<WebElement> elements = combo.getAllSelectedOptions();
		
		Assert.assertEquals(2, elements.size());*/
	}
	
	
	@Test
	public void deveInteragirComBotoes() {
		
		dsl.clicarBotao("buttonSimple");
		
		//WebElement botao = driver.findElement(By.id("buttonSimple")); 
		
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
	//@Ignore // não executa esse teste    
	public void deveInteragirComLinks() {
		
		// clica no link "Voltar"
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		
		/* ajuda a não deixar um falso positivo na bateria de testes
		Assert.fail();*/
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		
		/*retorna todo o texto da pagina
		driver.findElement(By.tagName("body")).getText();*/
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	// comandos javascript
	@Test
	public void testJavascript() {
		
		// faz uma busca pelo elemento
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		dsl.executarJS("arguments[0].style.border = arguments[1]",
				element, "solid 4px red");
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		//js.executeScript("alert('Testando js via selenium')");
//		js.executeScript("document.getElementById('elementosForm:nome').value = 'escrita via js'");
//		
//		// faz uma busca pelo elemento
//		WebElement element = driver.findElement(By.id("elementosForm:nome"));
//		
//		// interagindo com um elemento
//		js.executeScript("arguments[0].style.border = arguments[1]",
//				element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
