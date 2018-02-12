package modelos;

/**
 * Representa o tipo Valor de seguro de Aposta.
 * 
 * @author Douglas Lima.
 *
 */
public class Valor extends Seguro{
	
	private int valor;
	
	/**
	 * Constrói um objeto do tipo Valor a partir do paramêtro Valor.
	 * @param valor int que é o valor assegurado.
	 */
	public Valor (int valor) {
		this.valor = valor;
	}
	
	/**
	 * Método retorna o valor do seguro.
	 * 
	 * @return int que é o valor assegurado.
	 */
	public int getValor() {
		return this.valor;
	}
	
	/**
	 * Método que retorna uma representação textual do tipo do seguro. A 
	 * representação está da seguinte forma: "ASSEGURADA (VALOR) - R$ (Valor
	 * asserurado).
	 */
	public String toString() {
		String valorSaida = String.format("%,.2f", this.getValor());
		return "ASSEGURADA (VALOR) - R$ " + valorSaida;
	}
	
}
