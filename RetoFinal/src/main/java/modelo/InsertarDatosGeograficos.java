package modelo;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.persistence.Column;

import org.hibernate.Session;

/*
 * Introduce los datos de los archivos .XML en la BBDD en tres pasos:
 * 1) Lee el XML y lo pasa a un String
 * 2) Extrae los datos necesarios y crea los objetos correspondientes
 * 3) Los ingresa en la BBDD
 */

public class InsertarDatosGeograficos {
	
//PTE: AÑADIR ESPACIOS-NATURALES A LA CLASE
	
//    static String[] archivos = { "municipios", "estaciones", "espacios-naturales" };
//    static String[] contenedores = { "municipio", "estación", "espacio-natural" };

	static String[] archivos = { "municipios", "estaciones" };
	static String[] contenedores = { "municipio", "estación" };

	public static void main(String[] args) {
		Object[] objetos = null;

		for (int num = 0; num < archivos.length; num++) {
			String direccionArchivoEntrada = "./archivos//" + archivos[num] + ".xml";

			String xml = leerArchivo(direccionArchivoEntrada); // Lee el archivo

			switch (archivos[num]) {
			case "municipios":
				objetos = (Object[]) extraerDatosMunicipios(xml); // Extrae los datos y crea los objetos
				break;
			case "estaciones":
				objetos = (Object[]) extraerDatosEstaciones(xml); // Extrae los datos y crea los objetos
				break;

			default:
				System.out.println("no coincide");
			}

			ingresarObjetos(objetos); // Guarda los objetos en la BBDD
		}
	}

	public static String leerArchivo(String rutaArchivo) {

		StringBuilder acumuladoCadena = new StringBuilder();
		try {
			InputStream in;
			in = new FileInputStream(rutaArchivo);
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return acumuladoCadena.toString();
	}

	public static Municipios[] extraerDatosMunicipios(String archivo) {

		String[] municipios;
		String[] lineas;
		Municipios[] objetos;
		String municipio = "";
		String[] nombrePuebloXML = null;
		String[] nombrePuebloLIMPIO = null;
		int[] codProvinciaXML = null;
		int[] codProvinciaLIMPIO = null;

		municipios = archivo.split("</municipio>");

		nombrePuebloXML = new String[municipios.length - 1]; // declaro el tamaño
		codProvinciaXML = new int[municipios.length - 1]; // declaro el tamaño
		
		for (int i = 0; i < municipios.length; i++) {
			lineas = municipios[i].split("/");
			for (int j = 0; j < lineas.length; j++) {
				if (lineas[j].contains("<municipality>")) { // Recoge la linea con el municipio
					for (int k = 0; k < lineas[j].length(); k++) {
						if (lineas[j].charAt(k) == 'y') { // Recorta la linea desde el carácter 'y' hasta el final
							municipio = lineas[j].substring(k, lineas[j].length() - 1);
							if (municipio.contains(">"))
								nombrePuebloXML[i] = municipio.substring(2); // Guarda el nombre en el array

						}
					}
				} else if (lineas[j].contains("<territorycode>")) { // Guarda el código de la provincia correspondiente
					codProvinciaXML[i] = Integer.parseInt(lineas[j].substring(23, lineas[j].length() - 1));
				}
			}
		}

		// Borrar entradas duplicadas
		int contador = 0;
		for (int i = 0; i < nombrePuebloXML.length; i++) {
			for (int j = i + 1; j < nombrePuebloXML.length; j++) {
				if (nombrePuebloXML[i].contentEquals("San Sebastián")) { // Este nombre me da problemas y está repetido, lo borro									
					nombrePuebloXML[i] = "San Sebastian";
				}
				if (nombrePuebloXML[i].contentEquals(nombrePuebloXML[j])) {
					nombrePuebloXML[j] = "Entrada duplicada " + contador;
					contador++;
				}
			}
		}

		nombrePuebloLIMPIO = new String[nombrePuebloXML.length - contador]; // declaro el tamaño
		codProvinciaLIMPIO = new int[codProvinciaXML.length - contador]; // declaro el tamaño
		int cuentaPueblos = 0;
		for (int i = 0; i < nombrePuebloXML.length; i++) {
			if (!nombrePuebloXML[i].contains("Entrada duplicada ")
					&& !nombrePuebloXML[i].contentEquals("San Sebastián2")) {
				nombrePuebloLIMPIO[cuentaPueblos] = nombrePuebloXML[i];
				codProvinciaLIMPIO[cuentaPueblos] = codProvinciaXML[i];
				cuentaPueblos++;
			}
		}
		// Crea los objetos con la información del municipio (nombre y código de
		// provincia)
		objetos = new Municipios[nombrePuebloLIMPIO.length]; // declaro el tamaño
		for (int k = 0; k < nombrePuebloLIMPIO.length; k++) { // creo los objetos
			objetos[k] = new Municipios(nombrePuebloLIMPIO[k], codProvinciaLIMPIO[k]);
		}

		return objetos;
	}

	public static EstacionesMetereologicas[] extraerDatosEstaciones(String archivo) {
		EstacionesMetereologicas[] objetos = null;

		String[] estaciones = archivo.split("</estación>");
		String[] nombreAux = null;
		String[] nombres = new String[estaciones.length - 1];// declaro el tamaño
		String[] municipiosAux = null;
		String[] pueblos = null;
		String[] municipios = new String[estaciones.length - 1];// declaro el tamaño
		String[] direccionAux = null;
		String[] dir = null;
		String[] direcciones = new String[estaciones.length - 1];// declaro el tamaño
		String[] latitudAux = null;
		String[] lat = null;
		String[] latitudes = new String[estaciones.length - 1];// declaro el tamaño
		String[] longitudAux = null;
		String[] longit = null;
		String[] longitudes = new String[estaciones.length - 1];// declaro el tamaño
		String nombre = "";
		String municipio = "";
		String direccion = "";
		String latitudString = "";
		String latitud = "";
		String longitud = "";
		String longitudString = "";
		String nombreConEspacios = "";
		
		for (int i = 0; i < estaciones.length - 1; i++) { // Recorre cada estación
			nombreAux = estaciones[i].split("<Name>");

			// Nombre
			for (int j = 0; j < nombreAux.length; j++) {
				if (nombreAux[j].contains("</Name>")) {
					nombreConEspacios = nombreAux[j].substring(0, nombreAux[j].length() - 7);
				}
			}
			
			nombre = "";
			for (int k = 0; k < nombreConEspacios.length(); k++) { //Sustituyo los espacios por guiones bajos

				if(nombreConEspacios.charAt(k) == ' ')	{
					nombre += "_";
				}else {
					nombre += Character.toString(nombreConEspacios.charAt(k));
				}
				if(nombreConEspacios.charAt(k) == 'Ñ')	{
					nombre = nombre.substring(0,nombre.length()-1);
					nombre += "N";
				}
				if(nombreConEspacios.charAt(k) == '(')	{
					nombre = nombre.substring(0,nombre.length()-1);
					
				}
				if(nombreConEspacios.charAt(k) == ')')	{
					nombre = nombre.substring(0,nombre.length()-1);
				}
				if(nombreConEspacios.charAt(k) == 'ª')	{
					nombre = nombre.substring(0,nombre.length()-1);
				}
				if(nombreConEspacios.charAt(k) == '.')	{
					nombre = nombre.substring(0,nombre.length()-1);
				}
			}
			
			nombres[i] = nombre;

			// Municipio
			municipiosAux = estaciones[i].split("<Town>");
			for (int k = 0; k < municipiosAux.length; k++) {
				if (municipiosAux[k].contains("</Town>")) {
					pueblos = municipiosAux[k].split("<CoordenatesXETRS89>");
					municipio = pueblos[0].substring(0, pueblos[0].length() - 7);
					// Corrijo los nombres de las poblaciones para que coincidan las claves ajenas con los de la tabla "municipios"
					if (municipio.contains("Donostia"))
						municipio = "San Sebastian";
					if (municipio.contains("Agurain"))
						municipio = "Salvatierra";
					if (municipio.contentEquals("Valdegovía/Gaubea"))
						municipio = "Villanueva de Valdegovía";
					if (municipio.contentEquals("Laudio/Llodio"))
						municipio = "Laudio";
					if (municipio.contentEquals("Arrasate/Mondragón"))
						municipio = "Arrasate";
				}
			}
			municipios[i] = municipio;

			// Dirección
			direccionAux = estaciones[i].split("<Address>");
			for (int m = 0; m < direccionAux.length; m++) {
				if (direccionAux[m].contains("</Address>")) {
					dir = direccionAux[m].split("<Town>");
					direccion = dir[0].substring(0, dir[0].length() - 10);
				}
			}
			direcciones[i] = direccion;

			// Latitud
			latitud = "";
			latitudAux = estaciones[i].split("<Latitude>");
			for (int o = 0; o < latitudAux.length; o++) {
				if (latitudAux[o].contains("</Latitude>")) {
					lat = latitudAux[o].split("<Longitude>");
					latitudString = lat[0].substring(0, lat[0].length() - 11);

					for (int q = 0; q < latitudString.length(); q++) { // Cambio la ',' por '.'
						if (latitudString.charAt(q) == ',') {
							latitud += '.';
						} else {
							latitud += latitudString.charAt(q);
						}
					}
				}
			}
			latitudes[i] = latitud;

			// Longitud
			longitud = "";
			longitudAux = estaciones[i].split("<Longitude>");
			for (int r = 0; r < longitudAux.length; r++) {
				if (longitudAux[r].contains("</Longitude>")) {
					longit = longitudAux[r].split("<Province>");
					longitudString = longit[0].substring(0, longit[0].length() - 12);
					for (int t = 0; t < longitudString.length(); t++) { // Cambio la ',' por '.'
						if (longitudString.charAt(t) == ',') {
							longitud += '.';
						} else {
							longitud += longitudString.charAt(t);
						}
					}
				}
			}
			longitudes[i] = longitud;
		} // Fin bucle estaciones		

		// Crea los objetos con la información de las estaciones (nombre, municipio,
		// direccion, latitud y longitud)
		objetos = new EstacionesMetereologicas[estaciones.length - 1]; // declaro el tamaño

		for (int i = 0; i < estaciones.length - 1; i++) {
			objetos[i] = new EstacionesMetereologicas();
			objetos[i].setNombre(nombres[i]);
			objetos[i].setMunicipios(municipios[i]);
			objetos[i].setDireccion(direcciones[i]);
			objetos[i].setLatitud(Double.valueOf(latitudes[i]));
			objetos[i].setLongitud(Double.valueOf(longitudes[i]));
		}

		return objetos;
	}

	public static void ingresarObjetos(Object[] objetos) {
		for (int i = 0; i < objetos.length; i++) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.save(objetos[i]);

			session.getTransaction().commit();
			HibernateUtil.shutdown();

		}
	}
	
}