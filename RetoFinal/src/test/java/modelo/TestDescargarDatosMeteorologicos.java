package modelo;

import org.junit.Test;

public class TestDescargarDatosMeteorologicos {
	DescargarDatosMetereologicos Descargar = new DescargarDatosMetereologicos();
	@Test
	public void Testdescargar() {
		Descargar.descargar();
	}
}
