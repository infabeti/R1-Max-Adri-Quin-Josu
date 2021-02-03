package modelo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UsuariosTest {
	private String idUser = "a";
	private String password = "12345";
	
	private Usuarios usuario = new Usuarios(idUser, password);
	private Usuarios usuario2 = new Usuarios();
	
	@Test
	public void ConstructorLlenoTest() {
		assertEquals(usuario.getIdUser(),"a");
		assertEquals(usuario.getPassword(),"12345");
	}
	
	@Test
	public void ConstructorVacioTest() {
		usuario2.setIdUser(idUser);
		usuario2.setPassword(password);
		assertEquals(usuario2.getIdUser(),"a");
		assertEquals(usuario2.getPassword(),"12345");

	}
}
