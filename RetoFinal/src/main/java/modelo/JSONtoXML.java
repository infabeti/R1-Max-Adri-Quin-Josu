package modelo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.nio.charset.Charset;

import org.json.JSONException; //http://bit.ly/12O4D2w
import org.json.JSONObject;

/*
 * Convierte archivos de tipo JSON a XML en cuatro pasos:
 * 1) Lee el JSON y lo pasa a un String
 * 2) Renombrar etiquetas duplicadas en el string (Nota: está particularizado para el archivo espacios-naturales.json. Mejora --> Generalizar solución)
 * 3) Utiliza un objeto json de la libreria org.json para convertirlo a formato XML y guardarlo en un string
 * 4) Escribe el archivo de salida 
 */

public class JSONtoXML {
    private static String direccionArchivoEntrada = "./archivos/espacios-naturales.json";
    private static String direccionArchivoSalida = "./archivos/espacios-naturales.xml";

    public static void main(String[] args) throws FileNotFoundException, IOException, JSONException {
	// Lee el archivo JSON
	long tiempoInicial = System.currentTimeMillis();
	String json = leerArchivo(direccionArchivoEntrada); // Lee el archivo
	long tiempoFinal = System.currentTimeMillis();
	long duracion = tiempoFinal - tiempoInicial;
	System.out.println("Duración de la lectura del archivo: " + duracion); // Estadística

	// Eliminar etiquetas duplicadas
	tiempoInicial = System.currentTimeMillis();
	String jsonCorregido = eliminarEtiquetasDuplicadas(json);
	tiempoFinal = System.currentTimeMillis();
	duracion = tiempoFinal - tiempoInicial;
	System.out.println("Duración de renombrar etiquetas duplicadas: " + duracion); // Estadística

	// Convierte JSON a XML
	tiempoInicial = System.currentTimeMillis();
	String xml = convertir(jsonCorregido, "root");// Establezco el nombre del tag raiz del XML
	tiempoFinal = System.currentTimeMillis();
	duracion = tiempoFinal - tiempoInicial;
	System.out.println("Duración de la conversión: " + duracion); // Estadística
	
	// Escribe el archivo XML
	tiempoInicial = System.currentTimeMillis();
	escribirArchivo(direccionArchivoSalida, xml);
	tiempoFinal = System.currentTimeMillis();
	duracion = tiempoFinal - tiempoInicial;
	System.out.println("Duración de escritura de archivo: " + duracion); // Estadística
    }

    public static String leerArchivo(String rutaArchivo) throws FileNotFoundException, IOException {
	StringBuilder acumuladoCadena = new StringBuilder();
	InputStream in = new FileInputStream(direccionArchivoEntrada);
	Charset encoding = Charset.defaultCharset();

	Reader lector = new InputStreamReader(in, encoding);

	int r = 0;
	while ((r = lector.read()) != -1) {// OJO! uso read() mejor que readLine()
					   // porque puedo procesar archivos más largos con read()
	    char ch = (char) r;
	    acumuladoCadena.append(ch);
	}

	in.close();
	lector.close();

	return acumuladoCadena.toString();
    }

    public static String eliminarEtiquetasDuplicadas(String archivo) {
	String archivoCorregido = "";
	int contador1 = 0;
	int contador2 = 0;
	int contador3 = 0;
	String[] campos;
	String[] lineas;
	campos = archivo.split("}");
	for (int i = 0; i < campos.length; i++) {
	    lineas = campos[i].split(" : ");
	    for (int j = 0; j < lineas.length; j++) {
		
		if (lineas[j].toString().contains("\"turismDescription\"") && contador1 > 0) {
		    archivoCorregido += "\"turismDescription" + contador1 + "\"" + " : ";
		    contador1++;
		} else if (lineas[j].toString().contains("\"address\"") && contador2 > 0) {
		    archivoCorregido += lineas[j].substring(0, lineas[j].length() - 9) + "\"address" + contador2 + "\"" + " : ";
		    contador2++;
		} else if (lineas[j].toString().contains("\"phone\"") && contador3 > 0) {
		    archivoCorregido += lineas[j].substring(0, lineas[j].length() - 7) + "\"phone" + contador3 + "\"" + " : ";
		    contador3++;
		} else if (lineas[j].toString().contains("\"turismDescription\"") && contador1 == 0) {
		    archivoCorregido += lineas[j] + " : " + lineas[j + 1].substring(0, lineas[j + 1].length() - 19);
		    contador1++;
		} else if (lineas[j].toString().contains("\"address\"") && contador2 == 0) {
		    archivoCorregido += lineas[j] + " : ";
		    contador2++;
		} else if (lineas[j].toString().contains("\"phone\"") && contador3 == 0) {
		    archivoCorregido += lineas[j] + " : ";
		    contador3++;
		} else {
		    archivoCorregido += lineas[j];
		    if (lineas[j].charAt(lineas[j].length()-1) != (char) 10)
			archivoCorregido += " : ";
		}
	    }
	    lineas = null;
	    contador1 = 0;
	    contador2 = 0;
	    contador3 = 0;
	    archivoCorregido += "}";
	}

	return archivoCorregido;
    }

    public static String convertir(String json, String raiz) throws JSONException {
	JSONObject objetoJson = new JSONObject(json);
	String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?><" + raiz + ">"
		+ org.json.XML.toString(objetoJson) + "</" + raiz + ">";
	
	return xml;
    }

    public static void escribirArchivo(String rutaArchivo, String salida) throws FileNotFoundException, IOException {
	FileWriter escritor = new FileWriter(rutaArchivo);
	try (BufferedWriter out = new BufferedWriter(escritor)) {
	    out.write(salida);
	}
	escritor.close();
    }
}
