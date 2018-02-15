package modelos;

import java.util.Comparator;

public class IdComparador implements Comparator<Cenario>{

	@Override
	public int compare(Cenario c1, Cenario c2) {
		return c1.getNumeracao() - c2.getNumeracao();
	}

}
