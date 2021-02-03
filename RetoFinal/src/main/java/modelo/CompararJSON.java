package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CompararJSON {
	
	public CompararJSON() {
		
	}
	
	public String cifrar(String texto) {
		String encriptado = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
			byte dataBytes[] = texto.getBytes();
			md.update(dataBytes);
			byte resumen[] = md.digest();
			for (byte b: resumen) {
				encriptado+=b;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		}	
		return encriptado;
	}
	
	public Boolean compararCifrado(String x, String y) {
		
		return x.equals(y);
	}
	
	
}
