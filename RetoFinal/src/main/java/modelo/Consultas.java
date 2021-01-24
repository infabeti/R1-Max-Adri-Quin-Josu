package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.Session;

public class Consultas {

	public static String Consultas(String consulta) {
		String resultado = "";

		ArrayList<Municipios> municipio= new ArrayList<Municipios>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		//CONSULTA
		
		Iterator hql = session.createQuery(consulta).iterate();
        while (hql.hasNext()) {
        	Municipios muni = (Municipios) hql.next();
        	municipio.add(muni);
        }  
		for ( int x=0; x<municipio.size(); x++) {
			resultado = municipio.toString();
		}

		session.getTransaction().commit();
		session.close();
		
		return resultado;
	}

}