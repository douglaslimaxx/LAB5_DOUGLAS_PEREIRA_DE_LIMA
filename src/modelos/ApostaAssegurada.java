package modelos;

/**
 * Representa um aposta assegurada que terá o nome de quem apostou, a quantia apostada,
 * a previsão do cenário onde a aposta está sendo feita, e um valor ou uma taxa 
 * referentes ao valor assegurado
 * 
 * @author Douglas Lima
 *
 */
public class ApostaAssegurada  extends Aposta{

	private Seguro tipoSeguro;
	
	/**
	 * Constrói uma aposta assegurada por valor a partir dos parâmetros apostador, 
	 * previsao, quantia e valor.
	 * 
	 * @param apostador
	 *            String que é o nome de quem fez a aposta.
	 * @param quantia
	 *            Inteiro que é o quanto em centavos foi apostado.
	 * @param previsao
	 *            String que é previsão do cenário.
	 * @param valor
	 * 				int que é o valor a ser assegurado.
	 */
	
	public ApostaAssegurada(String apostador, int quantia, String previsao, int valor) {
		super(apostador, quantia, previsao);
		this.tipoSeguro = new Valor(valor);
	}
	
	/**
	 * Constrói uma aposta assegurada por taxa a partir dos parâmetros apostador, 
	 * previsao, quantia e taxa.
	 * 
	 * @param apostador
	 *            String que é o nome de quem fez a aposta.
	 * @param quantia
	 *            Inteiro que é o quanto em centavos foi apostado.
	 * @param previsao
	 *            String que é previsão do cenário.
	 * @param taxa
	 * 				double que é a taxa a ser aplicada no valor apostado para resultar
	 * 				no valor  a ser assegurado. 
	 */
	public ApostaAssegurada(String apostador, int quantia, String previsao, double taxa) {
		super(apostador, quantia, previsao);
		this.tipoSeguro = new Taxa(quantia, taxa);
	}
	
	/**
	 * Método que atribui à variável tipoSeguro um objeto do tipo Valor, a partir 
	 * do paramêtro valor.
	 * 
	 * @param valor
	 * 				int que é o valor a ser assegurado.
	 */
	public void mudarTipo(int valor) {
		tipoSeguro = new Valor(valor);
	}
	
	/**
	 * Método que atribui à variável tipoSeguro um objeto do tipo Taxa, a partir 
	 * do paramêtro Taxa
	 * @param taxa
	 * 				double que é a taxa a ser aplicada no valor apostado para resultar
	 * 				no valor  a ser assegurado. 
	 */
	public void mudarTipo(double taxa) {
		tipoSeguro = new Taxa(quantia,  taxa);
	}
	
	/**
	 * Método que retorna o valor que será assegurado na aposta.
	 * @return
	 */
	public int getValor() {
		return this.tipoSeguro.getValor();
	}
	
	/**
	 * Método retorna uma representação textual de uma aposta. A representação está
	 * da seguinte forma: (Nome do apostador) - R$ (a quantia em centavos convertida
	 * para reais) - (previsão da aposta) - A representação textual do tipo da variável
	 * tipoSeguro.
	 * 
	 * @return String que é uma representação textual da aposta assegurada.
	 */
	@Override
	public String toString() {
			return super.toString() + this.tipoSeguro.toString();
	}
}
