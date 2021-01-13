package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FotosEuskalmetTest {
    private String id = "Foto1";
    private Fotos foto = new Fotos();
    private Fotos foto1 = new Fotos();
    
    private FotosEuskalmet fotoEuskalmet1 = new FotosEuskalmet(foto);
    private FotosEuskalmet fotoEuskalmet2 = new FotosEuskalmet();
    
    @Test
    public void ConstructorLlenoTest() {
	assertEquals(fotoEuskalmet1.getFotos(), foto);
    }
    
    @Test
    public void ConstructorVacioTest() {
	fotoEuskalmet2.setId("Foto2");
	fotoEuskalmet2.setFotos(foto1);
	assertEquals(fotoEuskalmet2.getId(), "Foto2");
	assertEquals(fotoEuskalmet2.getFotos(), foto1);
    }
}
