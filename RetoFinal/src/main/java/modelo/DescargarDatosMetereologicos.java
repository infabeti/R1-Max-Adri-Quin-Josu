package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class DescargarDatosMetereologicos {

	public Boolean DescargarDatosMetereologicos() throws FileNotFoundException, IOException {
		
		JSONtoXML conversor = new JSONtoXML();
		DescargarJSON descarga = new DescargarJSON();
		File archivo;
		Boolean existe = false, igual = true, primeraVez = true;
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
	
		archivo = new File(ruta + archivos[0]);
		if(archivo.exists()) {
			ruta = "./archivos/calidadAireTemporal/";
			existe = true;
			primeraVez = false;
		}
		File directorio = new File("./archivos/calidadAireTemporal");
		if(!directorio.exists()) {
			directorio.mkdir();
		}
		

		descarga.descargarJSON(direcciones, archivos, ruta);

		// Convierte los archivos .JSON a .XML
		String nombreArchivos = "";
		String[] nombreArchivo;
		for (int i = 0; i < archivos.length; i++) {
			nombreArchivos += archivos[i].substring(0, archivos[i].length() - 5) + ",";
		}
		nombreArchivo = nombreArchivos.split(",");

		//Comprueba si son distintos
		
		if(existe) {
			for (int i = 0; i < archivos.length && igual; i++) {
				jsonOrigen = conversor.leerArchivo(ruta + archivos[i]); // Lee el archivo
				System.out.println("COMPARAR ARCHIVO " + nombreArchivo[i] + ".json");
			
				String jsoncomparar = conversor.leerArchivo("./archivos/calidadAire/" + archivos[i]);
				CompararJSON comp = new CompararJSON();
				if(!comp.compararCifrado(comp.cifrar(jsoncomparar), comp.cifrar(jsonOrigen))) {
					igual = false;
					File carpeta = new File("./archivos/calidadAire");
					File[] listaFicheros = carpeta.listFiles();
					for (int j = 0; j < listaFicheros.length; j++) {
						listaFicheros[j].delete();
					}
					if(carpeta.delete()) {
						File carpeta2 = new File("./archivos/calidadAireTemporal");
						carpeta2.renameTo(carpeta);
						File carpeta3 = new File("./archivos/calidadAireTemporal");
						carpeta3.mkdir();
					}
				}
			}
			ruta = "./archivos/calidadAire/";
		}
		
		if(!igual || primeraVez) {
			//Recorre todos los archivos
			for (int num = 0; num < archivos.length; num++) {
				String direccionArchivoEntrada2 = ruta + nombreArchivo[num] + ".json";
				String direccionArchivoSalida = ruta + nombreArchivo[num] + ".xml";

				jsonOrigen = conversor.leerArchivo(direccionArchivoEntrada2); // Lee el archivo
				System.out.println("LEER ARCHIVO " + nombreArchivo[num] + ".json");
	
				if (jsonOrigen.length()>0) {
					String jsonPreparado = conversor.prepararArchivo(jsonOrigen, nombreArchivo[num]); // Prepara el archivo: quita cabecera
					System.out.println("PREPARAR ARCHIVO " + nombreArchivo[num] + ".json");
	
					String xml = conversor.convertir(jsonPreparado, nombreArchivo[num]); // Establezco el nombre del tag raiz																		// del XML
					System.out.println("CONVERTIR ARCHIVO " + nombreArchivo[num] + ".json --> " + nombreArchivo[num] + ".xml");
	
					conversor.escribirArchivo(direccionArchivoSalida, xml); // Escribe el archivo XML
					System.out.println("ESCRIBIR ARCHIVO " + nombreArchivo[num] + ".xml");
					System.out.println();
				}
			}
		}else {
			File carpeta = new File("./archivos/calidadAireTemporal");
			File[] listaFicheros = carpeta.listFiles();
			for (int i = 0; i < listaFicheros.length; i++) {
				listaFicheros[i].delete();
			}
		}
		
		return igual;
	}
}
