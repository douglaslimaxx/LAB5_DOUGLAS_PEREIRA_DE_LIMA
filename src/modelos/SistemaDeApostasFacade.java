package modelos;

import easyaccept.EasyAccept;

/**
 * Representa uma Facade que controla o controller Sistema.
 * @author Douglas Lima
 *
 */
public class SistemaDeApostasFacade {

	private Sistema sistema;
	
	/**
	 * Método que inicializa um sistema a partir de um valor do caixa
	 * inicial e um valor de taxa.
	 * @param caixa int que é o valor do caixa inicial do sistema
	 * @param taxa double que é a taxa utilizada pelo sistema.
	 */
	public SistemaDeApostasFacade(int caixa, double taxa) {
		this.sistema = new Sistema(caixa, taxa);
	}
	
	/**
	 * @see Sistema#getCaixa()
	 */
	public int recuperaValorCaixa() {
		return this.sistema.getCaixa();
	}
	
	/**
	 * @see Sistema#cadastraCenario(String)
	 */
	public int cadastraCenario(String descricao) {
		return this.sistema.cadastraCenario(descricao);
	}
	
	/**
	 * @see Sistema#exibeUmCenario(int)
	 */
	public String exibirUmCenario(int numeracao) {
		return this.sistema.exibeUmCenario(numeracao);
	}

	/**
	 * @see Sistema#listaCenarios()
	 */
	public String exibirTodosCenarios() {
		return this.sistema.listaCenarios();
	}

	/**
	 * @see Sistema#adicionaAposta(int, String, int, String)
	 */
	public void cadastrarAposta(int cenario, String apostador, int quantia, String previsao) {
		this.sistema.adicionaAposta(cenario, apostador, quantia, previsao);
	}

	/**
	 * @see Sistema#valorTotalDeApostas(int)
	 */
	public int valorTotalDeApostas(int cenario) {
		return this.sistema.valorTotalDeApostas(cenario);
	}

	/**
	 * @see Sistema#totalDeApostas(int)
	 */
	public int totalDeApostas(int cenario) {
		return this.sistema.totalDeApostas(cenario);
	}

	/**
	 * @see Sistema#exibeApostasDeUmCenario(int)
	 */
	public String exibeApostas(int cenario) {
		return this.sistema.exibeApostasDeUmCenario(cenario);
	}
	
	/**
	 * @see Sistema#encerraCenario(int, boolean)
	 */
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.sistema.encerraCenario(cenario, ocorreu);
	}

	/**
	 * @see Sistema#cenarioParaCaixa(int)
	 */
	public int getCaixaCenario(int cenario) {
		return this.sistema.cenarioParaCaixa(cenario);
	}

	/**
	 * @see Sistema#retornaRateio(int)
	 */
	public int getTotalRateioCenario(int cenario) {
		return this.sistema.retornaRateio(cenario);
	}
	
	public static void main(String[] args) {
		args = new String[] {"modelos.SistemaDeApostasFacade", "acceptance_test/us1_test.txt",
				"acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us1_test.txt"};
		EasyAccept.main(args);
	}
}