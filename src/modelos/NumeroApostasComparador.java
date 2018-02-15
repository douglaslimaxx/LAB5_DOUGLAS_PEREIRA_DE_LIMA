package modelos;

import java.util.Comparator;

public class NumeroApostasComparador implements Comparator<Cenario>{

	@Override
	public int compare(Cenario c1, Cenario c2) {
		if ((c2.getApostasAsseguradas().size() + c2.getApostas().size()) - (c1.getApostasAsseguradas().size() + c1.getApostas().size()) == 0) {
			return c1.getNumeracao() - c2.getNumeracao();
		}else {
			return (c2.getApostasAsseguradas().size() + c2.getApostas().size()) - (c1.getApostasAsseguradas().size() + c1.getApostas().size());
		}
	}

}
