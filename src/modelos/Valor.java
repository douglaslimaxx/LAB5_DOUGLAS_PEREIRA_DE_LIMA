package modelos;

public class Valor extends Seguro{
	
	private int valor;
	
	public Valor (int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public String toString() {
		String valorSaida = String.format("%,.2f", this.getValor());
		return "ASSEGURADA (VALOR) - R$ " + valorSaida;
	}
	
}
