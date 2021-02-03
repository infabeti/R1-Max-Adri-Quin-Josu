package modelo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalidadAireTest {
	private String fechaHora = "31/12/2020_24:00";
	private String comgm3 = "0,44";
	private String co8hmgm3 = "0,32";
	private String nogm3 = "5";
	private String no2gm3 = "18";
	private String noxgm3 = "26";
	private String pm10gm3 = "12";
	private String pm25gm3 = "5";
	private String so2gm3 = "4";
	private String nomEstMet = "3_DE_MARZO";
	
	
	private CalidadAire calAire = new CalidadAire(fechaHora, comgm3, co8hmgm3, nogm3, no2gm3, noxgm3, pm10gm3, pm25gm3, so2gm3, nomEstMet);
	private CalidadAire calAire2 = new CalidadAire(fechaHora);
	private CalidadAire calAire3 = new CalidadAire();
	
	@Test
	public void ConstructorLlenoTest() {
		assertEquals(calAire.getFechaHora(),"31/12/2020_24:00");
		assertEquals(calAire.getComgm3(),"0,44");
		assertEquals(calAire.getCo8hmgm3(),"0,32");
		assertEquals(calAire.getNogm3(),"5");
		assertEquals(calAire.getNo2gm3(),"18");
		assertEquals(calAire.getNoxgm3(),"26");
		assertEquals(calAire.getPm10gm3(),"12");
		assertEquals(calAire.getPm25gm3(),"5");
		assertEquals(calAire.getSo2gm3(),"4");
		assertEquals(calAire.getNomEstMet(),"3_DE_MARZO");
	}
	
	@Test
	public void ConstructorFechaHoraTest() {
	    assertEquals(calAire2.getFechaHora(),"31/12/2020_24:00");		
	}
	
	@Test
	public void ConstructorVacioTest() {
		calAire3.setFechaHora(fechaHora);
		calAire3.setComgm3(comgm3);
		calAire3.setCo8hmgm3(co8hmgm3);
		calAire3.setNogm3(nogm3);
		calAire3.setNo2gm3(no2gm3);
		calAire3.setNoxgm3(noxgm3);
		calAire3.setPm10gm3(pm10gm3);
		calAire3.setPm25gm3(pm25gm3);
		calAire3.setSo2gm3(so2gm3);
		calAire3.setNomEstMet(nomEstMet);
		
		assertEquals(calAire3.getFechaHora(),"31/12/2020_24:00");
		assertEquals(calAire3.getComgm3(),"0,44");
		assertEquals(calAire3.getCo8hmgm3(),"0,32");
		assertEquals(calAire3.getNogm3(),"5");
		assertEquals(calAire3.getNo2gm3(),"18");
		assertEquals(calAire3.getNoxgm3(),"26");
		assertEquals(calAire3.getPm10gm3(),"12");
		assertEquals(calAire3.getPm25gm3(),"5");
		assertEquals(calAire3.getSo2gm3(),"4");
		assertEquals(calAire3.getNomEstMet(),"3_DE_MARZO");

	}

}
