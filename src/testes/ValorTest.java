package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.Valor;

public class ValorTest {

	private Valor valor;

	@Before
	public void setUp() {
		valor = new Valor(500);
	}
	
	@Test
	public void testeValorZero() {
		try {
			valor = new Valor(0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeValorMenorZero() {
		try {
			valor = new Valor(-600);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeGetValor() {
		String msg = "O valor do seguro deve ser 500";
		assertEquals(msg, 500, valor.getValor());
	}
	
	@Test
	public void testToString() {
		String msg = "A representação textual de um Valor deve ser da seguinte forma:";
		assertEquals(msg,  " - ASSEGURADA (VALOR) - R$ 500,00", valor.toString());
	}

}
