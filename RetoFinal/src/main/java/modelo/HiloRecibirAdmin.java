package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import vista.VentanaAdministrador;

public class HiloRecibirAdmin extends Thread implements Serializable{
	String texto = null;
	ObjectInputStream oentrada = null;

	public HiloRecibirAdmin(String texto, ObjectInputStream oentrada) {
		this.texto = texto;
		this.oentrada = oentrada;
	}

	public void run(){

		String texto = "";
		
		while (!texto.equals("ADIOS")) {
			try {
				VentanaAdministrador.editarjlist(oentrada.readObject());
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println("Termino recibir cliente");
	}
}