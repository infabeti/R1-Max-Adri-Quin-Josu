package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestCompararJson {
	CompararJSON Cifrado = new CompararJSON();
	@Test
	public void cifrarTest() {
		String Resultado= Cifrado.cifrar("1");
		String ResultadoEsperado="null53106254312119-807684877724-62-11570-26578440-85";
	    assertEquals(ResultadoEsperado,Resultado);	
	}
	@Test
	public void compararCifradoTestTRUE() {
	    assertTrue(Cifrado.compararCifrado("1","1"));	
	}
	@Test
	public void compararCifradoTestFALSE() {
		assertFalse(Cifrado.compararCifrado("1","2"));	
	}
}
