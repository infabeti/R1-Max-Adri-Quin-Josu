package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestInsertarDatosGeograficos {
	InsertarDatosGeograficos Insertar = new InsertarDatosGeograficos();
	
	@Test
	public void TestleerArchivo() {
		String Ruta="ArchivosTest\\TestLeer.json";
		String Resultado=Insertar.leerArchivo(Ruta);
		String ResultadoEsperado="jsonCallback([{Test}]);";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void TestExtraerDatos() {
		String Ruta="ArchivosTest\\municipios.xml";
		String texto=Insertar.leerArchivo(Ruta);
		Municipios[] Resultado=Insertar.extraerDatosMunicipios(texto);
		Municipios[] resultadoEsperado=Resultado;
		assertEquals(resultadoEsperado,Resultado);
	}
	
	@Test
	public void TestextraerDatosEstaciones() {
		String Ruta="ArchivosTest\\estaciones.xml";
		String texto=Insertar.leerArchivo(Ruta);
		EstacionesMeteorologicas[] Resultado=Insertar.extraerDatosEstaciones(texto);
		EstacionesMeteorologicas[] resultadoEsperado=Resultado;
		assertEquals(resultadoEsperado,Resultado);
	}
}
