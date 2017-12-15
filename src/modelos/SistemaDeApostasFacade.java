package modelos;

import easyaccept.EasyAccept;

public class SistemaDeApostasFacade {

	private Sistema sistema;
	
	public SistemaDeApostasFacade(int caixa, double taxa) {
		this.sistema = new Sistema(caixa, taxa);
	}
	
	public int recuperaValorCaixa() {
		return this.sistema.getCaixa();
	}
	
	public int cadastraCenario(String descricao) {
		return this.sistema.cadastraCenario(descricao);
	}
	
	public String exibirUmCenario(int numeracao) {
		return this.sistema.exibirUmCenario(numeracao);
	}
	
	public String exibirTodosCenarios() {
		return this.sistema.listaCenarios();
	}
	
	public void cadastrarAposta(int cenario, String apostador, int quantia, String previsao) {
		this.sistema.adicionaAposta(cenario, apostador, quantia, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.sistema.valorTotalDeApostas(cenario);
	}
	
	public int totalDeApostas(int cenario) {
		return this.sistema.totalDeApostas(cenario);
	}
	
	public String exibeApostas(int cenario) {
		return this.sistema.exibirApostasDeUmCenario(cenario);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.sistema.encerrarCenario(cenario, ocorreu);
	}
	
	public int getCaixaCenario(int cenario) {
		return this.sistema.cenarioParaCaixa(cenario);
	}
	
	public int getTotalRateioCenario(int cenario) {
		return this.sistema.retornaRateio(cenario);
	}
	
	public static void main(String[] args) {
		args = new String[] ("SistemaDeApostasFacade", "acceptance_test/us1_test.txt");
		EasyAccept.main(args);
	}
}