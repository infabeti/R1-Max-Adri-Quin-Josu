package modelo;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;

public class TestConsulta {
	Consultas Consu = new Consultas();
	@Test
	public void ConsultasTest() {
		String texto="select a from Municipios a where a.idProvincia = 48";
		List Resultado= Consu.Consultas(texto);
		List ResultadoEsperado=Resultado;
	    assertEquals(ResultadoEsperado,Resultado);	
	}
}
