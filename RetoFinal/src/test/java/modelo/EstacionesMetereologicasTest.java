package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class EstacionesMetereologicasTest {
    private String nombre = "ABANTO";
    private String municipio = "Abanto y Ciérvana-Abanto Zierbena";
    private String municipio2 = "Getxo";
    private String direccion = "Avda. del Minero, 2. Ayuntamiento";
    private Double latitud = 43.32047399433485;
    private Double longitud = -3.0741559961978044;

    private EstacionesMeteorologicas estacionesMetereologicas1 = new EstacionesMeteorologicas(nombre, municipio, direccion, latitud, longitud);
    private EstacionesMeteorologicas estacionesMetereologicas2 = new EstacionesMeteorologicas("AGURAIN");
    private EstacionesMeteorologicas estacionesMetereologicas3 = new EstacionesMeteorologicas();
    
    @Test
    public void ConstructorLlenoTest() {
	assertEquals(estacionesMetereologicas1.getNombre(), "ABANTO");
	assertEquals(estacionesMetereologicas1.getMunicipios(), municipio);
	assertEquals(estacionesMetereologicas1.getDireccion(), "Avda. del Minero, 2. Ayuntamiento");
	assertEquals(estacionesMetereologicas1.getLatitud(), 43.32047399433485, 0.0);
	assertEquals(estacionesMetereologicas1.getLongitud(), -3.0741559961978044, 0.0);
    }

    @Test
    public void ConstructorNombreTest() {
	assertEquals(estacionesMetereologicas2.getNombre(), "AGURAIN");
    }
    
    @Test
    public void ConstructorVacioTest() {
	estacionesMetereologicas3.setNombre("ALGORTA (BBIZI2)");
	estacionesMetereologicas3.setMunicipios(municipio2);
	estacionesMetereologicas3.setDireccion("Carretera de Galea, s/n");
	estacionesMetereologicas3.setLatitud(43.362055748944286);
	estacionesMetereologicas3.setLongitud(-3.0227822073211765);
	
	assertEquals(estacionesMetereologicas3.getNombre(), "ALGORTA (BBIZI2)");
	assertEquals(estacionesMetereologicas3.getMunicipios(), "Getxo");
	assertEquals(estacionesMetereologicas3.getDireccion(), "Carretera de Galea, s/n");
	assertEquals(estacionesMetereologicas3.getLatitud(), 43.362055748944286, 0.0);
	assertEquals(estacionesMetereologicas3.getLongitud(), -3.0227822073211765, 0.0);
    }
}
