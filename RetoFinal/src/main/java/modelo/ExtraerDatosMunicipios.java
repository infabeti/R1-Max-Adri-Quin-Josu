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

public class ExtraerDatosMunicipios {

	static String[] archivos = { "descripcionMunicipios" };
	static String[] contenedores = { "descripcionMunicipios" };

	public static void main(String[] args) {
		Object[] objetos = null;

		for (int num = 0; num < archivos.length; num++) {
			String direccionArchivoEntrada = "./archivos//" + archivos[num] + ".xml";

			String xml = leerArchivo(direccionArchivoEntrada); // Lee el archivo

			objetos = extraerDatosMunicipios(xml); // Extrae los datos y crea los objetos
			
			actualizarObjetos(objetos); // Guarda los objetos en la BBDD
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
		String[] pueblos;
		String[] lineasNombre;
		String[] lineasDescripcion;
		String[] nombres;
		String[] descripcion;
		Municipios[] objetos;
		String municipio = "";
		String[] descripcionPuebloXML = null;
		String[] descripcionPuebloLIMPIO = null;
		int[] codProvinciaXML = null;
		int[] codProvinciaLIMPIO = null;

		municipios = archivo.split("</descripcionMunicipios>");

		nombres = new String[municipios.length - 1]; // declaro el tamaño
		descripcionPuebloXML = new String[municipios.length - 1]; // declaro el tamaño
		codProvinciaXML = new int[municipios.length - 1]; // declaro el tamaño
		int n =0;
		//Nombre
		for (int i = 0; i < municipios.length; i++) {
			lineasNombre = municipios[i].split("<documentName>");
			for (int j = 0; j < lineasNombre.length; j++) {
				if (lineasNombre[j].contains("</documentName>")) { // Recoge la linea con el nombre
					pueblos = lineasNombre[j].split("</documentName>");
					if (pueblos[0].contentEquals("Irura")) { //Este pueblo me da problema al no tener descripción, lo borro
						n = n-1; //Desfaso los contadores en uno para que los arrays estén emparejados
					}else {
						
						nombres[n] = pueblos[0]; // Guarda el nombre en el array
					}
				}
			}
			n++;
		}
		//Descripcion
		for (int i = 0; i < municipios.length; i++) {
			lineasDescripcion = municipios[i].split("<turismDescription>");
			for (int j = 0; j < lineasDescripcion.length; j++) {
				if (lineasDescripcion[j].contains("</turismDescription>")) { // Recoge la linea con la descripcion
					descripcion = lineasDescripcion[j].split("</turismDescription>");
					descripcionPuebloXML[i] = descripcion[0]; // Guarda el nombre en el array
				}
			}
		}

		// Borrar entradas duplicadas
		int contador = 0;
		for (int i = 0; i < descripcionPuebloXML.length; i++) {
			for (int j = i + 1; j < descripcionPuebloXML.length; j++) {
				if (descripcionPuebloXML[i].contentEquals("San Sebastián")) { // Este nombre me da problemas y está repetido,
																			// lo borro
					descripcionPuebloXML[i] = "San Sebastian";
				}
				if (descripcionPuebloXML[i].contentEquals(descripcionPuebloXML[j])) {
					descripcionPuebloXML[j] = "Entrada duplicada " + contador;
					contador++;
				}
			}
		}

		descripcionPuebloLIMPIO = new String[descripcionPuebloXML.length - contador]; // declaro el tamaño
		codProvinciaLIMPIO = new int[codProvinciaXML.length - contador]; // declaro el tamaño
		int cuentaPueblos = 0;
		for (int i = 0; i < descripcionPuebloXML.length; i++) {
			if (!descripcionPuebloXML[i].contains("Entrada duplicada ")
					&& !descripcionPuebloXML[i].contentEquals("San Sebastián2")) {
				descripcionPuebloLIMPIO[cuentaPueblos] = descripcionPuebloXML[i];
				cuentaPueblos++;
			}
		}
		

		// Crea los objetos con la información del municipio (nombre y código de provincia)
		objetos = new Municipios[descripcionPuebloLIMPIO.length]; // declaro el tamaño
		for (int k = 0; k < descripcionPuebloLIMPIO.length; k++) { // creo los objetos
			objetos[k] = new Municipios();
			objetos[k].setNombre(nombres[k]);
			objetos[k].setDescripcion(descripcionPuebloLIMPIO[k]);

		}

		return objetos;
	}
	
	public static void actualizarObjetos(Object[] objetos) {
		for (int i = 0; i < objetos.length; i++) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.saveOrUpdate(objetos[i]);

			session.getTransaction().commit();
			HibernateUtil.shutdown();

		}
	}
}
