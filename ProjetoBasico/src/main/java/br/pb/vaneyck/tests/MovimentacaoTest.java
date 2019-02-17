package br.pb.vaneyck.tests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.pb.vaneyck.core.BaseTest;
import br.pb.vaneyck.core.Propriedades;
import br.pb.vaneyck.pages.MenuPage;
import br.pb.vaneyck.pages.MovimentacaoPage;
import br.pb.vaneyck.utils.DataUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTest extends BaseTest{

	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
	
	@Test
	public void test1_InserirMovimentacao() {
		
		menuPage.acessarTelaInserirMovimentacao();
		
		movimentacaoPage.setDataMovimentacao(DataUtils.obterDataFormatada(new Date()));
		movimentacaoPage.setDataPagamento(DataUtils.obterDataFormatada(new Date()));
		movimentacaoPage.setDescricao("Movimentação do teste");
		movimentacaoPage.setInteressado("Interessado qualquer");
		movimentacaoPage.setValor("500");
		movimentacaoPage.setConta("Conta para movimentacoes");
		movimentacaoPage.setStatusPago();
		movimentacaoPage.salvar();
		
		Assert.assertEquals("Movimentação adicionada com sucesso!", movimentacaoPage.obterMensagemSucesso());
	}
	
	
	@Test
	public void test2_CamposObrigatorios() {
		
		menuPage.acessarTelaInserirMovimentacao();
		movimentacaoPage.salvar();
		
		List<String> erros = movimentacaoPage.obterErros();
		
		/*** verificando os erros ***/
		
		// verifica apenas uma frase por vez e na ordem
		// Assert.assertEquals("Data da Movimentação é obrigatório", erros.get(0));
		
		/* verifica se a frase esta aparecendo na mensagem de erro. Independe da ordem, mas ainda precisa fazer pra
		cada erro um assert
		Assert.assertTrue(erros.contains("Data da Movimentação é obrigatório"));*/
		
		// passar uma lista de erros, e ele verifica se as frases passadas estão nos erros apresentados
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número"
				)));
		Assert.assertEquals(6, erros.size());
	}
	
	@Test
	public void test3_MovimentacaoFutura() {
		
		menuPage.acessarTelaInserirMovimentacao();
		
		Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		
		movimentacaoPage.setDataMovimentacao(DataUtils.obterDataFormatada(dataFutura));
		movimentacaoPage.setDataPagamento(DataUtils.obterDataFormatada(dataFutura));
		movimentacaoPage.setDescricao("Movimentação do teste");
		movimentacaoPage.setInteressado("Interessado qualquer");
		movimentacaoPage.setValor("500");
		movimentacaoPage.setConta("Conta para movimentacoes");
		movimentacaoPage.setStatusPago();
		movimentacaoPage.salvar();
		
		List<String> erros = movimentacaoPage.obterErros();
		
		Assert.assertTrue(erros.contains("Data da Movimentação deve ser menor ou igual à data atual"));
		Assert.assertEquals(1, erros.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
