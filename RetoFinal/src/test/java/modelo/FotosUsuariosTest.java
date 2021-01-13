package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class FotosUsuariosTest {
    private String id = "Foto1";
    private Fotos foto1 = new Fotos();
    private Usuarios usuario = new Usuarios();
    private Usuarios usuario2 = new Usuarios();
    
    private Fotos foto2 = new Fotos();
    private Fotos foto3 = new Fotos();

    private FotosUsuarios fotoUsuario1 = new FotosUsuarios(foto1, usuario);
    private FotosUsuarios fotoUsuario2 = new FotosUsuarios(foto2);
    private FotosUsuarios fotoUsuario3 = new FotosUsuarios();

    @Test
    public void ConstructorLlenoTest() {
	assertEquals(fotoUsuario1.getFotos(), foto1);
	assertEquals(fotoUsuario1.getUsuarios(), usuario);
    }

    @Test
    public void ConstructorFotosTest() {
	assertEquals(fotoUsuario2.getFotos(), foto2);
    }
    
    @Test
    public void ConstructorVacioTest() {
	fotoUsuario3.setId("Foto2");
	fotoUsuario3.setFotos(foto3);
	fotoUsuario3.setUsuarios(usuario2);
	assertEquals(fotoUsuario3.getId(), "Foto2");
	assertEquals(fotoUsuario3.getFotos(), foto3);
	assertEquals(fotoUsuario3.getUsuarios(), usuario2);
    }
}
