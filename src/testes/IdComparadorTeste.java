package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelos.Cenario;
import modelos.IdComparador;

public class IdComparadorTeste {

	
	private IdComparador Comparator;
	private Cenario Cenario1;
	private Cenario Cenario2;

	@Before
	public void setUp() {
		Comparator = new IdComparador();
	}
	
	@Test
	public void TesteIdCenario1Primeiro() {
		Cenario1 = new Cenario("Morar na Irlanda", 1);
		Cenario2 = new Cenario("Passar em Discreta", 2);
		String msg = "O retorno do método deve ser um número menor que zero";
		assertTrue(msg, this.Comparator.compare(Cenario1, Cenario2) < 0);
	}
	
	@Test
	public void TesteIdCenario2Primeiro() {
		Cenario1 = new Cenario("Morar na Irlanda", 2);
		Cenario2 = new Cenario("Passar em Discreta", 1);
		String msg = "O retorno do método deve ser um número maior que zero";
		assertTrue(msg, this.Comparator.compare(Cenario1, Cenario2) > 0);
	}
	
	@Test
	public void TesteIdIguais() {
		Cenario1 = new Cenario("Morar na Irlanda", 1);
		Cenario2 = new Cenario("Passar em Discreta", 1);
		String msg = "O retorno do método deve ser um número igual a zero";
		assertTrue(msg, this.Comparator.compare(Cenario1, Cenario2) == 0);
	}
	

}
