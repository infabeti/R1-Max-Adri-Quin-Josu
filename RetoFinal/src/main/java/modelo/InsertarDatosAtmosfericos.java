package modelo;

/*
 * Introduce los datos de los archivos .XML en la BBDD en tres pasos:
 * 1) Lee el XML y lo pasa a un String
 * 2) Extrae los datos necesarios y crea los objetos correspondientes
 * 3) Los ingresa en la BBDD
 */

public class InsertarDatosAtmosfericos {

	static String[] estacionesMeteo = new String[59];

	public static void main(String[] args) {
		InsertarDatosGeograficos obj = new InsertarDatosGeograficos(); // Instancia para llamar a los métodos de la
																		// clase InsertarDatosGeograficos
		CalidadAire[] objetos = new CalidadAire[estacionesMeteo.length];

		// Obtengo el array con los nombres de las estaciones
		String[] nombreAux = null;
		String nombreConEspacios = "";
		String nombre;
		String archivo = obj.leerArchivo("./archivos/estaciones.xml");
		int intento = 0;

		String[] estaciones = archivo.split("</estación>");
		for (int i = 0; i < estaciones.length - 1; i++) { // Recorre cada estación
			nombre = "";
			nombreAux = estaciones[i].split("<Name>");

			// Nombre
			for (int j = 0; j < nombreAux.length; j++) {
				if (nombreAux[j].contains("</Name>")) {
					nombreConEspacios = nombreAux[j].substring(0, nombreAux[j].length() - 7);
				}
			}

			// Tratamiento de los nombres:
			// - Sustituyo los espacios por guiones bajos
			// - Sustituyo 'Ñ' por 'N'
			// - Quito paréntesis '(' y ')'
			// - Quito caracter 'ª'
			// - Quito caracter '.'
			for (int k = 0; k < nombreConEspacios.length(); k++) { // Sustituyo los espacios por guiones bajos
				if (nombreConEspacios.charAt(k) == ' ') {
					nombre += "_";
				} else {
					nombre += Character.toString(nombreConEspacios.charAt(k));
				}
				if (nombreConEspacios.charAt(k) == 'Ñ') {
					nombre = nombre.substring(0, nombre.length() - 1);
					nombre += "N";
				}
				if (nombreConEspacios.charAt(k) == '(') {
					nombre = nombre.substring(0, nombre.length() - 1);

				}
				if (nombreConEspacios.charAt(k) == ')') {
					nombre = nombre.substring(0, nombre.length() - 1);
				}
				if (nombreConEspacios.charAt(k) == 'ª') {
					nombre = nombre.substring(0, nombre.length() - 1);
				}
				if (nombreConEspacios.charAt(k) == '.') {
					nombre = nombre.substring(0, nombre.length() - 1);
				}
			}

			estacionesMeteo[i] = nombre;
		}

		// Lee archivos --> extrae datos --> los ingresa en BBDD
		for (int num = 0; num < estacionesMeteo.length; num++) {
			intento = 0;
			String direccionArchivoEntrada = "./archivos/calidadAire/" + estacionesMeteo[num] + ".xml";

			String xml = obj.leerArchivo(direccionArchivoEntrada); // Lee el archivo
			CalidadAire datosMeteo = extraerDatosCalidadAire(xml, "</" + estacionesMeteo[num] + ">", intento); // Extrae los datos y crea los objetos
			while(datosMeteo.getNogm3() == null) {
				System.out.println("Sin datos --> rastrear el siguiente nodo");
				datosMeteo = extraerDatosCalidadAire(xml, "</"+estacionesMeteo[0]+">", intento + 1); // Extrae los datos de una hora anterior y crea los objetos
			} 

			objetos[num] = datosMeteo;

		}
		obj.ingresarObjetos((Object[]) objetos); // Guarda los objetos en la BBDD

	}

	public static CalidadAire extraerDatosCalidadAire(String archivo, String estacion, int intento) {

		int sinDatos = 0;
		String[] datos = archivo.split(estacion);

		String fecha = extraerDato(datos[intento], "Date");
		String hora = extraerDato(datos[intento], "Hour");

		String fechaHora = fecha + "-" + hora;

		String comgm3 = extraerDato(datos[intento], "COmgm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String co8hmgm3 = extraerDato(datos[intento], "CO8hmgm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String nogm3 = extraerDato(datos[intento], "NOgm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String no2gm3 = extraerDato(datos[intento], "NO2gm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String noxgm3 = extraerDato(datos[intento], "NOXgm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String pm10gm3 = extraerDato(datos[intento], "PM10gm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String pm25gm3 = extraerDato(datos[intento], "PM25gm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String so2gm3 = extraerDato(datos[intento], "SO2gm3");
		if (comgm3.contentEquals("SIN DATO")) {
			sinDatos++;
		}

		String nomEstMet = estacion.substring(2, estacion.length() - 1);

		if (sinDatos == 8) {
			// Crea un objeto vacio
			CalidadAire datosCalidadAire = new CalidadAire();
			return datosCalidadAire;
		} else {
			// Crea el objeto
			CalidadAire datosCalidadAire = new CalidadAire(fechaHora, comgm3, co8hmgm3, nogm3, no2gm3, noxgm3, pm10gm3,
					pm25gm3, so2gm3, nomEstMet);
			return datosCalidadAire;
		}
	}

	public static String extraerDato(String archivo, String etiqueta) {
		String dato = "";
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
			return "SIN DATO";
	}

}
