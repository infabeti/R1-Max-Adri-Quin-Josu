package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestInsertarDatosAdmosfericos {

	InsertarDatosAtmosfericos Da = new InsertarDatosAtmosfericos();
	InsertarDatosGeograficos obj = new InsertarDatosGeograficos();
	@Test
	public void TestextraerDatosCalidadAire0() {
		String texto=obj.leerArchivo("ArchivosTest\\estaciones.xml");
		CalidadAire Resultado= Da.extraerDatosCalidadAire("xml", texto, 0);
		CalidadAire ResultadoEsperado=Resultado;
	    assertEquals(ResultadoEsperado,Resultado);		
	}
	@Test
	public void TestextraerDatosCalidadAire1() {
		String texto=obj.leerArchivo("ArchivosTest\\estaciones.xml");
		CalidadAire Resultado= Da.extraerDatosCalidadAire("xml", texto, 0);
		CalidadAire ResultadoEsperado=Resultado;
	    assertEquals(ResultadoEsperado,Resultado);		
	}

}
