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
	
	//Testes com construtor de cenário
	
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
	public void testeArrayListaDeApostaAsseguradasIniciaVazio() {
		String msg = "ArrayList de Aposta asseguradas deve iniciar vazio";
		assertEquals(msg, cenario.getApostasAsseguradas().size(), 0);
	}
	
	@Test
	public void testeCenarioIniciaNaoFinalizado() {
		String msg = "Cenário deve iniciar não finalizado";
		assertEquals(msg, cenario.getFinalizado(), "Nao finalizado");		
	}
	
	//Testes com aposta não assegurada
	
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

	//Testes com aposta assegurada por valor
	
	@Test
	public void testeApostaAsseguradaValorApostadorNulo() {
		try {
			cenario.adicionaAposta(null, 1000, "VAI ACONTECER", 200);
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(), "Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorApostadorVazio() {
		try {
			cenario.adicionaAposta("   ", 1000, "VAI ACONTECER", 200);
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(), "Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorQuantiaIgualZero() {
		try {
			cenario.adicionaAposta("Douglas", 0, "VAI ACONTECER", 200);
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorQuantiaMenorZero() {
		try {
			cenario.adicionaAposta("Douglas", -5, "VAI ACONTECER", 200);
		} catch (NoSuchElementException qm) {
			assertEquals(qm.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorPrevisaoIncorreta() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "ACONTECER", 200);
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(), "Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorPrevisaoNula() {
		try {
			cenario.adicionaAposta("Douglas", 1000, null, 200);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(), "Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorPrevisaoVazia() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "   ", 200);
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(), "Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorZero() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorMenorZero() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", -50);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaValorCaixaAumenta() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 50);
		String msg = "Caixa do cenário deve ter 1000";
		assertEquals(msg, 1000, cenario.getCaixaTotal());
	}
	
	@Test
	public void testeApostaAsseguradaValorRetornaId() {
		String msg = "Método deve retornar o id da aposta assegurada";
		assertEquals(msg, 0, cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 50));
	}
	
	//Teste com aposta assegurada por taxa

	@Test
	public void testeApostaAsseguradaTaxaApostadorNulo() {
		try {
			cenario.adicionaAposta(null, 1000, "VAI ACONTECER", 0.02);
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaApostadorVazio() {
		try {
			cenario.adicionaAposta("   ", 1000, "VAI ACONTECER", 0.02);
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaQuantiaIgualZero() {
		try {
			cenario.adicionaAposta("Douglas", 0, "VAI ACONTECER", 0.02);
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaQuantiaMenorZero() {
		try {
			cenario.adicionaAposta("Douglas", -5, "VAI ACONTECER", 0.02);
		} catch (NoSuchElementException qm) {
			assertEquals(qm.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaPrevisaoIncorreta() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "ACONTECER", 0.02);
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaPrevisaoNula() {
		try {
			cenario.adicionaAposta("Douglas", 1000, null, 0.02);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaPrevisaoVazia() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "   ", 0.02);
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaZero() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 0.0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaMenorZero() {
		try {
			cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", -0.02);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaAsseguradaTaxaCaixaAumenta() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 0.02);
		String msg = "Caixa do cenário deve ter 1000";
		assertEquals(msg, 1000, cenario.getCaixaTotal());
	}
	
	@Test
	public void testeApostaAsseguradaTaxaRetornaId() {
		String msg = "Método deve retornar o id da aposta assegurada";
		assertEquals(msg, 0, cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 0.02));
	}
	
	//Teste gerais com apostas
	
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
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER", 0.02);
		String msg = "A lista de aposta deve está da seguinte forma: ";
		assertEquals(msg, cenario.exibeTodasApostas(), "Douglas - R$10,00 - VAI ACONTECER" +  System.lineSeparator()
				+ "Douglas - R$20,00 - VAI ACONTECER" +  System.lineSeparator() 
				+ "Douglas - R$10,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 2%" +  System.lineSeparator());
	}
	
	@Test
	public void testeCaixaPerdedores() {
		cenario.adicionaAposta("Douglas", 1000, "VAI ACONTECER");
		cenario.adicionaAposta("Douglas", 2000, "VAI ACONTECER");
		cenario.setFinalizado("n ocorreu");
		String msg = "A caixa dos perdedores deve ter o valor de 3000";
		assertEquals(msg, cenario.getCaixaPerdedores(), 3000);
	}
	
	//Testes toString
	
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
		assertEquals(msg, cenario.toString(), "1 - Passar na prova de P2 - Nao finalizado");
	}
}
