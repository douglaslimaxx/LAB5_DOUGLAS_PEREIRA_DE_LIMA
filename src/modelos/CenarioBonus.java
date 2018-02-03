package modelos;

public class CenarioBonus extends Cenario{
	
	private int bonus;
	
	public CenarioBonus(String descricao, int numeracao, int bonus) {
		super(descricao, numeracao);
		this.bonus = bonus;
	}
	
	/**
	 * Método que muda o valor do atributo Rateio.
	 * 
	 * @param valor
	 *            inteiro que será o valor atribuído ao rateio.
	 */
	public void setRateio(int valor) {
		rateio = valor + this.bonus;
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
		return (super.toString() + " - R$ " + this.bonus);
	}

}
