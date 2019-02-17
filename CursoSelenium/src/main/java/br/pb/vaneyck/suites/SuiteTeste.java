package br.pb.vaneyck.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.pb.vaneyck.core.DriverFactory;
import br.pb.vaneyck.test.TesteCadastro;
import br.pb.vaneyck.test.TesteCampoTreinamento;
import br.pb.vaneyck.test.TesteRegrasCadastro;

/**
 * agrupa classes testes para serem executadas
 * em conjuto
 * */

//definicao das classes que serao executadas
@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class
})

public class SuiteTeste {

	// executado após toda a classe acabar
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
}
