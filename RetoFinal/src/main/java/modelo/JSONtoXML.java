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
 * Convierte archivos de tipo JSON a XML en cinco pasos:
 * 1) Lee el JSON y lo pasa a un String
 * 2) Prepara el archivo, quitando cabecera, añadiendo contenedor y arreglando el final
 * 3) Renombrar etiquetas duplicadas en el string y borrar las vacias
 * 4) Utiliza un objeto json de la libreria org.json para convertirlo a formato XML y guardarlo en un string
 * 5) Escribe el archivo de salida
 */

public class JSONtoXML {
    static String[] archivos = { "index", "estaciones", "espacios-naturales", "municipios" };
    static String[] contenedores = { "index", "estación", "espacio-natural", "municipio" };

    public static void main(String[] args) throws FileNotFoundException, IOException, JSONException {
	for (int num = 0; num < 4; num++) {
	    String direccionArchivoEntrada = "./archivos//" + archivos[num] + ".json";
	    String direccionArchivoSalida = "./archivos//" + archivos[num] + ".xml";
	    // Lee el archivo JSON
	    long tiempoInicial = System.currentTimeMillis();
	    String jsonOrigen = leerArchivo(direccionArchivoEntrada); // Lee el archivo
	    long tiempoFinal = System.currentTimeMillis();
	    long duracion = tiempoFinal - tiempoInicial;
	    System.out.println("Duración de la lectura del archivo: " + duracion + " mlseg"); // Estadística

	    tiempoInicial = System.currentTimeMillis();
	    String jsonPreparado = prepararArchivo(jsonOrigen, contenedores[num]);
	    tiempoFinal = System.currentTimeMillis();
	    duracion = tiempoFinal - tiempoInicial;
	    System.out.println(
		    "Duración de preparar el archivo quitando cabecera y borrando final: " + duracion + " mlseg"); // Estadística

	    // Eliminar etiquetas duplicadas y borrar las vacias
	    tiempoInicial = System.currentTimeMillis();
	    String jsonCorregido = renombrarEtiquetasDuplicadas(jsonPreparado);
	    tiempoFinal = System.currentTimeMillis();
	    duracion = tiempoFinal - tiempoInicial;
	    System.out.println("Duración de renombrar etiquetas duplicadas y borrar vacias: " + duracion + " mlseg"); // Estadística

	    // Convierte JSON a XML
	    tiempoInicial = System.currentTimeMillis();
	    String xml = convertir(jsonCorregido, "root");// Establezco el nombre del tag raiz del XML
	    tiempoFinal = System.currentTimeMillis();
	    duracion = tiempoFinal - tiempoInicial;
	    System.out.println("Duración de la conversión: " + duracion + " mlseg"); // Estadística

	    // Escribe el archivo XML
	    tiempoInicial = System.currentTimeMillis();
	    escribirArchivo(direccionArchivoSalida, xml);
	    tiempoFinal = System.currentTimeMillis();
	    duracion = tiempoFinal - tiempoInicial;
	    System.out.println("Duración de escritura de archivo: " + duracion + " mlseg"); // Estadística
	}
    }

    public static String leerArchivo(String rutaArchivo) {
	StringBuilder acumuladoCadena = new StringBuilder();
	try {
		InputStream in;
		in = new FileInputStream(rutaArchivo);
//		Charset encoding = Charset.defaultCharset();
		Charset encoding = Charset.forName("UTF-8"); // Codifico el archivo con UTF-8

		Reader lector = new InputStreamReader(in, encoding);
		
		int r = 0;
		while ((r = lector.read()) != -1) {// OJO! uso read() mejor que readLine()
			// porque puedo procesar archivos más largos con read()
			char ch = (char) r;
			acumuladoCadena.append(ch);
		}

		in.close();
		lector.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}catch (IOException e) {
		System.out.println(e.getMessage());
	}
	return acumuladoCadena.toString();
    }

    public static String prepararArchivo(String archivo, String contenedor) {
	String archivoSinCabecera = "";
	String archivoSinFinal = "";
	String archivoFinal = "";
	boolean encontrado = false;
	String[] campos;

	for (int i = 0; i < archivo.length(); i++) {
	    if (archivo.charAt(i) == '{' && !encontrado) {
		archivoSinCabecera = archivo.substring(i);
		encontrado = true;
	    }
	}
	campos = archivoSinCabecera.split("}");
	archivoSinFinal += campos[0];
	for (int i = 1; i < campos.length - 1; i++) {
	    archivoSinFinal += "\n}" + campos[i];
	}

	if (archivo.charAt(0) == '{')
	    archivoFinal = archivoSinFinal + "}\n]\n}";
	else
	    archivoFinal = "{\n" + "\"" + contenedor + "\"" + ": [ \n" + archivoSinFinal + "\n}\n]\n}";

	return archivoFinal;
    }

    public static String renombrarEtiquetasDuplicadas(String archivo) {
	String archivoCorregido = "";
	String archivoFinal = "";
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
		    archivoCorregido += lineas[j].substring(0, lineas[j].length() - 9) + "\"address" + contador2 + "\""
			    + " : ";
		    contador2++;
		} else if (lineas[j].toString().contains("\"phone\"") && contador3 > 0) {
		    archivoCorregido += lineas[j].substring(0, lineas[j].length() - 7) + "\"phone" + contador3 + "\""
			    + " : ";
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
		    if (lineas[j].charAt(lineas[j].length() - 1) != (char) 10)
			archivoCorregido += " : ";
		}
	    }
	    archivoCorregido = archivoCorregido.substring(0, archivoCorregido.length() - 2);

	    lineas = null;
	    contador1 = 0;
	    contador2 = 0;
	    contador3 = 0;
	    archivoCorregido += "}";
	}
	archivoCorregido = archivoCorregido.substring(0, archivoCorregido.length() - 1);
	archivoCorregido += "]\n}";
	// Eliminar etiquetas vacias
	lineas = archivoCorregido.split(",");
	for (int i = 0; i < lineas.length; i++) {
	    if (!lineas[i].toString().contains("\"\"")) {
		archivoFinal += lineas[i] + ",";
	    }
	}
	archivoFinal = archivoFinal.substring(0, archivoFinal.length() - 1); // Le quita la última ","

	return archivoFinal;
    }

    public static String convertir(String json, String raiz) throws JSONException {
	JSONObject objetoJson = new JSONObject(json);
	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><" + raiz + ">" + org.json.XML.toString(objetoJson)
		+ "</" + raiz + ">";

	return xml;
    }

    public static void escribirArchivo(String rutaArchivo, String salida){

	try {
		FileWriter escritor = new FileWriter(rutaArchivo);
		escritor = new FileWriter(rutaArchivo);
		try (BufferedWriter out = new BufferedWriter(escritor)) {
			out.write(salida);
		}
		escritor.close();
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
    }
}
