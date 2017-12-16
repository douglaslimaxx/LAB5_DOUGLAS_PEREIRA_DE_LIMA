package modelos;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Representa um sistema de apostas que terá uma lista de cenários onde
 * apostadores podem apostar. O sistema também tem um caixa e uma taxa que será
 * usada para calcular o quanto do valor total das apostas perdedoras de um
 * cenário irá para o caixa do sistema. Nesse sistema é possível cadastrar
 * cenários, exibir uma representação textual de um cenário, listar todos os
 * cenários cadastrados, cadastrar uma aposta em um cenário, listas todas as
 * apostas de um cenário, exibir o valor total das apostas de um cenário, exibir
 * o número de apostas feitas em um cenário, encerrar um cenário, retornar o
 * valor que será destinado aos vencedores que apostaram em alguma cenário,
 * calcula o valor de um cenário destinado ao sistema e adiciona esse valor ao
 * caixa do sistema.
 * 
 * @author Douglas Lima
 *
 */
public class Sistema {

	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;

	/**
	 * Constrói um sistema a partir dos parâmetros caixa e taxa. E inicializa o
	 * Arraylist de objetos do tipo Cenario.
	 * 
	 * @param caixa
	 *            int que será o valor inicial do caixa do sistema.
	 * @param taxa
	 *            double que será a taxa para calcular o valor destinado ao caixa.
	 */
	public Sistema(int caixa, double taxa) {
		if (caixa <= 0) {
			throw new NoSuchElementException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa <= 0) {
			throw new NoSuchElementException("Erro na inicializacao: Taxa nao pode ser inferior a 0");
		}
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new ArrayList<>();
	}

	/**
	 * Método cadastra um cenário ao sistema a partir do paramêtro descricao,
	 * criando um objeto do tipo Cenario e o adiciona ao Arraylist de Cenarios em
	 * sistema. Ele ainda atribui uma numeração ao cenário cadastrado.
	 * 
	 * @param descricao
	 *            String que será a descrição do cenário cadastrado.
	 * @return int que é a numeração do cenário cadastrado.
	 */
	public int cadastraCenario(String descricao) {
		Cenario cenario = new Cenario(descricao, (this.cenarios.size() + 1));
		this.cenarios.add(cenario);
		return this.cenarios.size();
	}

	/**
	 * Método que exibe a representação textual de um determinado cenário.
	 * 
	 * @param numeracao
	 *            int que é a numeração de qual cenário será exibido.
	 * @return String que é a representaçaõ do cenário.
	 */
	public String exibeUmCenario(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro no consulta de cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro no consulta de cenario: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario).toString();
	}

	/**
	 * Método lista todos os cenários cadastrados até o momento no sistema.
	 * 
	 * @return String que é a lista das representações textuais de todos os cenário
	 *         já cadastrados.
	 */
	public String listaCenarios() {
		String saida = "";
		for (int i = 0; i < this.cenarios.size(); i++) {
			saida += (this.cenarios.get(i) + System.lineSeparator());
		}
		return saida;
	}

	/**
	 * Método que adiciona uma aposta a um cenário a partir dos paramêtros
	 * apostador, quantia e previsão que serão paramêtros do construtor de Aposta; e
	 * o paramêtro cenario é o numeração de qual cenário a aposta será adicionada.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenário no qual a aposta será adicionada.
	 * @param apostador
	 *            String que é o nome de quem está fazendo a aposta.
	 * @param quantia
	 *            int que é quantia que será apostada.
	 * @param previsao
	 *            String que é a previsão aque aposta está fazendo em relação ao
	 *            cenário.
	 */
	public void adicionaAposta(int cenario, String apostador, int quantia, String previsao) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro no cadastro de aposta: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		this.cenarios.get(cenario).adicionaAposta(apostador, quantia, previsao);
	}

	/**
	 * Método que retorna o valor total das apostas feitas até o momento em um
	 * cenário.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenário.
	 * @return int que é o valor total das apostas de um cenário.
	 */
	public int valorTotalDeApostas(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro na consulta do valor total de apostas: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario).getCaixaTotal();
	}

	/**
	 * Método retorna o número de apostas feitas até o momento em um cenário.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenaŕio.
	 * @return int que o número de apostas feitas no cenário.
	 */
	public int totalDeApostas(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro na consulta do total de apostas: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario).getApostas().size();
	}

	/**
	 * Método que retorna a representação textual de todas as apostas feitas em um
	 * cenário.
	 * 
	 * @param cenario
	 *            int que é a numeração de qual cenário será exibido.
	 * @return String com as representações textuais de todas as apostas de um
	 *         cenário.
	 */
	public String exibeApostasDeUmCenario(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro ao exibir apostas do cenário: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro ao exibir apostas do cenário: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario).exibeTodasApostas();
	}

	/**
	 * Método encerra um cenário. Mudando o atributo de cenário que contém a
	 * informação se ele foi finalizado ou não. E faz outras ações que estão
	 * relacionadas ao encerramento de um cenário: adiciona o valor destinado ao
	 * caixa do sistema e atribui o valor de rateio que será distribuído aos
	 * vencedores das apostas que foram feitas nesse cenário finalizado.
	 * 
	 * @param cenario
	 *            int que é a numeração de qual cenário será finalizado.
	 * @param ocorreu
	 *            boolean que determina se o cenário ocorreu com o valor true e se o
	 *            cenário não ocorreu com o valor false.
	 */
	public void encerraCenario(int cenario, boolean ocorreu) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario).getFinalizado().equals("Finalizado")) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		if (ocorreu == true) {
			this.cenarios.get(cenario).setFinalizado("ocorreu");
		} else {
			this.cenarios.get(cenario).setFinalizado("n ocorreu");
		}
		this.adicionaDinheiroEmCaixa(cenario);
		this.cenarios.get(cenario).setRateio(
				this.cenarios.get(cenario).getCaixaPerdedores() - this.calculaValorDeCenarioParaCaixa(cenario));
	}

	/**
	 * Método que adicionado o valor destinado ao caixa.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenário o qual será pego o valor a ser
	 *            adicionado no caixa do sistema.
	 */
	private void adicionaDinheiroEmCaixa(int cenario) {
		this.caixa += this.calculaValorDeCenarioParaCaixa(cenario);
	}

	/**
	 * Método calcula e retorna o valor do caixa de um cenário que será destinado ao
	 * caixa do sitema.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenário o qual será pego o valor a ser
	 *            calculado.
	 * @return int que é o valor calculado a ser destinado ao caixa do sistema.
	 */
	public int calculaValorDeCenarioParaCaixa(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario).getFinalizado().equals("Nao Finalizado")) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		return (int) (this.cenarios.get(cenario).getCaixaPerdedores() * this.taxa);
	}

	/**
	 * Método retorna o valor derateio de um cenário, que será destinado aos
	 * vencedores.
	 * 
	 * @param cenario
	 *            int que é a numeração de qual cenário retornará o valor de rateio
	 * @return int que é o valor de rateio do cenário.
	 */
	public int retornaRateio(int cenario) {
		if (cenario <= 0) {
			throw new NoSuchElementException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
		if (cenario > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario).getFinalizado().equals("Nao Finalizado")) {
			throw new NoSuchElementException(
					"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		return this.cenarios.get(cenario).getRateio();
	}

	/**
	 * Método retorna o valor do caixa do sistema até aquele momento.
	 * 
	 * @return int que é o valor do caixa do sistema até o momento.
	 */
	public int getCaixa() {
		return caixa;
	}
}
