package modelos;

import java.util.NoSuchElementException;

/**
 * Representa o tipo Taxa de seguro de Aposta.
 * 
 * @author Douglas Lima.
 *
 */
public class Taxa extends Seguro {

	private double taxa;
	private int valor;

	/**
	 * Constrói um objeto do tipo Taxa a partir do paramêtros valor e taxa.
	 * 
	 * @param valor
	 *            int que o valor apostado.
	 * @param taxa
	 *            double que é a taxa a ser aplicado ao valor apostado para obter o
	 *            valor do seguro
	 */
	public Taxa(int valor, double taxa) {
		if (taxa <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
		this.taxa = taxa;
		this.valor = valor;
	}

	/**
	 * Método retorna o valor do seguro.
	 * 
	 * @return int que é o valor assegurado.
	 */
	public int getValor() {
		return (int) Math.floor(this.taxa * this.valor);
	}

	/**
	 * Método que retorna uma representação textual do tipo do seguro. A
	 * representação está da seguinte forma: "ASSEGURADA (TAXA) - (Porcentagem
	 * assegurada)%".
	 */
	public String toString() {
		return " - ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
	}
}
