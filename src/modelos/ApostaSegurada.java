package modelos;

public class ApostaSegurada  extends Aposta{

	private Seguro tipo;
	
	public ApostaSegurada(String apostador, int quantia, String previsao, int valor) {
		super(apostador, quantia, previsao);
		this.tipo = new Valor(valor);
	}
	
	public ApostaSegurada(String apostador, int quantia, String previsao, double taxa) {
		super(apostador, quantia, previsao);
		this.tipo = new Taxa(taxa);
	}

	public void setTipo(Seguro tipo) {
		this.tipo = tipo;
	}
}
