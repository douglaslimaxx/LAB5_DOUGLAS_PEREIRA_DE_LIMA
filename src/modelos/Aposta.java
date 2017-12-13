package modelos;

public class Aposta {

	private String apostador;
	private int quantia;
	private String previsao;
	
	public Aposta(String apostador, int quantia, String previsao) {
		this.apostador = apostador;
		this.quantia = quantia;
		this.previsao = previsao;
	}

	public double getQuantia() {
		return quantia;
	}

	public String getApostador() {
		return apostador;
	}

	public String getPrevisao() {
		return previsao;
	}
	
	public String toString() {
		return (this.apostador + " - R$" + this.quantia + " - " + this.previsao);
	}
}
