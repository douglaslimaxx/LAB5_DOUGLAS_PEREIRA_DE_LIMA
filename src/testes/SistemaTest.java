package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.Sistema;

public class SistemaTest {

	private Sistema sistema;
	
	@Before
	public void setUp() {
		sistema = new Sistema(10000, 0.001);
	}
	
	@Test
	public void testsSistemaValorInicialIgualZero() {
		try {
			sistema = new Sistema(0, 0.001);
			if (sistema.getCaixa() == 0) {
				fail("Caixa inicial não pode iniciar com valor 0");
			}
			} catch (NoSuchElementException vn) {
				assertEquals(vn.getMessage(), "Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
	}

}
