package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.Sistema;
import modelos.IdComparador;
import modelos.NomeComparador;
import modelos.NumeroApostasComparador;

public class SistemaTest {

	private Sistema sistema;

	@Before
	public void setUp() {
		sistema = new Sistema(10000, 0.001);
	}
	
	//Teste Gerais do sistema

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
	
	//Testes com cenário sem bônus

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
	
	
	//Testes com cenário com bônus
	
	@Test
	public void testeCadastraCenarioBonusDescricaoNula() {
		try {
			sistema.cadastraCenario(null, 50);
		} catch (NullPointerException dn) {
			assertEquals(dn.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser nula");
		}
	}

	@Test
	public void testeCadastraCenarioBonusDescricaoVazia() {
		try {
			sistema.cadastraCenario("    ", 50);
		} catch (IllegalArgumentException dv) {
			assertEquals(dv.getMessage(), "Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
	}
	
	@Test
	public void testeCadastraCenarioBonusZero() {
		try {
			sistema.cadastraCenario("Passar em Discreta", 0);
		} catch (NoSuchElementException bz) {
			assertEquals(bz.getMessage(), "Erro no cadastro de cenario: Bonus invalido");
		}
	}
	
	@Test
	public void testeCadastraCenarioBonusMenorZero() {
		try {
			sistema.cadastraCenario("Passar em Discreta", -60);
		} catch (NoSuchElementException bmz) {
			assertEquals(bmz.getMessage(), "Erro no cadastro de cenario: Bonus invalido");
		}
	}
	
	@Test
	public void testeCaixaDiminuiComBonus() {
		sistema.cadastraCenario("Aprender IA", 50);
		String msg = "Caixa do sistema deve ter 9950";
		assertEquals(msg, 9950, sistema.getCaixa());
	}

	//Testes gerais de cenário
	
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
		assertEquals(msg, sistema.listaCenarios(), "1 - Passar em Discreta - Finalizado (n ocorreu)" + System.lineSeparator()
												+ "2 - Passar em Grafos - Nao finalizado" +System.lineSeparator());
	}
	
	//Testes com aposta não assegurada
	
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
	public void testeCadastraApostaCenarioInvalidoIgualZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(0, "Douglas", 1000, "N VAI ACONTECER");
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(), "Erro no cadastro de aposta: Cenario invalido");
		}
	}

	@Test
	public void testeCadastraApostaCenarioInvalidoMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaAposta(-2, "Douglas", 1000, "N VAI ACONTECER");
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

	//Testes com aposta assegurada por taxa
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaCenarioInvalido() {
		try {
			sistema.adicionaApostaSeguraTaxa(0, "Douglas", 1000, "VAI ACONTECER", 0.02, 50);
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaCenarioNaoCadastrado() {
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "VAI ACONTECER", 0.02, 50);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaApostadorNulo() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, null, 1000, "VAI ACONTECER", 0.02, 50);
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaApostadorVazio() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "", 1000, "VAI ACONTECER", 0.02, 50);
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaQuantiaZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 0, "VAI ACONTECER", 0.02, 50);
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaQuantiaMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", -3, "VAI ACONTECER", 0.02, 50);
		} catch (NoSuchElementException qmz) {
			assertEquals(qmz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaPrevisaoNula() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, null, 0.02, 50);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaPrevisaoVazia() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "", 0.02, 50);
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaPrevisaoInvalida() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "Queria muito", 0.02, 50);
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaSeguroZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.0, 50);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaSeguroMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", -0.02, 50);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaCustoZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, 0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Custo nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaTaxaCustoMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, -5);
		} catch (NoSuchElementException cmz) {
			assertEquals(cmz.getMessage(),  "Erro no cadastro de aposta assegurada por taxa: Custo nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastraApostaAsseguradaTaxaCorreta() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, 50);
		String msg = "O tamanho do Arraylist de objetos do tipo ApostaAssegurada que está em "
				+ "Cenario deve ter tamanho 1 após uma aposta ser cadastrada";
		assertEquals(msg, sistema.getCenarios().get(0).getApostasAsseguradas().size(), 1);
	}

	//Testes com aposta assegurada por valor

	@Test
	public void testeCadastrarApostaAsseguradaValorCenarioInvalido() {
		try {
			sistema.adicionaApostaSeguraValor(0, "Douglas", 1000, "VAI ACONTECER", 200, 50);
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorCenarioNaoCadastrado() {
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "VAI ACONTECER", 200, 50);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorApostadorNulo() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, null, 1000, "VAI ACONTECER", 200, 50);
		} catch (NullPointerException an) {
			assertEquals(an.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorApostadorVazio() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "", 1000, "VAI ACONTECER", 200, 50);
		} catch (IllegalArgumentException av) {
			assertEquals(av.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorQuantiaZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 0, "VAI ACONTECER", 200, 50);
		} catch (NoSuchElementException qz) {
			assertEquals(qz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorQuantiaMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", -3, "VAI ACONTECER", 200, 50);
		} catch (NoSuchElementException qmz) {
			assertEquals(qmz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorPrevisaoNula() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, null, 200, 50);
		} catch (NullPointerException pn) {
			assertEquals(pn.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorPrevisaoVazia() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "", 200, 50);
		} catch (IllegalArgumentException pv) {
			assertEquals(pv.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorPrevisaoInvalida() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "Queria muito", 200, 50);
		} catch (IllegalArgumentException pi) {
			assertEquals(pi.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorSeguroZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 0, 50);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorSeguroMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", -5, 50);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorCustoZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Custo nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastrarApostaAsseguradaValorCustoMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, -5);
		} catch (NoSuchElementException cmz) {
			assertEquals(cmz.getMessage(),  "Erro no cadastro de aposta assegurada por valor: Custo nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeCadastraApostaAsseguradaValorCorreta() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 50);
		String msg = "O tamanho do Arraylist de objetos do tipo ApostaAssegurada que está em "
				+ "Cenario deve ter tamanho 1 após uma aposta ser cadastrada";
		assertEquals(msg, sistema.getCenarios().get(0).getApostasAsseguradas().size(), 1);
	}
	
	//Teste gerais com apostas

	@Test
	public void testeValorTotalApostasCenarioInvalidoIgualZero() {
		try {
			sistema.valorTotalDeApostas(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do valor total de apostas: Cenario invalido");
		}
	}
	
	@Test
	public void testeValorTotalApostasCenarioInvalidoMenorZero() {
		try {
			sistema.valorTotalDeApostas(-2);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do valor total de apostas: Cenario invalido");
		}
	}
	
	@Test
	public void testeValorTotalApostasCenarioNaoCadastrado() {
		try {
			sistema.valorTotalDeApostas(1);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
	}
	
	//Testes para alterar tipo de seguro de uma aposta
	
	@Test
	public void testeAlteraSeguroValorCenarioInvalido() {
		try {
			sistema.alterarSeguroValor(0, 1, 200);
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Cenario invalido");
		}
	}
	
	@Test
	public void testeAlteraSeguroValorCenarioNaoCadastrado() {
		try {
			sistema.alterarSeguroValor(2, 1, 200);
		} catch (NoSuchElementException ci) {
			assertEquals(ci.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeAlteraSeguroValorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, 50);
		try {
			sistema.alterarSeguroValor(1, 0, 0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeAlteraSeguroValorMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, 50);
		try {
			sistema.alterarSeguroValor(1, 0, -5);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeAlteraSeguroValorCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraTaxa(1, "Douglas", 1000, "N VAI ACONTECER", 0.02, 50);
		sistema.alterarSeguroValor(1, 0, 200);
		String msg = "Valor assegurado da aposta deve ser 200";
		assertEquals(msg, sistema.getCenarios().get(0).getApostaAssegurada(0).getValor(), 200);		
	}
	
	@Test
	public void testeAlteraSeguroTaxaZero() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 50);
		try {
			sistema.alterarSeguroTaxa(1, 0, 0.0);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro na mudança do seguro para taxa de aposta assegurada: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeAlteraSeguroTaxaMenorZero() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 50);
		try {
			sistema.alterarSeguroTaxa(1, 0, -0.02);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro na mudança do seguro para taxa de aposta assegurada: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeAlteraSeguroTaxaCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 50);
		sistema.alterarSeguroTaxa(1, 0, 0.02);
		String msg = "Valor assegurado da aposta deve ser 20";
		assertEquals(msg, 20, sistema.getCenarios().get(0).getApostaAssegurada(0).getValor());		
	}
	
	//Teste funcionamento geral do sistema
	
	@Test
	public void testeSubtraindoValorSegurosDeCaixa() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaApostaSeguraValor(1, "Douglas", 1000, "N VAI ACONTECER", 200, 50);
		sistema.encerraCenario(1, true);
		String msg = "Deve ter 9850 no caixa do sistema";
		assertEquals(msg, 9851, sistema.getCaixa());
	}
	
	@Test
	public void testeValorTotalApostasCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Douglas", 10000, "N VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 50000, "N VAI ACONTECER");
		String msg = "O valor total da apostas no Cenario 1 devem ter o valor 60000";
		assertEquals(msg, sistema.valorTotalDeApostas(1), 60000);
	}
	
	@Test
	public void testeTotalApostaCenarioInvalidoIgualZero() {
		try {
			sistema.totalDeApostas(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do total de apostas: Cenario invalido");
		}
	}
	
	@Test
	public void testeTotalApostaCenarioInvalidoMenorZero() {
		try {
			sistema.totalDeApostas(-3);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do total de apostas: Cenario invalido");
		}
	}
	
	@Test
	public void testeTotalApostaCenarioInvalidoNaoCadastrado() {
		try {
			sistema.totalDeApostas(1);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeTotalApostasCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Douglas", 10000, "N VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 50000, "N VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 40000, "N VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 20000, "N VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 90000, "N VAI ACONTECER");
		String msg = "O total da apostas no Cenario 1 devem ser 5";
		assertEquals(msg, sistema.totalDeApostas(1), 5);
	}
	
	@Test
	public void testeExibeApostaCenarioInvalidoIgualZero() {
		try {
			sistema.exibeApostasDeUmCenario(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro ao exibir apostas do cenário: Cenario invalido");
		}
	}
	
	@Test
	public void testeExibeApostaCenarioInvalidoMenorZero() {
		try {
			sistema.exibeApostasDeUmCenario(-2);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro ao exibir apostas do cenário: Cenario invalido");
		}
	}
	
	@Test
	public void testeExibeApostaCenarioNaoCadastrado() {
		try {
			sistema.exibeApostasDeUmCenario(1);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro ao exibir apostas do cenário: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testEExibeApostaCenario() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Marcella", 10000, "VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 50000, "N VAI ACONTECER");
		String msg = "A representação textual das apostas de um cenário deve ser da seuinte forma";
		assertEquals(msg, sistema.exibeApostasDeUmCenario(1), "Marcella - R$100,00 - VAI ACONTECER" + System.lineSeparator()
															+ "Douglas - R$500,00 - N VAI ACONTECER" + System.lineSeparator());
	}
	
	@Test
	public void testeEncerrarCenarioInvalidoIgualZero() {
		try {
			sistema.encerraCenario(0, false);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro ao fechar aposta: Cenario invalido");
		}
	}
	
	@Test
	public void testeEncerrarCenarioInvalidoMenorZero() {
		try {
			sistema.encerraCenario(-2, false);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro ao fechar aposta: Cenario invalido");
		}
	}
	
	@Test
	public void testeEncerrarCenarioNaoCadastrado() {
		try {
			sistema.encerraCenario(1, false);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro ao fechar aposta: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeEncerrarCenarioJaFinalizada() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.encerraCenario(1, false);
		try {
			sistema.encerraCenario(1, false);
		} catch (NoSuchElementException cf) {
			assertEquals(cf.getMessage(), "Erro ao fechar aposta: Cenario ja esta fechado");
		}
	}
	
	@Test
	public void testeEncerrarCenarioSetaComoFinalizado() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.encerraCenario(1, false);
		String msg = "Após ser encerrado, o cenário 1 deve ter a variável finalizado == Finalizado";
		assertEquals(msg, sistema.getCenarios().get(0).getFinalizado(), "Finalizado");
	}
	
	@Test
	public void testeCalculaValorCenarioParaCaixaCenarioInvalidoIgualZero() {
		try {
			sistema.calculaValorDeCenarioParaCaixa(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do caixa do cenario: Cenario invalido");
		}
	}
	
	@Test
	public void testeCalculaValorCenarioParaCaixaCenarioInvalidoMenorZero() {
		try {
			sistema.calculaValorDeCenarioParaCaixa(-2);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do caixa do cenario: Cenario invalido");
		}
	}
	
	@Test
	public void testeCalculaValorCenarioParaCaixaCenarioNaoCadastrado() {
		try {
			sistema.calculaValorDeCenarioParaCaixa(1);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeCalculaValorCenarioParaCaixaCenarioNaofinalizado() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.calculaValorDeCenarioParaCaixa(1);
		} catch (NoSuchElementException cnf) {
			assertEquals(cnf.getMessage(), "Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
	}

	@Test
	public void testeCalculaValorCenarioParaCaixaCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Marcella", 10000, "VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 50000, "N VAI ACONTECER");
		sistema.encerraCenario(1, false);
		String msg = "O valor do cenário 1 que será destinado ao caixa do sistema deve ser 10";
		assertEquals(msg, sistema.calculaValorDeCenarioParaCaixa(1), 10);
	}
	
	
	@Test
	public void testeRetornaRateioCenarioInvalidoIgualZero() {
		try {
			sistema.retornaRateio(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
	}
	
	@Test
	public void testeRetornaRateioCenarioInvalidoMenorZero() {
		try {
			sistema.retornaRateio(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
	}
	
	@Test
	public void testeRetornaRateioCenarioNaoCadastrado() {
		try {
			sistema.retornaRateio(1);
		} catch (NoSuchElementException cnc) {
			assertEquals(cnc.getMessage(), "Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeRetornaRateioCenarioNaoFinalizado() {
		sistema.cadastraCenario("Passar em Discreta");
		try {
			sistema.retornaRateio(1);
		} catch (NoSuchElementException cnf) {
			assertEquals(cnf.getMessage(), "Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
	}

	@Test
	public void testeRetornaRateioCorreto() {
		sistema.cadastraCenario("Passar em Discreta");
		sistema.adicionaAposta(1, "Marcella", 10000, "VAI ACONTECER");
		sistema.adicionaAposta(1, "Douglas", 50000, "N VAI ACONTECER");
		sistema.encerraCenario(1, false);
		String msg = "O valor de rateio do cenário 1 deve ser 9990";
		assertEquals(msg, sistema.retornaRateio(1), 9990);
	}
	
	//Testes sobre Ordenação 
	
	@Test(expected = NullPointerException.class)
	public void testeAlteraOrdemNula() {
		sistema.alterarOrdem(null);
	}
	
	@Test
	public void testeAlteraOrdemVazia() {
		try {
			this.sistema.alterarOrdem("  ");
		} catch (IllegalArgumentException ov) {
			assertEquals(ov.getMessage(), "Erro ao alterar ordem: Ordem nao pode ser vazia ou nula");
		}
	}
	
	@Test
	public void testeAlteraOrdemInvalida() {
		try {
			this.sistema.alterarOrdem("Rateio");
		} catch (IllegalArgumentException oi) {
			assertEquals(oi.getMessage(), "Erro ao alterar ordem: Ordem invalida");
		}
	}
	
	@Test
	public void testeAlteraOrdemCorretaCadastro() {
		this.sistema.alterarOrdem("cadastro");
		String msg = "O tipo do comparador deve ser IdComparador";
		assertTrue(msg, this.sistema.getComparador() instanceof IdComparador);
	}
	
	@Test
	public void testeAlteraOrdemCorretaNome() {
		this.sistema.alterarOrdem("nome");
		String msg = "O tipo do comparador deve ser NomeComparador";
		assertTrue(msg, this.sistema.getComparador() instanceof NomeComparador);
	}
	
	@Test
	public void testeAlteraOrdemCorretaNumeroApostas() {
		this.sistema.alterarOrdem("apostas");
		String msg = "O tipo do comparador deve ser NumeroApostasComparador";
		assertTrue(msg, this.sistema.getComparador() instanceof NumeroApostasComparador);
	}
	
	@Test
	public void testeExibirCenarioOrdenadoCenarioZero() {
		try {
			this.sistema.exibirCenarioOrdenado(0);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta de cenario ordenado: Cenario invalido");
		}
	}
	
	@Test
	public void testeExibirCenarioOrdenadoCenarioMenorZero() {
		try {
			this.sistema.exibirCenarioOrdenado(-9);
		} catch (NoSuchElementException cmz) {
			assertEquals(cmz.getMessage(), "Erro na consulta de cenario ordenado: Cenario invalido");
		}
	}
	
	@Test
	public void testeExibirCenarioOrdenadoCenarioNaoCadastrado() {
		try {
			this.sistema.exibirCenarioOrdenado(1);
		} catch (NoSuchElementException cz) {
			assertEquals(cz.getMessage(), "Erro na consulta de cenario ordenado: Cenario nao cadastrado");
		}
	}
	
	@Test
	public void testeExibirCenarioOrdenadoCadastro() {
		this.sistema.cadastraCenario("Bianca vai passar em administração");
		this.sistema.cadastraCenario("Douglas vai ler It");
		this.sistema.alterarOrdem("cadastro");
		String msg = "Ordenando os cenários pela sequência do cadastro, temos o "
				+ "cenário 'Bianca vai passar em administração' na posição 1";
		assertEquals(msg, this.sistema.exibirCenarioOrdenado(1), "1 - Bianca vai passar em administração - Nao finalizado");
	}
	
	@Test
	public void testeExibirCenarioOrdenadoNome() {
		this.sistema.cadastraCenario("Douglas vai ler It");
		this.sistema.cadastraCenario("Bianca vai passar em administração");
		this.sistema.alterarOrdem("nome");
		String msg = "Ordenando os cenários pelo nome, temos o cenário 'Bianca "
				+ "vai passar em administração' na posição 1";
		assertEquals(msg, this.sistema.exibirCenarioOrdenado(1), "2 - Bianca vai passar em administração - Nao finalizado");
	}
	
	@Test
	public void testeExibirCenarioOrdenadoNumeroAposta() {
		this.sistema.cadastraCenario("Douglas vai ler It");
		this.sistema.cadastraCenario("Bianca vai passar em administração");
		this.sistema.adicionaAposta(2, "Douglas", 10000, "VAI ACONTECER");
		this.sistema.alterarOrdem("apostas");
		String msg = "Ordenando os cenários pelo nome, temos o cenário 'Bianca "
				+ "vai passar em administração' na posição 1";
		assertEquals(msg, this.sistema.exibirCenarioOrdenado(1), "2 - Bianca vai passar em administração - Nao finalizado");
	}
	
	
	
	
}
