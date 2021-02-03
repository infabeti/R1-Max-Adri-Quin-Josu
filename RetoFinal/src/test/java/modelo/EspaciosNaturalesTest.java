package modelo;

import static org.junit.Assert.*;

import javax.persistence.Column;

import org.junit.Test;

public class EspaciosNaturalesTest {
    private String nombre = "Embalse de Maroño";
    private String descripcion = "El embalse de Maroño, enclavado en la localidad del mismo nombre, perteneciente al municip...";
	private double latitud = 43.0468126377076;
	private double longitud = -3.05986404418945;
    private String tipo = "Pantanos";
    private String web = "https://opendata.euskadi.eus/datos/-/es/playas-embalses-rios/embalse-de-marono/";


    private EspaciosNaturales espacioNatural1 = new EspaciosNaturales(nombre, descripcion, latitud, longitud, tipo, web);
    private EspaciosNaturales espacioNatural2 = new EspaciosNaturales("Embalse de Ullíbarri-Gamboa");
    private EspaciosNaturales espacioNatural3 = new EspaciosNaturales();
    
    @Test
    public void ConstructorLlenoTest() {
	assertEquals(espacioNatural1.getNombre(), "Embalse de Maroño");
	assertEquals(espacioNatural1.getDescripcion(), "El embalse de Maroño, enclavado en la localidad del mismo nombre, perteneciente al municip...");
	assertEquals(espacioNatural1.getLatitud(), 43.0468126377076, 0.0);
	assertEquals(espacioNatural1.getLongitud(), -3.05986404418945, 0.0);
	assertEquals(espacioNatural1.getTipo(), "Pantanos");
	assertEquals(espacioNatural1.getWeb(), "https://opendata.euskadi.eus/datos/-/es/playas-embalses-rios/embalse-de-marono/");
    }
    
    @Test
    public void ConstructorNombreTest() {
	assertEquals(espacioNatural2.getNombre(), "Embalse de Ullíbarri-Gamboa");
    }

    @Test
    public void ConstructorVacioTest() {
	espacioNatural3.setNombre("Embalse de Urkulu");
	espacioNatural3.setDescripcion("El embalse de Urkulu se halla en el interior del territorio histórico de Gipuzkoa, a pocos...");
	espacioNatural3.setLatitud(43.0468126377076);
	espacioNatural3.setLongitud(-3.05986404418945);
	espacioNatural3.setTipo("Pantanos");
	espacioNatural3.setWeb("https://opendata.euskadi.eus/datos/-/es/playas-embalses-rios/embalse-de-urkulu/");
	
	assertEquals(espacioNatural3.getNombre(), "Embalse de Urkulu");
	assertEquals(espacioNatural3.getDescripcion(), "El embalse de Urkulu se halla en el interior del territorio histórico de Gipuzkoa, a pocos...");
	assertEquals(espacioNatural3.getLatitud(), 43.0468126377076, 0.0);
	assertEquals(espacioNatural3.getLongitud(), -3.05986404418945, 0.0);
	assertEquals(espacioNatural3.getTipo(), "Pantanos");
	assertEquals(espacioNatural3.getWeb(), "https://opendata.euskadi.eus/datos/-/es/playas-embalses-rios/embalse-de-urkulu/");
    }
}
