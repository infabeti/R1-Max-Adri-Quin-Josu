package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestJSONtoXML {

	private JSONtoXML json = new JSONtoXML();
	
	@Test
	public void leerArchivoTest() {
		String Ruta="ArchivosTest\\TestLeer.json";
		String Resultado=json.leerArchivo(Ruta);
		String ResultadoEsperado="jsonCallback([{Test}]);";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void prepararArchivoTest1Campo() {
		String Contenido="jsonCallback([{Test}]);";
		String Resultado=json.prepararArchivo(Contenido,"Test");
		String ResultadoEsperado="{\n"+ "\"Test\": [ \n"+"{Test\n"+"}\n"+"]\n"+"}";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void prepararArchivoTestMasCampos() {
		String Contenido="{Test}{Test}{Test}{Test}]);";
		String Resultado=json.prepararArchivo(Contenido,"Test");
		String ResultadoEsperado="{Test\n"+ "}{Test\n"+ "}{Test\n"+"}{Test}\n"+ "]\n"+ "}";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void renombrarEtiquetasDuplicadasTestSimple() {
		String Contenido="{Test}{Test}{Test}{Test}]);";
		String Resultado=json.renombrarEtiquetasDuplicadas(Contenido);
		String ResultadoEsperado="{Test }{Test }{Test }{Test }]); ]\n"+ "}";
		assertEquals(Resultado,ResultadoEsperado);
	}

}
