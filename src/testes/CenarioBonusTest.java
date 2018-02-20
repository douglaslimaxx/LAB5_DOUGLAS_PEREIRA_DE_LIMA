package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.CenarioBonus;

public class CenarioBonusTest {

	private CenarioBonus cenario;

	@Before
	public void setUp() {
		cenario = new CenarioBonus("Passar em Discreta", 1, 50);
	}
	
	@Test
	public void testeCenarioBonusZero() {
		try {
			cenario = new CenarioBonus("Passar em Discreta", 1, 0);
		} catch (NoSuchElementException bz) {
			assertEquals(bz.getMessage(), "Erro no cadastro de cenario: Bonus invalido");
		}
	}
	
	@Test
	public void testeCenarioBonusMenorZero() {
		try {
			cenario = new CenarioBonus("Passar em Discreta", 1, -50);
		} catch (NoSuchElementException bmz) {
			assertEquals(bmz.getMessage(), "Erro no cadastro de cenario: Bonus invalido");
		}
	}
	
	@Test
	public void testeSetRateio() {
		cenario.setRateio(10);
		String msg = "Rateio desse cenário deve ser de 60";
		assertEquals(msg, 60, cenario.getRateio());
	}
	
	@Test
	public void testeToStringFinalizado() {
		cenario.setFinalizado("n ocorreu");
		String msg = "Representação textual de um Cenário deve está da seguinte forma";
		assertEquals(msg, cenario.toString(), "1 - Passar em Discreta - Finalizado (n ocorreu) - R$ 0,50");
	}
	
	@Test
	public void testeToStringNaoFinalizado() {
		String msg = "Representação textual de um Cenário deve está da seguinte forma";
		assertEquals(msg, cenario.toString(), "1 - Passar em Discreta - Nao finalizado - R$ 0,50");
	}
}
