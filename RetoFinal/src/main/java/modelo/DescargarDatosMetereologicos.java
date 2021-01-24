package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DescargarDatosMetereologicos {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		descargar();
	}
	public static void descargar() {
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

		// Obtiene los nombres de los archivos
		for (int i = 0; i < direcciones.length; i++) {
			auxiliar = direcciones[i].split("/");
			paso = auxiliar[auxiliar.length - 1];
			acum += paso.substring(0, paso.length()) + ",";
		}
		archivos = acum.split(",");

		String ruta = "./archivos/calidadAire/";

		descarga.descargarJSON(direcciones, archivos, ruta);

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
			System.out.println("LEER ARCHIVO" + nombreArchivo[num] + ".json");

			if (jsonOrigen.length()>0) {
				String jsonPreparado = conversor.prepararArchivo(jsonOrigen, nombreArchivo[num]); // Prepara el archivo: quita cabecera
				System.out.println("PREPARAR ARCHIVO" + nombreArchivo[num] + ".json");

				String xml = conversor.convertir(jsonPreparado, nombreArchivo[num]); // Establezco el nombre del tag raiz																		// del XML
				System.out.println("CONVERTIR ARCHIVO" + nombreArchivo[num] + ".json --> " + nombreArchivo[num] + ".xml");

				conversor.escribirArchivo(direccionArchivoSalida, xml); // Escribe el archivo XML
				System.out.println("ESCRIBIR ARCHIVO" + nombreArchivo[num] + ".xml");
			}
			
			
		}
	}
}
