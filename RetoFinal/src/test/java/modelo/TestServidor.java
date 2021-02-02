package modelo;



import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestServidor {
    @Test
    public void ServidorTest() {
    	Servidor server = new Servidor();
    	server.continuar=false;
    	assertTrue(server.Server());
    }
    @Test
    public void Servidor2Test() {
    	Servidor server = new Servidor();
    	assertTrue(server.Server());
    }
}
