package modelo;

import java.util.List;
import org.hibernate.Session;
public class Consultas {

	public static List Consultas(String consulta) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		List hql = session.createQuery(consulta).list();
		session.getTransaction().commit();
		session.close();

		return hql;
	}
}