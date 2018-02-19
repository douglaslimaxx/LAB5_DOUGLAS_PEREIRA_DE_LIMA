package modelos;

import java.util.Comparator;

/**
 * Representa um paramêtro pra comparação entre cenário, que seria pelo nome dos 
 * cenário em ordem alfabética.
 * @author Douglas Lima.
 *
 */
public class NomeComparador implements Comparator<Cenario>{

	/**
	 * Método que pega dois objetos do tipo cenário e os compara a partir do nome 
	 * desse cenários, em ordem alfabética.
	 */
	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getDescricao().compareTo(c2.getDescricao());
	}

}