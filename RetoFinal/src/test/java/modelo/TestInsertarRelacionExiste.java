package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestInsertarRelacionExiste {

	InsertarRelacionExiste RelacionExiste = new InsertarRelacionExiste();
	
	@Test
	public void leerArchivoTest() {	
		String Ruta="ArchivosTest\\TestLeer.json";
		String Resultado=RelacionExiste.leerArchivo(Ruta);
		String ResultadoEsperado="jsonCallback([{Test}]);";
		assertEquals(Resultado,ResultadoEsperado);
	}
	
	@Test
	public void extraerDatoTest() {	
		String Contenido="<Prueba> texto </Prueba> <Etiqueta> texto </Etiqueta> <Prueba> texto </Prueba>";
		String Resultado=RelacionExiste.extraerDato(Contenido,"Etiqueta");
		String ResultadoEsperado=" texto ";
		assertEquals(Resultado,ResultadoEsperado);
	}
	@Test
	public void extraerDatoTestSinDato() {	
		String Contenido="<Prueba> texto </Prueba>";
		String Resultado=RelacionExiste.extraerDato(Contenido,"Etiqueta");
		String ResultadoEsperado="";
		assertEquals(Resultado,ResultadoEsperado);
	}
	
	@Test
	public void RelacionExisteSITest() {	
		String Ruta="ArchivosTest\\espacios-naturales.xml";
		String Contenido=RelacionExiste.leerArchivo(Ruta);
		Object[] Resultado=RelacionExiste.extraerDatosRelacionExiste(Contenido);
	System.out.println(Resultado.length);
		assertTrue(Resultado.length>0);
	}
}
