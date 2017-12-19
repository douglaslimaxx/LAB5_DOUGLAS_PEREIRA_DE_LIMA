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
	public void testeArrayListCenarioIniciaVazio() {
		String msg = "O Arraylist de objetos do tipo Cenario deve iniciar vazio";
		assertEquals(msg, sistema.getCenarios().size(), 0);
	}

	@Test
	public void testeCadastraCenarioDescricaoNula() {
		try {
			sistema.cadastraCenario(null);
		} catch (NullPointerException dn) {
			assertEquals(dn.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser nula");
		}
	}

	@Test
	public void testeCadastraCenarioDescricaoVazia() {
		try {
			sistema.cadastraCenario("    ");
		} catch (IllegalArgumentException dv) {
			assertEquals(dv.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}

	@Test
	public void testeCadastraCenarioArraylistDeveAumentar() {
		sistema.cadastraCenario("Passar em Discreta");
		String msg = "ArrayList de objetos do tipo Cenario deve conter um Cenario";
		assertEquals(msg, sistema.getCenarios().size(), 1);
	}

	@Test
	public void testeExibirCenarioNumeracaoIgualZero() {
		try {
			sistema.exibeUmCenario(0);
		} catch (NoSuchElementException nz) {
			assertEquals(nz.getMessage(), "Erro na consulta de cenario: Cenario invalido");
		}
	}

	@Test
	public void testeExibirCenarioNumeracaoMenorZero() {
		try {
			sistema.exibeUmCenario(-6);
		} catch (NoSuchElementException nm) {
			assertEquals(nm.getMessage(), "Erro na consulta de cenario: Cenario invalido");
		}
	}

	@Test
	public void testeExibirCenarioNumeracaoNaoCadastrada1() {
		try {
			sistema.exibeUmCenario(1);
		} catch (NoSuchElementException nnc) {
			assertEquals(nnc.getMessage(), "Erro na consulta de cenario: Cenario nao cadastrado");
		}
	}

	@Test
	public void testeExibirCenarioNumeracaoNaoCadastrada2() {
		try {
			sistema.cadastraCenario("Passar em Discreta");
			sistema.cadastraCenario("Passar em Grafos");
			sistema.cadastraCenario("Passar em P2");
			sistema.exibeUmCenario(4);
		} catch (NoSuchElementException nnc) {
			assertEquals(nnc.getMessage(), "Erro na consulta de cenario: Cenario nao cadastrado");
		}
	}

	@Test
	public void testeExibirCenarioCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.cadastraCenario("Passar em Grafos");
		sistema.cadastraCenario("Passar em P2");
		String msg = "A representação textual de um Cenário deve ser da seguinte forma";
		assertEquals(msg, sistema.exibeUmCenario(2), "2 - Passar em Grafos - Nao finalizado");
	}

	@Test
	public void testeListaCenarios() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.encerraCenario(1, false);
		sistema.cadastraCenario("Passar em Grafos");
		String msg = "A Representação textual dos Cenários devem está da seguinte forma";
		assertEquals(msg, sistema.listaCenarios(), "1 - Passar em Discreta - Finalizado(n ocorreu)" + System.lineSeparator()
												+ "2 - Passar em Grafos - Nao finalizado" +System.lineSeparator());
	}
	@Test
	public void testeCadastraApostaApostadorNulo() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, null, 1000, "VAI ACONTECER");
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastraApostaApostadorVazio() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "   ", 1000, "VAI ACONTECER");
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(), "Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastraApostaQuantiaIgualZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "Douglas", 0, "VAI ACONTECER");
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastraApostaQuantiaMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "Douglas", -1000, "VAI ACONTECER");
		} catch (NoSuchElementException qm) {
			assertEquals(qm.getMessage(), "Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastraApostaPrevisaoIncorreta() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "Douglas", 1000, "ACONTECER");
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(), "Erro no cadastro de aposta: Previsao invalida");
		}
	}
	
	@Test
	public void testeCadastraApostaPrevisaoNula() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "Douglas", 1000, null);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastraApostaPrevisaoVazia() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(1, "Douglas", 1000, "   ");
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(), "Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastraApostaCenarioInvalido() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(0, "Douglas", 1000, "N VAI ACONTECER");
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(), "Erro no cadastro de aposta: Cenario invalido");
		}
	}
	
	@Test
	public void testeCadastraApostaCenarioNaoCadastrado() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(2, "Douglas", 1000, "N VAI ACONTECER");
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro no cadastro de aposta: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeCadastraApostaCorreta() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Douglas", 1000, "N VAI ACONTECER");
		String msg = "O tamanho do Arraylist de objetos do tipo Aposta que está em "
				+ "Cenario deve ter tamanho 1 após uma aposta ser cadastrada";
		assertEquals(msg, sistema.getCenarios().get(0).getApostas().size(), 1);
	}

}
