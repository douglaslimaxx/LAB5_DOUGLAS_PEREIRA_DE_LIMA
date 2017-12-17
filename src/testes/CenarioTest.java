package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modelos.Cenario;

public class CenarioTest {
	
	private Cenario cenario;

	@Before
	public void setUp() {
		cenario = new Cenario("Passar na prova de P2", 1);
	}

	@Test
	public void testeCenarioDescricaoNula() {
		try {
			Cenario cenario = new Cenario(null, 1);
			if (cenario.getDescricao() == null) {
				fail("Descrição não pode ser nula");
			}
		} catch (NullPointerException dn) {
			assertEquals(dn.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser nula");
		}
	}

	@Test
	public void testeCenarioDescricaoVazia() {
		try {
			Cenario cenario = new Cenario("   ", 1);
			if (cenario.getDescricao().trim().equals("")) {
				fail("Descrição não pode ser vazia");
			}
		} catch (IllegalArgumentException dv) {
			assertEquals(dv.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}
	
	@Test
	public void testeOcorreuIniciaVazio() {
		String msg = "Ocorreu deve iniciar vazio";
		assertEquals(msg, cenario.getOcorreu(), "");
	}
	
	@Test
	public void testeArrayListaDeApostaIniciaVazio() {
		String msg = "ArrayList de Aposta deve iniciar vazio";
		assertEquals(msg, cenario.getApostas().size(), 0);
	}
	
	@Test
	public void testeCenarioIniciaNaoFinalizado() {
		String msg = "Cenário deve iniciar não finalizado";
		assertEquals(msg, cenario.getFinalizado(), "Nao Finalizado");		
	}
}
