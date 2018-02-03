package modelos;

public class Taxa extends Seguro {

	private double taxa;
	
	public Taxa (double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return this.taxa;
	}
}
