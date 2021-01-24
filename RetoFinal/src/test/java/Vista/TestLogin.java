package Vista;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;

import org.junit.Test;

import vista.VentanaAdministrador;
import vista.VentanaLogin;

public class TestLogin {

	@Test
	public void LoginTest() {
		VentanaLogin frame = new VentanaLogin();
		frame.setVisible(true);		
		assertTrue(frame.isVisible());
	}
//	@Test
//	public void VentanaAdminTest() throws IOException {
//		Socket s = null;
//		String Nombre="Josu";
//		VentanaAdministrador frame = new VentanaAdministrador(s, Nombre);
//		frame.setVisible(true);		
//		assertTrue(frame.isVisible());
//	}
}
