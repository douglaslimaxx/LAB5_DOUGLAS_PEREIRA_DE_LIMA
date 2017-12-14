package modelos;

import java.util.ArrayList;

public class Sistema {

	private int caixa;
	private double taxa;
	private ArrayList<Cenario> cenarios;
	
	public Sistema(int caixa, double taxa) {
		this.caixa = caixa;
		this.taxa = taxa;
		this.cenarios = new ArrayList<>();
	}

	public int cadastraCenario(String descricao) {
		Cenario cenario = new Cenario(descricao, (this.cenarios.size() + 1));
		this.cenarios.add(cenario);
		return this.cenarios.size();
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
		this.adicionaDinheiroEmCaixa(cenario);
		this.cenarios.get(cenario).setRateio(this.cenarios.get(cenario).getCaixaPerdedores() - 
				this.cenarioParaCaixa(cenario));
	}
	
	public int retornaRateio(int cenario) {
		return this.cenarios.get(cenario).getRateio();
	}
	
	private void adicionaDinheiroEmCaixa(int cenario) {
		this.caixa += this.cenarioParaCaixa(cenario);
		this.cenarios.get(cenario).setDestinadoAoCaixa(this.cenarioParaCaixa(cenario));
	}
	
	public int cenarioParaCaixa(int cenario) {
		return (int)(this.cenarios.get(cenario).getCaixaPerdedores() * this.taxa);
	}
	public int getCaixa() {
		return caixa;
	}
	
	public ArrayList<Cenario> getCenarios() {
		return cenarios;
	}
	
}
