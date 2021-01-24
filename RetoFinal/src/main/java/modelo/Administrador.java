package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Administrador {
	private int puerto = 5000;
	private String host = "localhost";
	Socket socket = null;
	JTextArea textArea = null;
	String texto = null;
	ObjectOutputStream fsalida = null;
	JButton botonEnviar = null;

	public Administrador() {

	}

	public Administrador(Socket socket, JTextArea textArea, JButton botonEnviar) throws IOException {
		this.socket = socket;
		this.textArea = textArea;
		this.botonEnviar = botonEnviar;
		ObjectInputStream fentrada = null;

		fsalida = new ObjectOutputStream(socket.getOutputStream());
		fentrada = new ObjectInputStream(socket.getInputStream());

		HiloRecibirAdmin hilo = new HiloRecibirAdmin(this.textArea, this.texto, fentrada, this.botonEnviar);
		hilo.start();
	}

	public void enviarMensaje(String mensaje) {
		try {
			if (botonEnviar.isEnabled())
				fsalida.writeObject(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPuerto() {
		return puerto;
	}

	public String getHost() {
		return host;
	}
}
