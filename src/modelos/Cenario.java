package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
	private List<Aposta> apostas;
	private List<ApostaAssegurada> apostaAsseguradas;
	protected int rateio;
	private int caixaPerdedores;
	private int numeracao;
	private int caixaTotal;
	private int seguros;

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
		this.apostaAsseguradas = new ArrayList<>();
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
	 * Método que cria um objeto do tipo apostaAssegurada a partir dos parâmetros
	 * e o adiciona no Arraylist de Apostas Asseguradas de cenário. Além disso ele
	 * adiciona a quantia que foi apostada no valor total das apostas daquele cenário.
	 * Essa aposta é assegurada por um valor fixo.
	 * 
	 * @param apostador
	 *            String que é nome do apostador.
	 * @param quantia
	 *            inteiro que é a quantia a ser apostada.
	 * @param previsao
	 *            String que é a previsão apostada em relação ao cenário.
	 * @param valor
	 * 				int que é o valor a ser assegurado.
	 * @return
	 * 				int que é o id da aposta feita, sendo o seu índice no array de 
	 * 				apostas asseguradas.
	 */
	public int adicionaAposta(String apostador, int quantia, String previsao, int valor) {
		if (apostador == null) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Apostador nao pode ser vazio ou nulo");
		}
		if (previsao == null) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao nao pode ser vazia ou nula");
		}
		if (quantia <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta assegurada por valor: Valor nao pode ser menor ou igual a zero");
		}
		if ((!previsao.equals("VAI ACONTECER")) && (!previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por valor: Previsao invalida");
		}
		
		ApostaAssegurada aposta = new ApostaAssegurada(apostador, quantia, previsao, valor);
		this.apostaAsseguradas.add(aposta);
		this.caixaTotal += quantia;
		return (this.apostaAsseguradas.size() - 1);
	}

	/**
	 * Método que cria um objeto do tipo apostaAssegurada a partir dos parâmetros
	 * e o adiciona no Arraylist de Apostas Asseguradas de cenário. Além disso ele
	 * adiciona a quantia que foi apostada no valor total das apostas daquele cenário.
	 * Essa aposta é assegurada por um valor fixo.
	 * 
	 * @param apostador
	 *            String que é nome do apostador.
	 * @param quantia
	 *            inteiro que é a quantia a ser apostada.
	 * @param previsao
	 *            String que é a previsão apostada em relação ao cenário.
	 * @param taxa
	 * 				double que é a taxa a ser aplicada ao valor apostado para resultar
	 * 				no valor assegurado.
	 * @return
	 * 				int que é o id da aposta feita, sendo o seu índice no array de 
	 * 				apostas asseguradas.
	 */
	public int adicionaAposta(String apostador, int quantia, String previsao, double taxa) {
		if (apostador == null) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
		if (apostador.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo");
		}
		if (previsao == null) {
			throw new NullPointerException("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
		if (previsao.trim().equals("")) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula");
		}
		if (quantia <= 0) {
			throw new NoSuchElementException(
					"Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero");
		}
		if ((!previsao.equals("VAI ACONTECER")) && (!previsao.equals("N VAI ACONTECER"))) {
			throw new IllegalArgumentException("Erro no cadastro de aposta assegurada por taxa: Previsao invalida");
		}
		ApostaAssegurada aposta = new ApostaAssegurada(apostador, quantia, previsao, taxa);
		this.apostaAsseguradas.add(aposta);
		this.caixaTotal += quantia;
		return (this.apostaAsseguradas.size() - 1);
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
		for (int i = 0; i < this.apostaAsseguradas.size(); i++) {
			saida += (this.apostaAsseguradas.get(i).toString() + System.lineSeparator());
		}
		return saida;
	}

	/**
	 * Método verifica quais apostas são perdedoras e soma a quantia apostada à
	 * caixa dos perdedores. E se a aposta perdedora for assegurada, o método
	 * adiciona o valor assegurado ao atributo seguros.
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
		for (ApostaAssegurada a : apostaAsseguradas) {
			if (a.getPrevisao().equals(previsao)) {

			} else {
				this.caixaPerdedores += a.getQuantia();
				this.seguros += a.getValor();
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
	public List<Aposta> getApostas() {
		return apostas;
	}
	
	public List<ApostaAssegurada> getApostasAsseguradas() {
		return apostaAsseguradas;
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
	 * Método que retorna um objeto do tipo Aposta Assegurada, atravéns do seu 
	 * índice no arraylist de aposta assegurada.
	 * 
	 * @param apostaAssegurada 
	 * 					int que é o índice da aposta assegurada no arraylist.
	 * @return
	 * 			ApostaAssegurada um objeto que é uma aposta assegurada.
	 */
	public ApostaAssegurada getApostaAssegurada(int apostaAssegurada) {
		return this.apostaAsseguradas.get(apostaAssegurada);
	}
	
	/**
	 * Método que retorna o valor dos seguros de todas as apostas asseguradas perdedoras.
	 * 
	 * @return
	 * 			int que é o valor de todos os seguros desse cenário.
	 */
	public int getSeguros() {
		return this.seguros;
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
