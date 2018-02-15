package modelos;

import java.util.Comparator;

public class NumeroApostasComparador implements Comparator<Cenario>{

	@Override
	public int compare(Cenario c1, Cenario c2) {
		return (c1.getApostasAsseguradas().size() + c1.getApostas().size()) - (c2.getApostasAsseguradas().size() + c2.getApostas().size());
	}

}
