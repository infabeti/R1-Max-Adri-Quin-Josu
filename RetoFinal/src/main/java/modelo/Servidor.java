package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread {

	int PUERTO = 5000;
	ServerSocket servidor = null;
	boolean continuar = true;

	public void run() {

		Server();
	}
	public boolean Server() {
		try {
			servidor = new ServerSocket(PUERTO);

			System.out.println("Servidor iniciado...");
			Socket socket = new Socket();
			
			while (continuar) {
				socket = servidor.accept();
				ObjectOutputStream osalida = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream oentrada = new ObjectInputStream(socket.getInputStream());
				HiloRecibirServidor hilo = new HiloRecibirServidor(osalida, oentrada);
				hilo.start();
			}

			socket.close();

			System.out.println("Servidor terminado");

		} catch (IOException e) {
			System.out.println("Servidor cerrado");
			
			System.exit(0);
		}
		return true;
	}

}
