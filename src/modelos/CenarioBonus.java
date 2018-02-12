package modelos;

import java.util.NoSuchElementException;

public class CenarioBonus extends Cenario{
	
	private int bonus;
	
	/**
	 * Constrói um cenário com bônus a partir de um nome, de uma numeração e o seu valor de bônus. Inicializa outros
	 * atributos.
	 * 
	 * @param descricao
	 *            String que é a descrição do cenário.
	 * @param numeracao
	 *            int que é a numeração do cenário.
	 * @param bonus
	 * 				int que é o valor do bônus desse cenário.
	 */
	public CenarioBonus(String descricao, int numeracao, int bonus) {
		super(descricao, numeracao);
		if (bonus <= 0) {
			throw new NoSuchElementException("Erro no cadastro de cenario: Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	/**
	 * Método que muda o valor do atributo Rateio e adiciona o bonus determinado para
	 * o cenário.
	 * 
	 * @param valor
	 *            inteiro que será o valor atribuído ao rateio.
	 */
	@Override
	public void setRateio(int valor) {
		rateio = valor + this.bonus;
	}
	
	/**
	 * Método que retorna uma representação textual do cenário. A representação está
	 * seguinta forma: (Numeração do cenário) - (descrição do cenário) - (situação
	 * se foi finalizado ou não) - (e se o cenário foi finalizado, a situação se ele
	 * ocorreu ou não) - Bônus a ser somado ao rateio.
	 * 
	 * @return String que é a representação textual de Cenário.
	 */
	@Override
	public String toString() {
		String valorReais = String.format("%,.2f", this.bonus / 100.0);
		return (super.toString() + " - R$ " + valorReais);
	}

}
