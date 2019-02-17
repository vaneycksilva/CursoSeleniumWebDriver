package br.pb.vaneyck.pages;

import org.openqa.selenium.By;

import br.pb.vaneyck.core.BasePage;
import br.pb.vaneyck.core.DriverFactory;

public class LoginPage extends BasePage{

	public void acessarPagina() {
		
		System.setProperty("webdriver.gecko.driver", "/home/van/Downloads/driver/geckodriver");
		
		DriverFactory.getDriver().get("https://seubarriga.wcaquino.me");
	}
	
	public void setEmail(String email) {
		
		escrever("email", email);
	}
	
	public void setSenha(String senha) {
		
		escrever("senha", senha);
	}
	
	public void entrar() {
		clicarBotaoPorTexto("Entrar");
	}
	
	// poderia ser assim tbm
	public void logar(String email, String senha) {
		setEmail(email);
		setSenha(senha);
		entrar();
	}
	
	public void resetar() {
		clicarLink("reset");
	}
	
}
