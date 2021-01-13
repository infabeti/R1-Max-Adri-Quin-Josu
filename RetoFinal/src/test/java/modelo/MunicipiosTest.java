package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MunicipiosTest {
    private String nombre = "Vitoria";
    private Provincias provincia = new Provincias(1, "Álava");

    private Municipios municipio = new Municipios(nombre, provincia);
    private Municipios municipio2 = new Municipios(nombre);
    private Municipios municipio3 = new Municipios();
    
    private Provincias provincia2 = new Provincias(48, "Bizkaia");

    @Test
    public void ConstructorLlenoTest() {
	assertEquals(municipio.getNombre(), "Vitoria");
	assertEquals(municipio.getProvincias().getId(), 1);
	assertEquals(municipio.getProvincias().getNombre(), "Álava");
    }

    @Test
    public void ConstructorNombreTest() {
	assertEquals(municipio2.getNombre(), "Vitoria");
    }
    
    @Test
    public void ConstructorVacioTest() {
	municipio3.setNombre("Bilbao");
	municipio3.setProvincias(provincia2);
	
	assertEquals(municipio3.getNombre(), "Bilbao");
	assertEquals(municipio3.getProvincias().getId(), 48);
	assertEquals(municipio3.getProvincias().getNombre(), "Bizkaia");
    }
}
