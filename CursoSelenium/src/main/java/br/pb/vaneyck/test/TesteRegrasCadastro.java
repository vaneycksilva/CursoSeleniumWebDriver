package br.pb.vaneyck.test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static br.pb.vaneyck.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.DSL;
import br.pb.vaneyck.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {

	private DSL dsl;
	private CampoTreinamentoPage page;
	
	/** campos dos testes**/
	@Parameter
	public String nome;
	
	@Parameter(value=1)
	public String sobrenome;
	
	@Parameter(value=2)
	public String sexo;
	
	@Parameter(value=3)
	public List<String> comidas;
	
	@Parameter(value=4)
	public String[] esportes;
	
	@Parameter(value=5)
	public String msg;
		
	@Before  // antes de cada teste, executa o conteudo desse metodo
	public void inicializa() {
		
		System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		// ja deixa o arquivo html dentro da raiz do eclipse
		getDriver().get("file:///"+System.getProperty("user.dir")+"/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	/**
	 * inserindo os dados
	 * cada linha dentro da coleção, será um conjunto de dados
	 * que irá passar pela estrutura genérica, ou seja irá ser
	 * um novo cenário
	 * */
	@Parameters
	public static Collection<Object[]> getCollections(){
		
		return Arrays.asList(new Object[][] {
			
			// cria todo um cenário de uma vez
			{"","","",Arrays.asList(),new String[]{},"Nome eh obrigatorio"},
			{"van","","", Arrays.asList(), new String[]{},"Sobrenome eh obrigatorio"},
			{"van","silva","", Arrays.asList(), new String[]{},"Sexo eh obrigatorio"},
			{"van","silva","Masculino", Arrays.asList("Carne","Vegetariano"), new String[]{},"Tem certeza que voce eh vegetariano?"},
			{"van","silva","Masculino", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"},"Voce faz esporte ou nao?"}
		});
	}
	
	
	/** parametrizando valores 
	 * 
	 * Data Driven Test = teste orientado a dados
	 * **/
	@Test
	public void deveValidarRegras() {
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if (sexo.equals("Masculino")) page.setSexoMasculino();
		if (sexo.equals("Feminino"))	page.setSexoFeminino();
		
		if (comidas.contains("Carne")) page.setComidaCarne();
		if (comidas.contains("Pizza")) page.setComidaPizza();
		if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
		
		page.setEsporte(esportes);
		page.cadastrar();
		System.out.println(msg);
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}	
	
}
