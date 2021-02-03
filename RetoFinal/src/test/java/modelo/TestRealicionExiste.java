package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRealicionExiste {
	private String Municipios = "Basauri";
	private String NomEsNat = "Pantano";
	RelacionExiste Vacio = new RelacionExiste();
	
	RelacionExiste RE = new RelacionExiste(Municipios,NomEsNat);
	@Test
	public void RelacionExisteConstructorLlenoTest() {
		assertEquals(RE.getNomMunicipio(),"Basauri");
		assertEquals(RE.getNomEspNat(),"Pantano");
	}
	
	@Test
	public void RelacionExisteLlenadoTest() {
		RE.setNomMunicipio("BILBO");
		RE.setNomEspNat("Playa");
		assertEquals(RE.getNomMunicipio(),"BILBO");
		assertEquals(RE.getNomEspNat(),"Playa");
	}
}	
	
