package modelo;

import static org.junit.Assert.assertEquals;

import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;

import org.junit.Test;

public class TestHiloRecibirAdmin {
	HiloRecibirAdmin HR;
	@Test
	public void TestHiloConstructor() {
		JTextArea textArea = null;
		String texto = null;
		ObjectInputStream oentrada = null;
		JButton botonEnviar = null;
		HR = new HiloRecibirAdmin(textArea, texto, oentrada, botonEnviar);
	}
}
