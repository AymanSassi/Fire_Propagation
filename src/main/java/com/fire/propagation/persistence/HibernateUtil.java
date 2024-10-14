package com.fire.propagation.persistence;

import com.fire.propagation.model.PropagationStep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    private static SessionFactory sessionFactory;

    static {
        try {
            // Charger la configuration Hibernate à partir du fichier hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void savePropagationStep(PropagationStep step) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(step);
        session.getTransaction().commit();
        session.close();
    }

}

