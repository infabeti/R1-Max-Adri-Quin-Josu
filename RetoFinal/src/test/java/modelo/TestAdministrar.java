package modelo;

import static org.junit.Assert.assertEquals;

import java.net.Socket;

import org.junit.Test;

public class TestAdministrar {
	Administrador Admin= new Administrador();
	
	@Test
	public void ConstructorLlenoTest() {
		assertEquals(Admin.getPuerto(),5000);
		assertEquals(Admin.getHost(),"localhost");
		
	}
	@Test
	public void enviarMensajeTest() {
		Admin.enviarMensaje("Hola");
		
	}
	@Test
	public void enviarMensajeTest2() {
		String[] mensaje = null;
		Admin.enviarMensaje(mensaje);
		String[] mensaje2 = {"Enero", "Febrero"};
		Admin.enviarMensaje(mensaje2);
	}
}
