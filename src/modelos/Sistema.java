package modelos;

import java.util.ArrayList;

public class Sistema {

	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	private int numeracaoCenario;
	
	public Sistema(int caixa, double taxa) {
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new ArrayList<>();
		this.numeracaoCenario = 0;
	}

	public int cadastraCenario(String descricao) {
		this.numeracaoCenario++;
		Cenario cenario = new Cenario(descricao, this.numeracaoCenario);
		this.cenarios.add(cenario);
		return this.numeracaoCenario; 
	}
	
	public String exibirUmCenario(int numeracao) {
		return this.cenarios.get(numeracao).toString();
	}
	
	public String listaCenarios() {
		String saida = "";
		for (int i = 0; i<this.cenarios.size(); i++) {
			saida += (this.cenarios.get(i) + System.lineSeparator());
		}
		return saida;
	}

	public void adicionaAposta(int cenario, String apostador, int quantia, String previsao) {
		this.cenarios.get(cenario).adicionaAposta(apostador, quantia, previsao);
	}
	
	public int valorTotalDeApostas(int cenario) {
		return this.cenarios.get(cenario).getCaixaTotal();
	}
	
	public int totalDeApostas(int cenario) {
		return this.cenarios.get(cenario).getApostas().size();
	}
	
	public String exibirApostasDeUmCenario(int cenario) {
		return this.cenarios.get(cenario).exibirTodasApostas();
	}
	
	
	
	public void encerrarCenario(int cenario, boolean ocorreu) {
		if (ocorreu == true) {
			this.cenarios.get(cenario).setFinalizado("ocorreu");
		} else {
			this.cenarios.get(cenario).setFinalizado("n ocorreu");
		}
	}
	
	public int retornaCaixaVencedores(int cenario) {
		return this.cenarios.get(cenario).getCaixaVencedores();
	}
	
	public int retornaCaixaPerdedores(int cenario) {
		return this.cenarios.get(cenario).getCaixaPerdedores();
	}
	
	
	
	public int getCaixa() {
		return caixa;
	}

	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}

	public double getTaxa() {
		return taxa;
	}
	
	public ArrayList<Cenario> getCenarios() {
		return cenarios;
	}

	public int getNumeracaoCenario() {
		return numeracaoCenario;
	}
	
}
