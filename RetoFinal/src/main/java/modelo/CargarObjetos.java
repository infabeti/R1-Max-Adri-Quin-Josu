package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CargarObjetos {
	
	//Carga los objetos con los municipios del .\archivos\municipios.xml
	private String nombre;
	private Provincias provincias;
	public static void main(String[] args) throws FileNotFoundException, IOException {
		JSONtoXML conversor = new JSONtoXML();
		String direccionArchivoEntrada = "./archivos/index.json";
		String xml = conversor.leerArchivo(direccionArchivoEntrada); //Carga el xml en un String
		for (int i=0; i<xml.length();i++) {
		}

	}

}
