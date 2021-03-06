package modelos;

/**
 * Representa uma Facade que controla o controller Sistema.
 * 
 * @author Douglas Lima
 *
 */
public class Facade {

	private Sistema sistema;

	/**
	 * Método que inicializa um sistema a partir de um valor do caixa inicial e um
	 * valor de taxa.
	 * 
	 * @param caixa
	 *            int que é o valor do caixa inicial do sistema
	 * @param taxa
	 *            double que é a taxa utilizada pelo sistema.
	 * @return
	 */
	public void inicializa(int caixa, double taxa) {
		this.sistema = new Sistema(caixa, taxa);
	}

	/**
	 * @see Sistema#getCaixa()
	 */
	public int getCaixa() {
		return this.sistema.getCaixa();
	}

	/**
	 * @see Sistema#cadastraCenario(String)
	 */
	public int cadastrarCenario(String descricao) {
		return this.sistema.cadastraCenario(descricao);
	}

	/**
	 * @see Sistema#cadastraCenario(String, int)
	 */
	public int cadastrarCenario(String descricao, int bonus) {
		return this.sistema.cadastraCenario(descricao, bonus);
	}

	/**
	 * @see Sistema#exibeUmCenario(int)
	 */
	public String exibirCenario(int numeracao) {
		return this.sistema.exibeUmCenario(numeracao);
	}

	/**
	 * @see Sistema#listaCenarios()
	 */
	public String exibirCenarios() {
		return this.sistema.listaCenarios();
	}

	/**
	 * @see Sistema#adicionaAposta(int, String, int, String)
	 */
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.sistema.adicionaAposta(cenario, apostador, valor, previsao);
	}

	/**
	 * @see Sistema#adicionaApostaSeguraValor(int, String, int, String, int, int)
	 */
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorSeguro,
			int custo) {
		return this.sistema.adicionaApostaSeguraValor(cenario, apostador, valor, previsao, valorSeguro, custo);
	}

	/**
	 * @see Sistema#adicionaApostaSeguraTaxa(int, String, int, String, double, int)
	 */
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa,
			int custo) {
		return this.sistema.adicionaApostaSeguraTaxa(cenario, apostador, valor, previsao, taxa, custo);
	}

	/**
	 * @see Sistema#alterarSeguroValor(int, int, int)
	 */
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		return this.sistema.alterarSeguroValor(cenario, apostaAssegurada, valor);
	}

	/**
	 * @see Sistema#alterarSeguroTaxa(int, int, double)
	 */
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double taxa) {
		return this.sistema.alterarSeguroTaxa(cenario, apostaAssegurada, taxa);
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
	 * @see Sistema#calculaValorDeCenarioParaCaixa(int)
	 */
	public int getCaixaCenario(int cenario) {
		return this.sistema.calculaValorDeCenarioParaCaixa(cenario);
	}

	/**
	 * @see Sistema#retornaRateio(int)
	 */
	public int getTotalRateioCenario(int cenario) {
		return this.sistema.retornaRateio(cenario);
	}
	
	/**
	 * 
	 * @see Sistema#alterarOrdem(String)
	 */
	public void alterarOrdem(String ordem) {
		this.sistema.alterarOrdem(ordem);
	}
	
	/**
	 * 
	 * @see Sistema#exibirCenarioOrdenado(int)
	 */
	public String exibirCenarioOrdenado(int cenario) {
		return this.sistema.exibirCenarioOrdenado(cenario);
	}
}