package Iniciadores;

import modelo.Servidor;

public class IniciarServer {

	public static void main(String[] args) {
		// Arranca el servidor
			Servidor servidor = new Servidor();
		servidor.start();
	}
}
