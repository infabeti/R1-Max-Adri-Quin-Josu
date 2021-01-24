package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestAdministrar {
	Administrador Admin= new Administrador();
	
	@Test
	public void ConstructorLlenoTest() {
		
		assertEquals(Admin.getPuerto(),5000);
		assertEquals(Admin.getHost(),"localhost");
		
	}
}
