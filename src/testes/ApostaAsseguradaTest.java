package testes;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import modelos.ApostaAssegurada;

public class ApostaAsseguradaTest {

	private ApostaAssegurada apostaValor;
	private ApostaAssegurada apostaTaxa;

	@Before
	public void setUp() {
		apostaValor = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", 200);
		apostaTaxa = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", 0.05);
	}
	
	@Test
	public void  testeApostaValorZero() {
		try {
			apostaValor = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", 0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void  testeApostaValorMenorZero() {
		try {
			apostaValor = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", -50);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro no cadastro de aposta assegurada por valor: Valor assegurado nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void  testeApostaValoTaxaZero() {
		try {
			apostaTaxa = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", 0.0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void  testeApostaTaxaMenorZero() {
		try {
			apostaTaxa = new ApostaAssegurada("Douglas", 1000, "VAI ACONTECER", -0.02);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro no cadastro de aposta assegurada por taxa: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeMudarTipoValorZero() {
		try {
			apostaTaxa.mudarTipo(0);
		} catch (NoSuchElementException vz) {
			assertEquals(vz.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeMudarTipoValorMenorZero() {
		try {
			apostaTaxa.mudarTipo(-200);
		} catch (NoSuchElementException vmz) {
			assertEquals(vmz.getMessage(), "Erro na mudança do seguro para valor de aposta assegurada: Valor nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeMudarTipoTaxaZero() {
		try {
			apostaValor.mudarTipo(0.0);
		} catch (NoSuchElementException tz) {
			assertEquals(tz.getMessage(), "Erro na mudança do seguro para taxa de aposta assegurada: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeMudarTipoParaTaxaCorreto() {
		apostaValor.mudarTipo(0.06);
		String msg = "Ao mudar o tipo de seguro, o valor assegurado deve ser de 60";
		assertEquals(msg, 60, apostaValor.getValor());
	}
	
	@Test
	public void testeMudarTipoTaxaMenorZero() {
		try {
			apostaValor.mudarTipo(-0.05);
		} catch (NoSuchElementException tmz) {
			assertEquals(tmz.getMessage(), "Erro na mudança do seguro para taxa de aposta assegurada: Taxa nao pode ser menor ou igual a zero");
		}
	}
	
	@Test
	public void testeMudarTipoParaValorCorreto() {
		apostaTaxa.mudarTipo(100);
		String msg = "Ao mudar o tipo de seguro, o valor assegurado deve ser de 100";
		assertEquals(msg, 100, apostaTaxa.getValor());
	}
	
	@Test
	public void testeGetValorTipoValor() {
		String msg = "Valor do seguro deve ser 200";
		assertEquals(msg, 200, apostaValor.getValor());
	}
	
	@Test
	public void testeGetValorTipoTaxa() {
		String msg = "Valor do seguro deve ser 50";
		assertEquals(msg, 50, apostaTaxa.getValor());
	}
	
	@Test
	public void testeToStringTipoValor() {
		String msg = "A representação textual de uma aposta assegurada por valor deve ser da seguinte forma: ";
		assertEquals(msg, "Douglas - R$10,00 - VAI ACONTECER - ASSEGURADA (VALOR) - R$ 200,00", apostaValor.toString());
	}
	
	@Test
	public void testeToStringTipoTaxa() {
		String msg = "A representação textual de uma aposta assegurada por taxa deve ser da seguinte forma: ";
		assertEquals(msg, "Douglas - R$10,00 - VAI ACONTECER - ASSEGURADA (TAXA) - 5%", apostaTaxa.toString());
	}

}
