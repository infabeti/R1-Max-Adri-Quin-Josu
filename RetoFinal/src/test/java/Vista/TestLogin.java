package Vista;

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
//		Socket s=null;
//		VentanaAdministrador frame = new VentanaAdministrador(s,"Test");
//		frame.setVisible(true);		
//		assertTrue(frame.isVisible());
//	}
//	@Test
//	public void EditarJlistTest() throws IOException {
//		Socket s=null;
//		VentanaAdministrador frame = new VentanaAdministrador(s,"Test");
//		Object object={"Basauri",1,"",2.2,3.3};
//		frame.editarjlist(object);
//	}
}
