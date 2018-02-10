package modelos;

public class Taxa extends Seguro {

	protected double taxa;
	private int valor;
	
	public Taxa (int valor, double taxa) {
		this.taxa = taxa;
		this.valor = valor;
	}
	public int getValor() {
		return (int) Math.floor(this.taxa * this.valor);
	}
	
	public String toString() {
		return "ASSEGURADA (TAXA) - " + (int) (this.taxa * 100) + "%";
	}
}
