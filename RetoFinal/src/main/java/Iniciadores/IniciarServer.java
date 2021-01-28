package Iniciadores;

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

	public static void main(String[] args) throws FileNotFoundException, JSONException, IOException{
		// Arranca el servidor
		Servidor servidor = new Servidor();
		servidor.start();
		
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
