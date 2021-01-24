package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestConsulta {
	Consultas Consu = new Consultas();
	@Test
	public void ConsultasTest() {
		String texto="select a from Municipios a where a.idProvincia = 48";
		String Resultado= Consu.Consultas(texto);
		String ResultadoEsperado=Resultado;
	    assertEquals(ResultadoEsperado,Resultado);	
	}
}
