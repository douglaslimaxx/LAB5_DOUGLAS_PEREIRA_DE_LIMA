package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.Aposta;

public class ApostaTest {

	private Aposta aposta;

	@Before
	public void setUp() {
		aposta = new Aposta("Douglas", 1000, "VAI ACONTECER");
	}
	
	@Test
	public void testeApostadorCorreto() {
		String msg = ("O nome do apostador deve ser Douglas");
		assertEquals(msg, aposta.getApostador(), "Douglas");
	}
	
	@Test
	public void testeQuantiaCorreta() {
		String msg = ("A quantia apostada deve ser 1000");
		assertEquals(msg, aposta.getQuantia(), 1000);
	}
	
	@Test
	public void testePrevisaoCorreta() {
		String msg = ("Previsão da aposta deve ser VAI ACONTECER");
		assertEquals(msg, aposta.getPrevisao(), "VAI ACONTECER");
	}
	
	@Test
	public void testeToString() {
		String msg = ("Representação textual de aposta deve ser da seguinte forma:"
				+ "Douglas - R$10,00 - VAI ACONTECER");
		assertEquals(msg, aposta.toString(), "Douglas - R$10,00 - VAI ACONTECER");
	}
	
	@Test
	public void testeApostaApostadorNulo() {
		try {
			Aposta aposta = new Aposta(null, 1000, "VAI ACONTECER");
			if (aposta.getApostador() == null) {
				fail("Apostador não pode ser nulo");
			}
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaApostadorVazio() {
		try {
			Aposta aposta = new Aposta("   ", 1000, "VAI ACONTECER");
			if (aposta.getApostador().trim().equals("")) {
				fail("Apostador não pode ser vazio");
			}
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeApostaQuantiaIgualZero() {
		try {
			Aposta aposta = new Aposta("Douglas", 0, "VAI ACONTECER");
			if (aposta.getQuantia() == 0) {
				fail("Quantia apostada não pode ser 0");
			}
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaQuantiaMenorZero() {
		try {
			Aposta aposta = new Aposta("Douglas", -5, "VAI ACONTECER");
			if (aposta.getQuantia() == 0) {
				fail("Quantia apostada não pode ser menor que 0");
			}
		} catch (NoSuchElementException qm) {
			assertEquals(qm.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeApostaPrevisaoIncorreta() {
		try {
			Aposta aposta = new Aposta("Douglas", 1000, "ACONTECER");
			if (!aposta.getPrevisao().equals("N VAI ACONTECER") && !aposta.getPrevisao().equals("VAI ACONTECER")) {
				fail("Previsão não pode ser diferente de 'VAI ACONTECER' ou 'N VAI ACONTECER'");
			}
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(), "Erro no cadastro de aposta: Previsao invalida");
		}
	}
	
	@Test
	public void testeApostaPrevisaoNula() {
		try {
			Aposta aposta = new Aposta("Douglas", 1000, null);
			if (aposta.getPrevisao() == null) {
				fail("Previsão não pode ser nula");
			}
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeApostaPrevisaoVazia() {
		try {
			Aposta aposta = new Aposta("Douglas", 1000, "   ");
			if (aposta.getPrevisao().trim().equals("")) {
				fail("Previsão não pode ser vazia");
			}
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
}
