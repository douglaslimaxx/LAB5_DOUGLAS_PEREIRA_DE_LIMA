package modelos;

/**
 * Representa um aposta que irá ter o nome de quem apostou, a quantia
 * apostada e a previsão do cenário onde a aposta está sendo feita
 * @author Douglas Lima
 *
 */
public class Aposta {

	private String apostador;
	private int quantia;
	private String previsao;
	
	/**
	 * Constrói uma aposta a partir dos parâmetros apostados e previsao
	 * que são Strings e o parâmetro quantia que é um inteiro.
	 * @param apostador String que é o nome de quem fez a aposta.
	 * @param quantia Inteiro 
	 * @param previsao
	 */
	public Aposta(String apostador, int quantia, String previsao) {
		this.apostador = apostador;
		this.quantia = quantia;
		this.previsao = previsao;
	}

	public double getQuantia() {
		return quantia;
	}

	public String getApostador() {
		return apostador;
	}

	public String getPrevisao() {
		return previsao;
	}
	
	public String toString() {
		return (this.apostador + " - R$" + (this.quantia/100.0) + " - " + this.previsao);
	}
}
