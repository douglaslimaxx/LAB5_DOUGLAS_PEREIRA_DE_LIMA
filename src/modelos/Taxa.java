package modelos;

public class Taxa extends Seguro {

	private double taxa;
	private int valor;
	
	public Taxa (int valor, double taxa) {
		this.taxa = taxa;
	}
	
	public int getValor() {
		return (int) (this.taxa * this.valor);
	}
}
