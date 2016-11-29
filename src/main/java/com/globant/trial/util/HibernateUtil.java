package com.globant.trial.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate utility class, manages SessionFactory
 * 
 * @author Manish
 *
 */
public class HibernateUtil {
	 private static SessionFactory sessionFactory ;
     static {
    	Configuration configuration = new Configuration().configure("/hibernate/hibernate.cfg.xml");
    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
     }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
