package Prueba01;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() 
    {
    	Configuration configuration = new Configuration();
        configuration.configure(new File("src/hibernate.cfg.xml"));
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
               getSessionFactory().close();
    }
}
