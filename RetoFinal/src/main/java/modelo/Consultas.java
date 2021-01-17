package modelo;

import java.util.List;

import org.hibernate.Session;

public class Consultas {

	public static String Consultas(String consulta) {
		List<Object[]> result = null;
		String resultado = "";

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		//CONSULTA
		result = session.createSQLQuery(consulta).list();
		
		try {
		for (Object[] row : result) {
			resultado +=  "\n";
			for (Object col : row) {
				resultado += col + " ";
			}
		}
		resultado +=  "\n";
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();
		session.close();
		
		return resultado;

	}

}


//-->CONSULTA TIPO
//List<Object[]> result = session.createNativeQuery("SELECT * FROM some_table").list();
//for (Object[] row : result) {
//    for (Object col : row) {
//        System.out.print(col);
//    }
//}