package Prueba01;

import org.hibernate.Session;


public class prueba {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
 
        // Añadir un nuevo objeto alumno
        objeto obj = new objeto();
        obj.setNombre("josu");
        obj.setApellido("de los mozos");
        obj.setNumero(10);
        obj.setUnBooleano(true);
 
        session.save(obj);
 
        session.getTransaction().commit();

	}

}
