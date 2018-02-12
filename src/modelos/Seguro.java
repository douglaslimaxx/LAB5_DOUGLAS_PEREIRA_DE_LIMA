package modelos;

/**
 * Representa um molde de como o seguro das aposta asseguradas serão calculados 
 * e demonstrados. 
 * 
 * @author Douglas Lima.
 *
 */
public abstract class Seguro {
	
	/**
	 * Método retorna o valor assegurado.
	 * @return
	 * 		int que é o valor assegurado. 
	 */
	public abstract int getValor();
	
	/**
	 * Retorna uma representação textual do tipo do seguro.
	 */
	public abstract String toString();
}
