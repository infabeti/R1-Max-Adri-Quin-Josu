package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DescargarDatosMetereologicos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
	JSONtoXML conversor = new JSONtoXML();
	DescargarJSON descarga = new DescargarJSON();
	String[] lineas;
	String direccionArchivoEntrada = "./archivos/index.json";
	String listadoUrls = "";
	String[] direcciones;
	String[] archivos;
	String[] auxiliar;
	String paso, acum="";
	// Lee el archivo JSON
	String jsonOrigen = conversor.leerArchivo(direccionArchivoEntrada); // Lee el archivo

	//Obtiene las direcciones de descarga
	lineas = jsonOrigen.split(",");
	for (int i=0; i<lineas.length; i++) {
	    if (lineas[i].contains("datos_horarios"))
		listadoUrls +=lineas[i].substring(15, lineas[i].length()-7)+",";
	}
	direcciones = listadoUrls.split(",");
	
	//Obtiene los nombres de los archivos
	for (int i=0; i<direcciones.length; i++) {
	    auxiliar = direcciones[i].split("/");
	   paso = auxiliar[auxiliar.length-1];
	   acum += paso.substring(0,paso.length())+",";
	}
	archivos = acum.split(",");	
	
	String ruta = "./archivos/calidadAire/";
		
	descarga.descargarJSON(direcciones, archivos, ruta);
    }

}
