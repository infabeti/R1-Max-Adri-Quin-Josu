package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibirServidor extends Thread {
	ObjectOutputStream objetoSalida = null;
	ObjectInputStream objetoEntrada = null;
	
	public HiloRecibirServidor(ObjectOutputStream objetoSalida, ObjectInputStream objetoEntrada) {
		this.objetoSalida = objetoSalida;
		this.objetoEntrada = objetoEntrada;
	}
	
	public void run() {
		String consulta = "";
		String respuesta = "";
		
		while (!consulta.equals("ADIOS")) {
			try {
				consulta = (String) objetoEntrada.readObject();
				if (consulta.contains("CONSULTA--> ")) {
					consulta = consulta.substring(12);
					respuesta = modelo.Consultas.Consultas(consulta);
					objetoSalida.writeObject(respuesta);
				} else {
					System.out.println(consulta);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	System.out.println("Termino este hilo del servidor");
	}
}

