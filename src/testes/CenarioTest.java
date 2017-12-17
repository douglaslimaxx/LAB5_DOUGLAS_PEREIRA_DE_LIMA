package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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
		assertEquals(msg, cenario.getFinalizado(), "Não Finalizado");		
	}
	

	@Test
	public void testeApostaApostadorNulo() {
		try {
			cenario.adicionaAposta(null, 1000, "VAI ACONTECER");
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaApostadorVazio() {
		try {
			cenario.adicionaAposta("   ", 1000, "VAI ACONTECER");
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaQuantiaIgualZero() {
		try {
			cenario.adicionaAposta("Douglas", 0, "VAI ACONTECER");
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaQuantiaMenorZero() {
		try {
			cenario.adicionaAposta("Douglas", -5, "VAI ACONTECER");
		} catch (NoSuchElementException qm) {
			assertEquals(qm.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaPrevisaoIncorreta() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "ACONTECER");
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(), "Erro no cadastro de aposta: Previsao invalida");
		}
	}
	
	@Test
	public void testeApostaPrevisaoNula() {
		try {
			cenario.adicionaAposta("Douglas", 1000, null);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaPrevisaoVazia() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "   ");
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCaixaCenarioAumentaAposAdicionarAposta() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER");
		String msg = "Caixa do cenário deve ter o valor de 1000";
		assertEquals(msg, cenario.getCaixaTotal(), 1000);
	}
	
	@Test
	public void testeExibirTodasApostas() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER");
		cenario.adicionaAposta("Douglas", 2000, "VAI ACONTECER");
		String msg = "A lista de aposta deve está da seguinte forma: ";
		assertEquals(msg, cenario.exibeTodasApostas(), "Douglas - R$10,00 - VAI ACONTECER" +  System.lineSeparator()
				+ "Douglas - R$20,00 - VAI ACONTECER" +  System.lineSeparator());
	}
	
	@Test
	public void testeCaixaPerdedores() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER");
		cenario.adicionaAposta("Douglas", 2000, "VAI ACONTECER");
		cenario.setFinalizado("n ocorreu");
		String msg = "A caixa dos perdedores deve ter o valor de 3000";
		assertEquals(msg, cenario.getCaixaPerdedores(), 3000);
	}
	
	@Test
	public void testeToStringFinalizado() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER");
		cenario.adicionaAposta("Douglas", 2000, "VAI ACONTECER");
		cenario.setFinalizado("n ocorreu");
		String msg = "Representação textual de um Cenário deve está da seguinte forma";
		assertEquals(msg, cenario.toString(), "1 - Passar na prova de P2 - Finalizado(n ocorreu)");
	}
	
	@Test
	public void testeToStringNaoFinalizado() {
		String msg = "Representação textual de um Cenário deve está da seguinte forma";
		assertEquals(msg, cenario.toString(), "1 - Passar na prova de P2 - Não finalizado");
	}
}
