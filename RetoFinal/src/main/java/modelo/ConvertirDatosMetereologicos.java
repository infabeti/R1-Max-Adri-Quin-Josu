package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ConvertirDatosMetereologicos {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		comvertir();
	
	}
	public static void comvertir() {
		JSONtoXML conversor = new JSONtoXML();
		DescargarJSON descarga = new DescargarJSON();
		String[] lineas;
		String direccionArchivoEntrada = "./archivos/index.json";
		String listadoUrls = "";
		String[] direcciones;
		String[] archivos;
		String[] auxiliar;
		String paso, acum = "";
		// Lee el archivo JSON
		String jsonOrigen = conversor.leerArchivo(direccionArchivoEntrada); // Lee el archivo

		// Obtiene las direcciones de descarga
		lineas = jsonOrigen.split(",");
		for (int i = 0; i < lineas.length; i++) {
			if (lineas[i].contains("datos_horarios"))
				listadoUrls += lineas[i].substring(15, lineas[i].length() - 7) + ",";
		}
		direcciones = listadoUrls.split(",");

		// Saca los nombres de los archivos de las direcciones
		for (int i = 0; i < direcciones.length; i++) {
			auxiliar = direcciones[i].split("/");
			paso = auxiliar[auxiliar.length - 1];
			acum += paso.substring(0, paso.length()) + ",";
		}
		archivos = acum.split(",");

		String ruta = "./archivos/calidadAire/";

		// Convierte los archivos .JSON a .XML
		String nombreArchivos = "";
		String[] nombreArchivo;
		for (int i = 0; i < archivos.length; i++) {
			nombreArchivos += archivos[i].substring(0, archivos[i].length() - 5) + ",";
		}
		nombreArchivo = nombreArchivos.split(",");

		//Recorre todos los archivos
		for (int num = 0; num < archivos.length; num++) {
			String direccionArchivoEntrada2 = ruta + nombreArchivo[num] + ".json";
			String direccionArchivoSalida = ruta + nombreArchivo[num] + ".xml";

			jsonOrigen = conversor.leerArchivo(direccionArchivoEntrada2); // Lee el archivo
			
			if (jsonOrigen.length()>0) {
				
			
			String jsonPreparado = conversor.prepararArchivo(jsonOrigen, nombreArchivo[num]); // Prepara el archivo: quita cabecera

			String xml = conversor.convertir(jsonPreparado, nombreArchivo[num]); // Establezco el nombre del tag raiz del XML

			conversor.escribirArchivo(direccionArchivoSalida, xml); // Escribe el archivo XML
			}
		}
	}
	
}
