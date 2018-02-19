package modelos;

import java.util.Comparator;

/**
 * Representa um paramêtro pra comparação entre cenário, que seria pela numeração 
 * do id do cenário.
 * @author Douglas Lima.
 *
 */
public class IdComparador implements Comparator<Cenario>{

	/**
	 * Método que pega dois objetos do tipo cenário e os compara a partir da 
	 * numeração do id do cenário.
	 */
	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getNumeracao() - c2.getNumeracao();
	}

}
