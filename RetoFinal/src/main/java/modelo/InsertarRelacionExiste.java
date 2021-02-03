package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.hibernate.Session;

/*
 * Introduce los datos de los archivos .XML en la BBDD en tres pasos:
 * 1) Lee el XML y lo pasa a un String
 * 2) Extrae los datos necesarios y crea los objetos correspondientes
 * 3) Los ingresa en la BBDD
 */

public class InsertarRelacionExiste {

	public void InsertarRelacionExiste() {

		String xml = leerArchivo("./archivos/espacios-naturales.xml"); // Lee el archivo

		Object[] objetos = (Object[]) extraerDatosRelacionExiste(xml); // Extrae los datos y crea los objetos

		ingresarObjetos(objetos); // Guarda los objetos en la BBDD
	}

	public String leerArchivo(String rutaArchivo) {

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

	public RelacionExiste[] extraerDatosRelacionExiste(String archivo) {
	    InsertarDatosGeograficos insertarDatosGeograficos = new InsertarDatosGeograficos();
		Object[] nombresMunicipios = null;
		RelacionExiste relacion = null;
		ArrayList<RelacionExiste> relaciones = new ArrayList<RelacionExiste>(); // Create an ArrayList object

		String xml = leerArchivo("./archivos/municipios.xml");
		nombresMunicipios = insertarDatosGeograficos.extraerDatosMunicipios(xml);

		RelacionExiste[] objetos = null;

		String[] espNat = archivo.split("</espacio-natural>");

		String[] nombresEspNat = new String[espNat.length - 1];// declaro el tamaño

		String[] munConEspNat = new String[espNat.length - 1];// declaro el tamaño

		for (int i = 0; i < espNat.length - 1; i++) { // Recorre cada espacio

			// Nombre Espacio Natural
			nombresEspNat[i] = extraerDato(espNat[i], "documentName");

			// Nombre Municipio
			munConEspNat[i] = extraerDato(espNat[i], "municipality");

		} // Fin bucle espacios-naturales

		// Corrijo nombres
		for (int j = 0; j < munConEspNat.length; j++) {
			if (munConEspNat[j].contains("Donostia")) {
				munConEspNat[j] = "San Sebastian";
			}
		}
		for (int j = 0; j < munConEspNat.length; j++) {
			for (int k = 0; k < nombresMunicipios.length; k++) {
				if (munConEspNat[j].contains(((Municipios) nombresMunicipios[k]).getNombre())) {
					relacion = new RelacionExiste();
					relacion.setNomEspNat(nombresEspNat[j]);
					relacion.setNomMunicipio(((Municipios) nombresMunicipios[k]).getNombre());
					relaciones.add(relacion);
				}
			}
		}

		// Paso los objetos con la información de la tabla existe (nomEspNat,
		// nomMunicipio) del arraylist al array de objetos

		objetos = new RelacionExiste[relaciones.size() - 1]; // declaro el tamaño (Quito el 1º porque el municipio de
																// Ayala no está en municipios)

		for (int i = 0; i < objetos.length; i++) { // paso los objetos
			objetos[i] = relaciones.get(i + 1);
		}

		return objetos;
	}

	public void ingresarObjetos(Object[] objetos) {
		for (int i = 0; i < objetos.length; i++) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.save(objetos[i]);

			session.getTransaction().commit();
			HibernateUtil.shutdown();

		}
	}

	public String extraerDato(String archivo, String etiqueta) {
		String dato = null;
		String etiquetaIzqda = "<" + etiqueta + ">";
		String etiquetaDrcha = "</" + etiqueta + ">";
		String[] corte1 = archivo.split(etiquetaIzqda);
		String[] corte2;

		for (int i = 0; i < corte1.length; i++) {
			if (corte1[i].contains("</" + etiqueta + ">")) {
				corte2 = corte1[i].split(etiquetaDrcha);
				dato = corte2[0];
			}
		}

		if (dato != null)
			return dato;
		else
			return "";
	}
}