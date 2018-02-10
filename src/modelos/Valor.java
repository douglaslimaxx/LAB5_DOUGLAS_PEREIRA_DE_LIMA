package modelos;

public class Valor extends Seguro{
	
	private int valor;
	
	public Valor (int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		System.out.println(this.valor);
		return this.valor;
	}
}
