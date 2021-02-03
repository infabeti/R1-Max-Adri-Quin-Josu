package modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHibernate {
	HibernateUtil HB = new HibernateUtil();
	
	@Test
	public void HibernateTest() {
		HB.shutdown();
	}

}
