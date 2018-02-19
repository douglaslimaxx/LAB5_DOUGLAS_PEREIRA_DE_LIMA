package modelos;

import java.util.Comparator;

/**
 * Representa um paramêtro pra comparação entre cenário, que seria pelo número 
 * que cada cenário tem.
 * @author Douglas Lima
 *
 */
public class NumeroApostasComparador implements Comparator<Cenario>{

	/**
	 * Método que pega dois objetos do tipo cenário e os compara a partir do número 
	 * de aposta. Mas caso o número de apostas do cenários seja igual, a comparação 
	 * será feita com os id dos cenários.
	 */
	@Override
	public int compare(Cenario c1, Cenario c2) {
		if ((c2.getApostasAsseguradas().size() + c2.getApostas().size()) - (c1.getApostasAsseguradas().size() + c1.getApostas().size()) == 0) {
			return c1.getNumeracao() - c2.getNumeracao();
		}else {
			return (c2.getApostasAsseguradas().size() + c2.getApostas().size()) - (c1.getApostasAsseguradas().size() + c1.getApostas().size());
		}
	}

}
