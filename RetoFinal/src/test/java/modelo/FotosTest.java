package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FotosTest {
    private String id = "Foto1";
    private Municipios municipio = new Municipios();

    private Fotos foto1 = new Fotos(id, municipio);
    private Fotos foto2 = new Fotos("Foto2");
    private Fotos foto3 = new Fotos();
    
    @Test
    public void ConstructorLlenoTest() {
	assertEquals(foto1.getId(), "Foto1");
	assertEquals(foto1.getMunicipios(), municipio);
    }

    @Test
    public void ConstructorIdTest() {
	assertEquals(foto2.getId(), "Foto2");
    }
    
    @Test
    public void ConstructorVacioTest() {
	foto3.setId("Foto3");
	foto3.setMunicipios(municipio);
	assertEquals(foto3.getId(), "Foto3");
	assertEquals(foto3.getMunicipios(), municipio);
    }
}
