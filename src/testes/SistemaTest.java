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
	public void testeSistemaValorInicialIgualZero() {
		try {
			sistema = new Sistema(0, 0.001);
			if (sistema.getCaixa() == 0) {
				fail("Caixa inicial não pode iniciar com valor 0");
			}
			} catch (NoSuchElementException vz) {
				assertEquals(vz.getMessage(), "Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
	}
	
	@Test
	public void testeSistemaValorInicialMenorZero() {
		try {
			sistema = new Sistema(-500, 0.001);
			if (sistema.getCaixa() < 0) {
				fail("Caixa inicial não pode iniciar com valor menor que 0");
			}
			} catch (NoSuchElementException vm) {
				assertEquals(vm.getMessage(), "Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
	}
	
	@Test
	public void testeSistemaTaxaIgualZero() {
		try {
			sistema = new Sistema(500, 0.00);
			if (sistema.getTaxa() == 0.00) {
				fail("Taxa do sistema não pode ter valor 0");
			}
		} catch (NoSuchElementException tz) {
			assertEquals(tz.getMessage(), "Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
	}
	
	@Test
	public void testeSistemaTaxaMenorZero() {
		try {
			sistema = new Sistema(500, -0.02);
			if (sistema.getTaxa() < 0.00) {
				fail("Taxa do sistema não pode ter valor menor que 0");
			}
		} catch (NoSuchElementException tm) {
			assertEquals(tm.getMessage(), "Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
	}
	
	@Test
	public void testeCadastraCenario
}
