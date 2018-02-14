package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.Taxa;

public class TaxaTest {

	private Taxa taxa;

	@Before
	public void setUp() {
		taxa = new Taxa(4000, 0.1);
	}

	@Test
	public void testeTaxaZero() {
		try {
			taxa = new Taxa(5000, 0.0);
		} catch (NoSuchElementException tz) {
			assertEquals(tz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeTaxaMenorZero() {
		try {
			taxa = new Taxa(5000, -0.09);
		} catch (NoSuchElementException tmz) {
			assertEquals(tmz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeGetValor() {
		String msg = "O valor do seguro deve ser de 400";
		assertEquals(msg, 400, taxa.getValor());
	}
	
	@Test
	public void testeToString() {
		String msg = "A representação textual de uma taxa deve ser seguinte forma: ";
		assertEquals(msg, " - ASSEGURADA (TAXA) - 10%", taxa.toString());
	}

}
