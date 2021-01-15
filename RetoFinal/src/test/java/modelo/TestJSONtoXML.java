package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
	@Test
	public void renombrarEtiquetasDuplicadasTestCompleto() {
		String Contenido="{\"turismDescription\" : \"El embalse de Maroño"
				+ "  \"turismDescription\" : \"El embalse de Matilde"
				+ "\"phone\" : \"\",\"phone\" : \"\","
				+ "\"address\" : \"\",\"address\" : \"\",}]);";
		String Resultado=json.renombrarEtiquetasDuplicadas(Contenido);
		String ResultadoEsperado=" }]); ]\n"+ "}";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void convertirTEST() {
		String Contenido="{}";
		String Resultado=json.convertir(Contenido,"root");
		System.out.println(Resultado);
		String ResultadoEsperado="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root></root>";
		assertEquals(Resultado,ResultadoEsperado);
	}
	
	@Test
	public void CrearArchivoTestTrue() {
		String Ruta="ArchivosTest\\TestLeer.xml";
		File file=new File(Ruta);
		String contenido = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root></root>";
		json.escribirArchivo(Ruta, contenido);
		assertTrue(file.exists());
	}
}
