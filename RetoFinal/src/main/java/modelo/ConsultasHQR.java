package modelo;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;

public class ConsultasHQR {

	public static void main(String[] args) {
		List<Object[]> result = null;
		String resultado = "";

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
// ============================================================================ //
		// MÉTODO EN PROCESO //
		
		//CONSULTA
//		result = session.createSQLQuery(consulta).list();
		
		Query respuesta = (Query) session.createQuery("Select idUser from usuarios"); //Iker dice de usar este comando, devuelve objetos
		System.out.println(respuesta);
//		try {
//		for (Object[] row : result) {
//			resultado +=  "\n";
//			for (Object col : row) {
//				resultado += col + " ";
//			}
//		}
//		resultado +=  "\n";
//		} catch (ClassCastException e) {
//			e.printStackTrace();
//		}
		session.getTransaction().commit();
		HibernateUtil.shutdown();
		
	//	return resultado;
// ============================================================================ //
	}

}
