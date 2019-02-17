package br.pb.vaneyck.pages;

import org.openqa.selenium.By;

import br.pb.vaneyck.core.BasePage;

public class HomePage extends BasePage {
	
	public String obterSaldoConta(String conta) {
	
		return obterCelula("Conta", conta, "Saldo", "tabelaSaldo").getText();

	}
}
