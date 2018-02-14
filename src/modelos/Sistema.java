package modelos;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Representa um sistema de apostas que terá uma lista de cenários onde
 * apostadores podem apostar. O sistema também tem um caixa e uma taxa que será
 * usada para calcular o quanto do valor total das apostas perdedoras de um
 * cenário irá para o caixa do sistema. Nesse sistema é possível cadastrar
 * cenários, exibir uma representação textual de um cenário, listar todos os
 * cenários cadastrados, cadastrar uma aposta em um cenário, cadastrar uma
 * aposta assegurada por um valor ou por um taxa, mudar uma aposta assegurada
 * por taxa para assegurada por valor e vice-versa, listas todas as apostas de
 * um cenário, exibir o valor total das apostas de um cenário, exibir o número
 * de apostas feitas em um cenário, encerrar um cenário, retornar o valor que
 * será destinado aos vencedores que apostaram em alguma cenário, calcula o
 * valor de um cenário destinado ao sistema e adiciona esse valor ao caixa do
 * sistema.
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
		if (caixa < 0) {
			throw new NoSuchElementException("Erro na inicializacao: Caixa nao pode ser inferior a 0");
		}
		if (taxa < 0) {
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
	 * Método cadastra um cenário bônus ao sistema a partir dos paramêtros descricao
	 * e bonus, criando um objeto do tipo CenarioBonus e o adiciona ao Arraylist de
	 * Cenarios em sistema. Ele ainda atribui uma numeração ao cenário cadastrado e
	 * subtrai do caixa o valor do bonus.
	 * 
	 * @param descricao
	 *            String que será a descrição do cenário cadastrado.
	 * @param bonus
	 *            int valor do bonus a ser retirado do caixa do sistema;
	 * @return int que é a numeração do cenário cadastrado.
	 */
	public int cadastraCenario(String descricao, int bonus) {
		Cenario cenario = new CenarioBonus(descricao, (this.cenarios.size() + 1), bonus);
		this.caixa -= bonus;
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
		if ((cenario) <= 0) {
			throw new NoSuchElementException("Erro na consulta de cenario: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta de cenario: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario - 1).toString();
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
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro no cadastro de aposta: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro no cadastro de aposta: Cenario nao cadastrado");
		}
		this.cenarios.get(cenario - 1).adicionaAposta(apostador, quantia, previsao);
	}

	/**
	 * Método que adiciona uma aposta assegurada por valor a um cenário a partir dos
	 * paramêtros apostador, quantia, previsão e valor que serão paramêtros do
	 * construtor de Aposta; o paramêtro cenario é o numeração de qual cenário a
	 * aposta será adicionada; e o paramêtro custo será adicionado ao caixa do
	 * sistema, pois é o valor que apostador paga para ter uma aposta assegurada.
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
	 * @param valor
	 *            int que é o valor assegurado, que o apostador receberá se perder.
	 * @param custo
	 *            int que é o a pagar para fazer uma aposta assegurada.
	 * @return int o índice da aposta, que será o seu id.
	 */
	public int adicionaApostaSeguraValor(int cenario, String apostador, int quantia, String previsao, int valor,
			int custo) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro no cadastro de aposta assegurada por valor: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro no cadastro de aposta assegurada por valor: Cenario nao cadastrado");
		}
		if (custo <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta assegurada por valor: Custo nao pode ser menor ou igual a zero");
		}
		this.caixa += custo;
		return this.cenarios.get(cenario - 1).adicionaAposta(apostador, quantia, previsao, valor);
	}

	/**
	 * Método que adiciona uma aposta assegurada por taxa a um cenário a partir dos
	 * paramêtros apostador, quantia, previsão e taxa que serão paramêtros do
	 * construtor de Aposta; o paramêtro cenario é o numeração de qual cenário a
	 * aposta será adicionada; e o paramêtro custo será adicionado ao caixa do
	 * sistema, pois é o valor que apostador paga para ter uma aposta assegurada.
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
	 * @param taxa
	 *            double o valor que será multiplicado pelo valor apostado, para
	 *            resultar no valor que o apostador receberá se perder.
	 * @param custo
	 *            int que é o a pagar para fazer uma aposta assegurada.
	 * @return int o índice da aposta, que será o seu id.
	 */
	public int adicionaApostaSeguraTaxa(int cenario, String apostador, int quantia, String previsao, double taxa,
			int custo) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro no cadastro de aposta assegurada por taxa: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro no cadastro de aposta assegurada por taxa: Cenario nao cadastrado");
		}
		if (custo <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta assegurada por taxa: Custo nao pode ser menor ou igual a zero");
		}
		this.caixa += custo;
		return this.cenarios.get(cenario - 1).adicionaAposta(apostador, quantia, previsao, taxa);
	}

	/**
	 * Método que muda o modo como uma aposta será assegurada. Mudando de um valor
	 * fixo para uma taxa que será aplicada no valor apostado.
	 * 
	 * @param cenario
	 *            int que é o id do cenário no qual a aposta a ser modificada foi
	 *            feita.
	 * @param apostaAssegurada
	 *            int que é o id da aposta que será modificada
	 * @param valor
	 *            int que é o valor a ser assegurado.
	 * @return int o índice da aposta, que será o seu id.
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException(
					"Erro na mudança do seguro para valor de aposta assegurada: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException(
					"Erro na mudança do seguro para valor de aposta assegurada: Cenario nao cadastrado");
		}
		this.cenarios.get(cenario - 1).getApostasAsseguradas().get(apostaAssegurada).mudarTipo(valor);
		return apostaAssegurada;
	}

	/**
	 * Método que muda o modo como uma aposta será assegurada. Mudando de uma taxa
	 * que será aplicada no valor apostado, para um valor fixo assegurado.
	 * 
	 * @param cenario
	 *            int que é o id do cenário no qual a aposta a ser modificada foi
	 *            feita.
	 * @param apostaAssegurada
	 *            int que é o id da aposta que será modificada
	 * @param taxa
	 *            double que é a taxa aplicado ao valor apostado, para obter o valor
	 *            assegurado
	 * @return int o índice da aposta, que será o seu id.
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException(
					"Erro na mudança do seguro para taxa de aposta assegurada: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException(
					"Erro na mudança do seguro para taxa de aposta assegurada: Cenario nao cadastrado");
		}
		this.cenarios.get(cenario - 1).getApostasAsseguradas().get(apostaAssegurada).mudarTipo(taxa);
		return apostaAssegurada;
	}

	// public int adicionaAposta(int cenario, String apostador, int valor, String
	// previsao, int valor, int custo)

	/**
	 * Método que retorna o valor total das apostas feitas até o momento em um
	 * cenário.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenário.
	 * @return int que é o valor total das apostas de um cenário.
	 */
	public int valorTotalDeApostas(int cenario) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro na consulta do valor total de apostas: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do valor total de apostas: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario - 1).getCaixaTotal();
	}

	/**
	 * Método retorna o número de apostas feitas até o momento em um cenário.
	 * 
	 * @param cenario
	 *            int que é a numeração do cenaŕio.
	 * @return int que o número de apostas feitas no cenário.
	 */
	public int totalDeApostas(int cenario) {
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro na consulta do total de apostas: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do total de apostas: Cenario nao cadastrado");
		}
		return (this.cenarios.get(cenario - 1).getApostas().size()
				+ this.cenarios.get(cenario - 1).getApostasAsseguradas().size());
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
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro ao exibir apostas do cenário: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro ao exibir apostas do cenário: Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario - 1).exibeTodasApostas();
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
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario - 1).getFinalizado().equals("Finalizado")) {
			throw new NoSuchElementException("Erro ao fechar aposta: Cenario ja esta fechado");
		}
		if (ocorreu == true) {
			this.cenarios.get(cenario - 1).setFinalizado("ocorreu");
		} else {
			this.cenarios.get(cenario - 1).setFinalizado("n ocorreu");
		}
		this.adicionaDinheiroEmCaixa(cenario);
		this.cenarios.get(cenario - 1).setRateio(
				this.cenarios.get(cenario - 1).getCaixaPerdedores() - this.calculaValorDeCenarioParaCaixa(cenario));
		this.caixa -= this.cenarios.get(cenario - 1).getSeguros();
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
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario - 1).getFinalizado().equals("Nao finalizado")) {
			throw new NoSuchElementException("Erro na consulta do caixa do cenario: Cenario ainda esta aberto");
		}
		return (int) (this.cenarios.get(cenario - 1).getCaixaPerdedores() * this.taxa);
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
		if ((cenario - 1) < 0) {
			throw new NoSuchElementException("Erro na consulta do total de rateio do cenario: Cenario invalido");
		}
		if ((cenario) > this.cenarios.size()) {
			throw new NoSuchElementException("Erro na consulta do total de rateio do cenario: Cenario nao cadastrado");
		}
		if (this.cenarios.get(cenario - 1).getFinalizado().equals("Nao finalizado")) {
			throw new NoSuchElementException(
					"Erro na consulta do total de rateio do cenario: Cenario ainda esta aberto");
		}
		return this.cenarios.get(cenario - 1).getRateio();
	}

	/**
	 * Método retorna o valor do caixa do sistema até aquele momento.
	 * 
	 * @return int que é o valor do caixa do sistema até o momento.
	 */
	public int getCaixa() {
		return this.caixa;
	}

	/**
	 * Método retorna o valor da taxa do sistema.
	 * 
	 * @return double que é o valor da taxa do sistema.
	 */
	public double getTaxa() {
		return this.taxa;
	}

	/**
	 * Método retorna a lista de objetos do tipo Cenario que estão em Sistema.
	 * 
	 * @return Arraylist<Cenario> que é a lista de cenários de um sistema.
	 */
	public ArrayList<Cenario> getCenarios() {
		return cenarios;
	}

}
