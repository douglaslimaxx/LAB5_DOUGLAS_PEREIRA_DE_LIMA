package modelos;

import java.util.ArrayList;

public class Cenario {

	private String nome;
	private String ocorreu;
	private String finalizado;
	private ArrayList<Aposta> apostas;
	private int caixaVencedores;
	private int caixaPerdedores;
	private int numeracao;
	private int caixaTotal;

	public Cenario(String nome, int numeracao) {
		this.nome = nome;
		this.numeracao = numeracao;
		this.ocorreu = "";
		this.apostas = new ArrayList<>();
		this.caixaPerdedores = 0;
		this.caixaVencedores = 0;
		this.finalizado = "Não Finalizado";
	}

	public void adicionaAposta(String apostador, int quantia, String previsao) {
		Aposta aposta = new Aposta(apostador, quantia, previsao);
		this.apostas.add(aposta);
		this.caixaTotal += quantia;
	}
	
	public void setFinalizado(String ocorreu) {
		if (this.finalizado == "Finalizado") {
			
		} else {
			this.finalizado = "Finalizado";
			this.ocorreu = ocorreu;
			if (ocorreu.equals("ocorreu")) {
				this.definirRateio("VAI ACONTECER");
			} else {
				this.definirRateio("N VAI ACONTECER");
			}
		}
	}

	public String exibirTodasApostas() {
		String saida = "";
		for (int i = 0; i<this.apostas.size(); i++) {
			saida += (this.apostas.get(i) + System.lineSeparator());
		}
		return saida;
	}
	
	public void definirRateio(String previsao) {
		for (Aposta a: apostas) {
			if (a.getPrevisao().equals(previsao)) {
				this.caixaVencedores += a.getQuantia();
			} else {
				this.caixaPerdedores += a.getQuantia();
			}
		}
	}
	
	public String getNome() {
		return nome;
	}

	public String getOcorreu() {
		return ocorreu;
	}

	public ArrayList<Aposta> getApostas() {
		return apostas;
	}

	public int getCaixaVencedores() {
		return this.caixaVencedores;
	}

	public int getCaixaPerdedores() {
		return this.caixaPerdedores;
	}

	public String getFinalizado() {
		return finalizado;
	}
	
	public int getCaixaTotal() {
		return this.caixaTotal;
	}
	
	public String toString() {
		return (this.numeracao + " - " + this.nome + " - " + this.finalizado + "(" + this.ocorreu + ")");
	}
	
	
}
