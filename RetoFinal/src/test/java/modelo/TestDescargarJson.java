package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDescargarJson {

	DescargarJSON json = new DescargarJSON();
	
	@Test
	public void DescargarJsonaTest() {
		String[] direcciones = {
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json",
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json",
				"https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json",
				"https://opendata.euskadi.eus/contenidos/ds_registros/registro_entidades_locales/opendata/entidades.json" }; 
				// Cojo los datos de las entidades municipales, que están más limpios
			
			String[] archivos = { "index.json", "estaciones.json", "espacios-naturales.json", "municipios.json" };
			
			String ruta = "./ArchivosTest/";
			json.descargarJSON(direcciones, archivos, ruta);		
	}
	
}
