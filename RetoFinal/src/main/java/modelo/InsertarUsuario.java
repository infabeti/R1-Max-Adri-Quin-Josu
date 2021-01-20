package modelo;

import org.hibernate.Session;

public class InsertarUsuario {
    
	public static void main(String[] args) {
//		HibernateUtil hibernateUtil = new HibernateUtil();

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		// Añadir un nuevo objeto usuario
		Usuarios usuario = new Usuarios();
		usuario.setIdUser("AdrianQ");
		usuario.setPassword("11111");

		session.save(usuario);
		
		session.getTransaction().commit();
		session.close();
	}
}
