package modelos;

import java.util.ArrayList;

/**
 * Representa um cenário com descrição, uma lista de objetos do tipo Aposta,
 * Strings que representam se o cenário foi finalizado e se ele ocorreu ou não.
 * Além disso um cenário terá inteiros que representam o caixa total de apostas,
 * o valor total da soma das apostas perdedoras, o valor que será distribuído
 * aos vencedores que é o rateio e a numeração desse cenário. Um cenário também
 * pode adicionar apostas, ser finalizado, exibir todas as suas apostas, definir
 * o rateio.
 * 
 * @author Douglas Lima
 *
 */
public class Cenario {

	private String descricao;
	private String ocorreu;
	private String finalizado;
	private ArrayList<Aposta> apostas;
	private int rateio;
	private int caixaPerdedores;
	private int numeracao;
	private int caixaTotal;

	/**
	 * Constrói um cenário a partir de um nome e de uma numeração. Inicializa outros
	 * atributos.
	 * 
	 * @param nome
	 *            String que é a descrição do cenário.
	 * @param numeracao
	 *            int que é a numeração do cenário.
	 */
	public Cenario(String descricao, int numeracao) {
		if (descricao == null) {
			throw new NullPointerException("Erro no cadastro de cenario: Descricao nao pode ser nula");
		}
		if (descricao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de cenario: Descricao nao pode ser vazia");
		}
		this.descricao = descricao;
		this.numeracao = numeracao;
		this.ocorreu = "";
		this.apostas = new ArrayList<>();
		this.finalizado = "Nao finalizado";
	}

	/**
	 * Método que cria um objeto do tipo aposta a partir dos parâmetros e o adiciona
	 * no Arraylist de Apostas de cenário. Além disso ele adiciona a quantia que foi
	 * apostada no valor total das apostas daquele cenário.
	 * 
	 * @param apostador
	 *            String que é nome do apostador.
	 * @param quantia
	 *            inteiro que é a quantia a ser apostada.
	 * @param previsao
	 *            String que é a previsão apostada em relação ao cenário.
	 */
	public void adicionaAposta(String apostador, int quantia, String previsao) {
		Aposta aposta = new Aposta(apostador, quantia, previsao);
		this.apostas.add(aposta);
		this.caixaTotal += quantia;
	}

	/**
	 * Método exibe uma representação textual de todas as apostas feitas nesse
	 * cenário.
	 * 
	 * @return String que é uma representação textual de todas as apostas feitas no
	 *         cenário.
	 */
	public String exibeTodasApostas() {
		String saida = "";
		for (int i = 0; i < this.apostas.size(); i++) {
			saida += (this.apostas.get(i).toString() + System.lineSeparator());
		}
		return saida;
	}

	/**
	 * Método verifica quais apostas são perdedoras e soma a quantia apostada à
	 * caixa dos perdedores.
	 * 
	 * @param previsao
	 *            String que é a previsão de quem ganha.
	 */
	private void defineCaixaPerdedores(String previsao) {
		for (Aposta a : apostas) {
			if (a.getPrevisao().equals(previsao)) {

			} else {
				this.caixaPerdedores += a.getQuantia();
			}
		}
	}

	/**
	 * Método finaliza o cenário, mudando o atributo finalizado, somente se ele já
	 * não foi finalizado. E após finalizado ele determina também se o cenário
	 * ocorreu ou não a partir do parâmetro previsao.
	 * 
	 * @param ocorreu
	 *            String que afirma se o cenário ocorreu ou não.
	 */
	public void setFinalizado(String ocorreu) {
		this.finalizado = "Finalizado";
		this.ocorreu = ocorreu;
		if (ocorreu.equals("ocorreu")) {
			this.defineCaixaPerdedores("VAI ACONTECER");
		} else {
			this.defineCaixaPerdedores("N VAI ACONTECER");
		}
	}

	/**
	 * Método que muda o valor do atributo Rateio.
	 * 
	 * @param valor
	 *            inteiro que será o valor atribuído ao rateio.
	 */
	public void setRateio(int valor) {
		this.rateio = valor;
	}

	/**
	 * Método que retorna a lista de objetos do tipo Aposta que são armazenados em
	 * Cenário.
	 * 
	 * @return Um ArrayList de objetos do tipo Aposta.
	 */
	public ArrayList<Aposta> getApostas() {
		return apostas;
	}

	/**
	 * Método que retorna o valor do rateio que será destinado aos vencedores.
	 * 
	 * @return um inteiro que é o valor do rateio.
	 */
	public int getRateio() {
		return this.rateio;
	}

	/**
	 * Método que retorna o caixa do perdedoras que é a soma da quantia de todas as
	 * apostas perdedoras.
	 * 
	 * @return Um inteiro que é o valor do caixa do Perdedores.
	 */
	public int getCaixaPerdedores() {
		return this.caixaPerdedores;
	}

	/**
	 * Método retorna o caixa total que é a soma da quantia de todas as apostas.
	 * 
	 * @return Um inteiro que é o valor do caixa total.
	 */
	public int getCaixaTotal() {
		return this.caixaTotal;
	}
	
	/**
	 * Método retorna a descrição do cenário.
	 * 
	 * @return String que é a descrição do cenário
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Método retorna uma String que é a situação em relação ao encerramento das
	 * apostas do cenário, se ele foi finalizado ou não
	 * 
	 * @return String que é a representação da situação do cenário em relação ao seu
	 *         encerramento.
	 */
	public String getFinalizado() {
		return this.finalizado;
	}
	
	/**
	 * Método retorna uma String que é se a situação do cenário ocorreu ou não
	 * 
	 * @return String que é demonstra se a situação do cenário ocorreu ou não.
	 */
	public String getOcorreu() {
		return this.ocorreu;
	}

	/**
	 * Método que retorna uma representação textual do cenário. A representação está
	 * seguinta forma: (Numeração do cenário) - (descrição do cenário) - (situação
	 * se foi finalizado ou não) - (e se o cenário foi finalizado, a situação se ele
	 * ocorreu ou não).
	 * 
	 * @return String que é a representação textual de Cenário.
	 */
	public String toString() {
		if (this.finalizado.equals("Nao finalizado")) {
			return (this.numeracao + " - " + this.descricao + " - " + this.finalizado);
		} else {
			return (this.numeracao + " - " + this.descricao + " - " + this.finalizado + "(" + this.ocorreu + ")");
		}
	}
}
