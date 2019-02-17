package br.pb.vaneyck.pages;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.pb.vaneyck.core.BasePage;
import br.pb.vaneyck.core.DriverFactory;

public class MovimentacaoPage extends BasePage {
	
	
	@Test
	public void setDataMovimentacao(String data) {
		escrever("data_transacao", data);
	}

	@Test
	public void setDataPagamento(String data) {
		escrever("data_pagamento", data);
	}
	
	@Test
	public void setDescricao(String descricao) {
		escrever("descricao", descricao);
	}
	
	@Test
	public void setInteressado(String interessado) {
		escrever("interessado", interessado);
	}
	
	@Test
	public void setValor(String valor) {
		escrever("valor", valor);
	}
	
	@Test
	public void setConta(String conta) {
		selecionarCombo("conta", conta);
	}
	
	@Test
	public void setStatusPago() {
		clicarRadio("status_pago");
	}
	
	public void salvar() {
		
		clicarBotaoPorTexto("Salvar");
	}
	
	public String obterMensagemSucesso() {
		
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
		
	}
	
	public List<String> obterErros(){
		List<WebElement> erros = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
		
		// convertendo de webelement para string
		List<String> retorno = new ArrayList<String>();
		for (WebElement erro : erros) {
			retorno.add(erro.getText());
		}
		return retorno;
	}
}
