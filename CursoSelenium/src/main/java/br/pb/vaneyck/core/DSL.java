package br.pb.vaneyck.core;
import static br.pb.vaneyck.core.DriverFactory.getDriver;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	/***** TextField e TextArea ******/
	
	public void escrever(By by, String texto) {
		
		getDriver().findElement(by).clear(); // limpa o campo antes de escrever
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escrever(String id, String texto) {
		
		escrever(By.id(id),texto);	
	}
	

	public String obterValorCampo(String id) {
		
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	
	/** Radio e Check **/
	
	public void clicarRadio(By by) {
		
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {

		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id) {
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		
		getDriver().findElement(By.id(id)).click();
	}

	public boolean isCheckMarcado(String id) {
		
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/** Combo **/
	
	public void selecionarCombo(String id, String valor) {
		
		WebElement element = getDriver().findElement(By.id(id));
		
		// objeto que seleciona uma opcao do combo baseado no id do objeto element
		Select combo = new Select(element);
		
		/*seleciona elemento por id
		combo.selectByIndex(3);
		 seleciona pelo valor do html
		combo.selectByValue("superior");*/
		// seleciona pela texto que esta visivel no como
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		
		WebElement element = getDriver().findElement(By.id(id)); 
		
		Select combo = new Select(element);
		
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {

		WebElement element = getDriver().findElement(By.id(id));
		
		// objeto que seleciona uma opcao do combo baseado no id do objeto element
		Select combo = new Select(element);
		
		return combo.getFirstSelectedOption().getText();
	}
	
	
	public List<String> obterValoresCombo(String id) {
		
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(String id, String opcao) {
		
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		for (WebElement webElement : options) {
			if (webElement.getText().equals(opcao)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
		
	}
	
	
	/** Botao **/
	
	public void clicarBotao(String id) {
		
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id) {
		
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	/** Link **/
	
	public void clicarLink(String link) {
		
		getDriver().findElement(By.linkText(link)).click();
	}
	
	/** Textos **/
	
	public String obterTexto(String id) {
		
		return obterTexto(By.id(id));
		
	}
	
	public String obterTexto(By by) {
		
		return getDriver().findElement(by).getText();
		
	}
	
	/** Alerts **/

	public String alertaObterTexto() {
		
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita() {
		
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}

	public String alertaObterTextoENega() {
		
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}

	public void alertaEscrever(String valor) {
		
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/** Frames e Janelas **/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}

	public void sairFrame() {
		getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
		
	/*** JS ***/
	
	public Object executarJS(String cmd, Object... param) {
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		
		// interagindo com um elemento
		return js.executeScript(cmd, param);
		
//		js.executeScript("alert('Testando js via selenium')");
//		js.executeScript("document.getElementById('elementosForm:nome').value = 'escrita via js'");
//		
//		// faz uma busca pelo elemento
//		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
	
	}
	
	/*** Tabela ***/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		
														// .(ponto) referencia o diretorio corrente
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		
		celula.findElement(By.xpath(".//input")).click();
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		
		return idColuna;
	}
	
	
	
}
