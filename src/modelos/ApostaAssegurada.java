package modelos;

public class ApostaAssegurada  extends Aposta{

	private Seguro tipo;
	
	public ApostaAssegurada(String apostador, int quantia, String previsao, int valor) {
		super(apostador, quantia, previsao);
		this.tipo = new Valor(valor);
		System.out.println(this.getValor());
	}
	
	public ApostaAssegurada(String apostador, int quantia, String previsao, double taxa) {
		super(apostador, quantia, previsao);
		this.tipo = new Taxa(quantia, taxa);
		System.out.println(this.getValor());
	}

	public void setTipo(Seguro tipo) {
		this.tipo = tipo;
	}
	
	public void mudarTipo(int valor) {
		tipo = new Valor(valor);
		System.out.println(this.getValor());
	}
	
	public void mudarTipo(double taxa) {
		tipo = new Taxa(quantia,  taxa);
		System.out.println(this.getValor());
	}
	
	public int getValor() {
		return this.tipo.getValor();
	}
}
