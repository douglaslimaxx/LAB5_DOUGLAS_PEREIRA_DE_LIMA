package modelos;

import java.util.NoSuchElementException;

public class ApostaSegurada  extends Aposta{

	private Seguro tipo;
	private int valorAssegurada;
	private int custo;
	
	public ApostaSegurada(String apostador, int quantia, String previsao, int valor, int custo) {
		super(apostador, quantia, previsao);
		this.tipo = new Valor(valor);
		this.custo = custo;
	}
	
	public ApostaSegurada(String apostador, int quantia, String previsao, double taxa, int custo) {
		super(apostador, quantia, previsao);
		this.tipo = new Taxa(quantia, taxa);
		this.custo = custo;
	}

	public void setTipo(Seguro tipo) {
		this.tipo = tipo;
	}
	
	public void mudarTipo(int valor) {
		tipo = new Valor(valor);
	}
	
	public void mudarTipo(double taxa) {
		tipo = new Taxa(quantia,  taxa);
	}
	
	public int getCusto() {
		return this.custo;
	}
}
