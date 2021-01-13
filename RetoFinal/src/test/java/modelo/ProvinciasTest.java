package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProvinciasTest {
	private int id = 1;
	private String nombre = "Álava";
	
	private Provincias provincia = new Provincias(id, nombre);
	private Provincias provincia2 = new Provincias();
	
	@Test
	public void ConstructorLlenoTest() {
		assertEquals(provincia.getId(),1);
		assertEquals(provincia.getNombre(),"Álava");
	}
	
	@Test
	public void ConstructorVacioTest() {
		provincia2.setId(1);
		provincia2.setNombre("Álava");
		assertEquals(provincia2.getId(),1);
		assertEquals(provincia2.getNombre(),"Álava");

	}

}
