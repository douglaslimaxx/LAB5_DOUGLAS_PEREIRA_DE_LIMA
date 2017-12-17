package modelos;

import java.util.NoSuchElementException;

/**
 * Representa um aposta que irá ter o nome de quem apostou, a quantia apostada e
 * a previsão do cenário onde a aposta está sendo feita
 * 
 * @author Douglas Lima
 *
 */
public class Aposta {

	private String apostador;
	private int quantia;
	private String previsao;

	/**
	 * Constrói uma aposta a partir dos parâmetros apostados e previsao que são
	 * Strings e o parâmetro quantia que é um inteiro.
	 * 
	 * @param apostador
	 *            String que é o nome de quem fez a aposta.
	 * @param quantia
	 *            Inteiro que é o quanto em centavos foi apostado.
	 * @param previsao
	 *            String que é previsão do cenário.
	 */
	public Aposta(String apostador, int quantia, String previsao) {
		if (apostador == null) {
			throw new NullPointerException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo");
		}
		if (previsao == null) {
			throw new NullPointerException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula");
		}
		if (quantia <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero");
		}
		if ((!previsao.equals("VAI ACONTECER")) && (!previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta: Previsao invalida");
		}
		this.apostador = apostador;
		this.quantia = quantia;
		this.previsao = previsao;
	}

	/**
	 * Método retorna o valor do atributo quantia.
	 * 
	 * @return Inteiro que é a quantia que foi apostada.
	 */
	public int getQuantia() {
		return quantia;
	}

	/**
	 * Método retorna o valo do atributo apostador.
	 * 
	 * @return String que é o nome de quem apostou.
	 */
	public String getApostador() {
		return apostador;
	}

	/**
	 * Método retorna o valor do atributo previsao.
	 * 
	 * @return String que é a previsão sobre o cenário.
	 */
	public String getPrevisao() {
		return previsao;
	}

	/**
	 * Método retorna uma representação textual de uma aposta. A representação está
	 * da seguinte forma: (Nome do apostador) - R$ (a quantia em centavos convertida
	 * para reais) - (previsão da aposta).
	 * 
	 * @return String que é auma representação textual da aposta.
	 */
	public String toString() {
		String valorReais = String.format("%,.2f", this.quantia / 100.0);
		return (this.apostador + " - R$" + valorReais + " - " + this.previsao);
	}
}
