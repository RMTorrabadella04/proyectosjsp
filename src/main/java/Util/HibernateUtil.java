package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(entities.Proyectos.class);
        configuration.addAnnotatedClass(entities.Tareas.class);
        return configuration.buildSessionFactory();
    }

}