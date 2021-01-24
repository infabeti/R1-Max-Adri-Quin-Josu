package modelo;


import org.junit.Test;

public class TestComversionDatosMeteorologicos {
	ConvertirDatosMetereologicos Convertir = new ConvertirDatosMetereologicos();
	@Test
	public void TestComversion() {
		Convertir.comvertir();
	}
}
