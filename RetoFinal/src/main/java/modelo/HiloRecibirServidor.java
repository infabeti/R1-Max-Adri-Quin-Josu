package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HiloRecibirServidor extends Thread implements Serializable{
	ObjectOutputStream objetoSalida = null;
	ObjectInputStream objetoEntrada = null;
	
	public HiloRecibirServidor(ObjectOutputStream objetoSalida, ObjectInputStream objetoEntrada) {
		this.objetoSalida = objetoSalida;
		this.objetoEntrada = objetoEntrada;
	}
	
	public void run() {
		String consulta = "";
		
		while (!consulta.equals("ADIOS")) {
			try {
				consulta = (String) objetoEntrada.readObject();
				if (consulta.contains("CONSULTA--> ")) {
					consulta = consulta.substring(12);
					objetoSalida.writeObject(modelo.Consultas.Consultas(consulta));
				}else {
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