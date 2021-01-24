package modelo;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestHiloRecibirServidor {
	HiloRecibirServidor hrs;
	@Test
	public void HiloRecibirTest() {
		ObjectOutputStream objetoSalida = null;
		ObjectInputStream objetoEntrada = null;
		hrs= new HiloRecibirServidor(objetoSalida, objetoEntrada);	
	}
}
