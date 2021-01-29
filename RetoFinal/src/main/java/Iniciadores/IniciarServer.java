package Iniciadores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;

import modelo.DescargarDatosMetereologicos;
import modelo.DescargarJSON;
import modelo.InsertarDatosAtmosfericos;
import modelo.InsertarDatosGeograficos;
import modelo.InsertarRelacionExiste;
import modelo.JSONtoXML;
import modelo.Servidor;

public class IniciarServer {

    public static void main(String[] args) throws FileNotFoundException, JSONException, IOException {
	boolean existen = false;
	// Arranca el servidor
	Servidor servidor = new Servidor();
	servidor.start();

	File archivoMunicipios = new File("./archivos/municipios.json");
	File archivoIndex = new File("./archivos/index.json");
	File archivoEstaciones = new File("./archivos/estaciones.json");
	File archivoEspNaturales = new File("./archivos/espacios-naturales.json");
	
	if (!archivoIndex.exists()) {
	    existen = true;
	}
	if (!archivoMunicipios.exists()) {
	    existen = true;
	}
	if (!archivoEstaciones.exists()) {
	    existen = true;
	}
	if (!archivoEspNaturales.exists()) {
	    existen = true;
	}

	if (!existen) {
	    DescargarJSON descargarJSON = new DescargarJSON();
	    JSONtoXML jsonToXml = new JSONtoXML();
	    InsertarDatosGeograficos insertarDatosGeograficos = new InsertarDatosGeograficos();
	    DescargarDatosMetereologicos descargarDatosMetereologicos = new DescargarDatosMetereologicos();
	    InsertarDatosAtmosfericos insertarDatosAtmosfericos = new InsertarDatosAtmosfericos();
	    InsertarRelacionExiste insertarRelacionExiste = new InsertarRelacionExiste();

	    descargarJSON.DescargarJSON();
	    jsonToXml.JSONtoXML();
	    insertarDatosGeograficos.InsertarDatosGeograficos();
	    descargarDatosMetereologicos.DescargarDatosMetereologicos();
	    insertarDatosAtmosfericos.InsertarDatosAtmosfericos();
	    insertarRelacionExiste.InsertarRelacionExiste();
	}
    }
}
