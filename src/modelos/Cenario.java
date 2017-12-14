package modelos;

import java.util.ArrayList;

/**
 * Representa um cenário com descrição, uma lista de objetos do tipo 
 * Aposta, Strings que representam se o cenário foi finalizado e se 
 * ele ocorreu ou não. Além disso um cenário terá inteiros que
 * representam o caixa total de apostas, o valor total da soma das
 * apostas perdedoras, o valor que será destinado ao caixa do sistema
 * o valor que será distribuído aos vencedores que é o rateio e a
 * numeração desse cenário. Um cenário também pode adicionar apostas,
 * ser finalizado, exibir todas as suas apostas, definir o rateio.  
 * @author douglas
 *
 */
public class Cenario {

	private String nome;
	private String ocorreu;
	private String finalizado;
	private ArrayList<Aposta> apostas;
	private int rateio;
	private int caixaPerdedores;
	private int destinadoAoCaixa;
	private int numeracao;
	private int caixaTotal;

	public Cenario(String nome, int numeracao) {
		this.nome = nome;
		this.numeracao = numeracao;
		this.ocorreu = "";
		this.apostas = new ArrayList<>();
		this.finalizado = "Não Finalizado";
	}
	public void adicionaAposta(String apostador, int quantia, String previsao) {
		Aposta aposta = new Aposta(apostador, quantia, previsao);
		this.apostas.add(aposta);
		this.caixaTotal += quantia;
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
				
			} else {
				this.caixaPerdedores += a.getQuantia();
			}
		}
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
	
	public void setCaixaPerdedores(int valor) {
		this.caixaPerdedores = valor;
	}
	
	public void setRateio(int valor) {
		this.rateio = valor;
	}
	
	public void setDestinadoAoCaixa(int destinadoAoCaixa) {
		this.destinadoAoCaixa = destinadoAoCaixa;
	}

	public ArrayList<Aposta> getApostas() {
		return apostas;
	}

	public int getRateio() {
		return this.rateio;
	} 

	public int getCaixaPerdedores() {
		return this.caixaPerdedores;
	}
	
	public int getCaixaTotal() {
		return this.caixaTotal;
	}
	
	public String toString() {
		return (this.numeracao + " - " + this.nome + " - " + this.finalizado + "(" + this.ocorreu + ")");
	}
	
	
}
