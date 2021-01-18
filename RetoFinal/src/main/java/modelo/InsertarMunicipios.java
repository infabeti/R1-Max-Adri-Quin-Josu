package modelo;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.hibernate.Session;

/*
 * Introduce los datos de los archivos .XML en la BBDD en tres pasos:
 * 1) Lee el XML y lo pasa a un String
 * 2) Extrae los datos necesarios y crea los objetos correspondientes
 * 3) Los ingresa en la BBDD
 */

public class InsertarMunicipios {

//    static String[] archivos = { "estaciones", "espacios-naturales", "municipios" };
//    static String[] contenedores = { "estación", "espacio-natural", "municipio" };
	static String[] archivos = { "municipios" };
	static String[] contenedores = { "municipio" };

	public static void main(String[] args) {
		for (int num = 0; num < archivos.length; num++) {
			String direccionArchivoEntrada = "./archivos//" + archivos[num] + ".xml";

			String xml = leerArchivo(direccionArchivoEntrada); // Lee el archivo
			Municipios[] objetos = extraerDatos(xml); // Extrae los datos y crea los objetos
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

	public static Municipios[] extraerDatos(String archivo) {
		String[] municipios;
		String[] lineas;
		Municipios[] objetos;
		String nombre;
		int idProvincia;
		String municipio = "";
		String[] nombrePueblo = null;
		int[] codProvincia = null;

		municipios = archivo.split("</municipio>");

		nombrePueblo = new String[municipios.length - 1]; // declaro el tamaño
		codProvincia = new int[municipios.length - 1]; // declaro el tamaño
		objetos = new Municipios[municipios.length - 1]; // declaro el tamaño
		for (int i = 0; i < municipios.length; i++) {
			lineas = municipios[i].split("/");
			for (int j = 0; j < lineas.length; j++) {
				if (lineas[j].contains("<municipality>")) { // Recoge la linea con el municipio
					for (int k = 0; k < lineas[j].length(); k++) {
						if (lineas[j].charAt(k) == 'y') { // Recorta la linea desde el carácter 'y' hasta el final
							municipio = lineas[j].substring(k, lineas[j].length() - 1);
							if (municipio.contains(">"))
								nombrePueblo[i] = municipio.substring(2); // Guarda el nombre en el array
						}
					}
				} else if (lineas[j].contains("<territorycode>")) { // Guarda el código de la provincia correspondiente
					codProvincia[i] = Integer.parseInt(lineas[j].substring(23, lineas[j].length() - 1));
				}
			}
		}

		// Renombrar nombres duplicados
		for (int i = 0; i < nombrePueblo.length; i++) {
			for (int j = i + 1; j < nombrePueblo.length; j++) {
				if (nombrePueblo[i].contentEquals("San Sebastián")) { //Este nombre me da problemas, no lo detecta en el recorrido y además está repetido, por eso está puesta la condición independiente
					nombrePueblo[i] = "San Sebastián-bis";
				}
				if (nombrePueblo[i].contentEquals(nombrePueblo[j])) {
					nombrePueblo[j] = nombrePueblo[j] + "-bis";
				}
			}
		}

		// Crea los objetos con la información del municipio (nombre y código de
		// provincia)
		for (int k = 0; k < nombrePueblo.length; k++) {
			objetos[k] = new Municipios(nombrePueblo[k], codProvincia[k]);
		}

		//Comprobación de nombres
//		for (int k = 0; k < nombrePueblo.length; k++) {
//			System.out.println(nombrePueblo[k]);
//		}
//		System.out.println("Nº de municipios --> " + nombrePueblo.length);
		
		return objetos;
	}

	public static void ingresarObjetos(Municipios[] objetos) {
		for (int i = 0; i < objetos.length; i++) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.save(objetos[i]);

			session.getTransaction().commit();
			HibernateUtil.shutdown();
//			session.close();
		}
	}
}