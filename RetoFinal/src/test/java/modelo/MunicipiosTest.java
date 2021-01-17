package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class MunicipiosTest {
    private String nombre = "Vitoria";
    private int idProvincia = 1;
    private int idProvincia2 = 48;

    private Municipios municipio = new Municipios(nombre, idProvincia);
    private Municipios municipio2 = new Municipios(nombre);
    private Municipios municipio3 = new Municipios();

    @Test
    public void ConstructorLlenoTest() {
	assertEquals(municipio.getNombre(), "Vitoria");
	assertEquals(municipio.getIdProvincias(), 1);
    }

    @Test
    public void ConstructorNombreTest() {
	assertEquals(municipio2.getNombre(), "Vitoria");
    }
    
    @Test
    public void ConstructorVacioTest() {
	municipio3.setNombre("Bilbao");
	municipio3.setIdProvincias(idProvincia2);
	
	assertEquals(municipio3.getNombre(), "Bilbao");
	assertEquals(municipio3.getIdProvincias(), 48);
    }
}
