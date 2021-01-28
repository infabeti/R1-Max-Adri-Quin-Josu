package modelo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class DescargarJSON {

    /*
     * Descargará los 4 archivos .json que necesitamos: 
     * ● JSON (35.02 KB) - Diarios, horarios e índices de calidad del aire 
     * ● JSON (16.23 KB) - Estaciones de medición 
     * ● JSON (129.41 KB) - Espacios naturales 
     * ● JSON (1.03 MB) - Municipios
     */

    // método para bypass los certificados
    private void trustEveryone() {
	try {
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
		    return true;
		}
	    });
	    SSLContext context = SSLContext.getInstance("TLS");
	    context.init(null, new X509TrustManager[] { new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
		    return new X509Certificate[0];
		}
	    } }, new SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	} catch (Exception e) { // nunca devería ocurrir
	    e.printStackTrace();
	}
    }

    // método para descargar los archivos
    public void descargarJSON(String[] direcciones, String[] archivos, String ruta) {

	try {
	    trustEveryone(); // Para evitar el error de los certificados
	    for (int num = 0; num < direcciones.length; num++) {

		// Urls con los enlaces
		URL url = new URL(direcciones[num]);

		// establecemos conexion
		URLConnection urlCon = url.openConnection();

		// Sacamos por pantalla el tipo de fichero
		System.out.println(urlCon.getContentType());

		// Se obtiene el inputStream del archivo web y se abre el fichero
		// local.
		InputStream is = urlCon.getInputStream();
		FileOutputStream fos = new FileOutputStream(ruta + archivos[num]);

		// Lectura del archivo de la web y escritura en fichero local
		byte[] array = new byte[1000]; // buffer temporal de lectura.
		int leido = is.read(array);
		while (leido > 0) {
		    fos.write(array, 0, leido);
		    leido = is.read(array);
		}

		// cierre de conexion y fichero.
		is.close();
		fos.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void DescargarJSON() {
	String[] direcciones = {
		"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/index.json",
		"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json",
		"https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json",
		"https://opendata.euskadi.eus/contenidos/ds_registros/registro_entidades_locales/opendata/entidades.json",
		"https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/pueblos.json"}; 
		// Cojo los datos de las entidades municipales, que están más limpios
	
	String[] archivos = { "index.json", "estaciones.json", "espacios-naturales.json", "municipios.json", "descripcionMunicipios.json" };
	
	String ruta = "./archivos/";
	
	descargarJSON(direcciones, archivos, ruta);
    }
}
