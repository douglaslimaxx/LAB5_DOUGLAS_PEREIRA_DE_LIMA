package modelos;

public class Taxa extends Seguro {

	private double taxa;
	private int valor;
	
	public Taxa (int valor, double taxa) {
		this.taxa = taxa;
	}
	
	public int getValor() {
		System.out.println((int) (this.taxa * this.valor));
		return (int) Math.floor(this.taxa * this.valor);
	}
}
