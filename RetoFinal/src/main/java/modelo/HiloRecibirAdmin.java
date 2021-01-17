package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibirAdmin extends Thread {
	JTextArea textArea = null;
	JTextField texto = null;
	ObjectInputStream oentrada = null;
	JButton botonEnviar = null;

	public HiloRecibirAdmin(JTextArea textArea, JTextField texto, ObjectInputStream oentrada, JButton botonEnviar) {
		this.textArea = textArea;
		this.texto = texto;
		this.oentrada = oentrada;
		this.botonEnviar = botonEnviar;
	}

	public void run() {

		String texto = "";

		while (!texto.equals("ADIOS")) {
			try {
				texto = (String) oentrada.readObject();
				if(!texto.equals("ADIOS"))
					textArea.append(texto);
				else
					textArea.append("Servidor desconectado");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Termino recibir cliente");
		botonEnviar.setEnabled(false);
	}
}
