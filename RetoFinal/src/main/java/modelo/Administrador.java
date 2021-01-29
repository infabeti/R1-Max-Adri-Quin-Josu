package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Administrador {
	private int puerto = 5000;
	private String host = "localhost";
	Socket socket = null;
	String texto = null;
	ObjectOutputStream fsalida = null;

	public Administrador() {

	}

	public Administrador(Socket socket, String texto) throws IOException {
		this.socket = socket;
		this.texto = texto;
		ObjectInputStream fentrada = null;

		fsalida = new ObjectOutputStream(socket.getOutputStream());
		fentrada = new ObjectInputStream(socket.getInputStream());

		HiloRecibirAdmin hilo = new HiloRecibirAdmin(this.texto, fentrada);
		hilo.start();
	}
	public Administrador(Socket socket) throws IOException {
		this.socket = socket;
		ObjectInputStream fentrada = null;

		fsalida = new ObjectOutputStream(socket.getOutputStream());
		fentrada = new ObjectInputStream(socket.getInputStream());

		HiloRecibirAdmin hilo = new HiloRecibirAdmin(this.texto, fentrada);
		hilo.start();
	}

	public void enviarMensaje(String mensaje) {
		try {
			fsalida.writeObject(mensaje);
		} catch (Exception e) {

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
