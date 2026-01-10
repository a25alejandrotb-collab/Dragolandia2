package com.example.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static boolean initialized = false;

    public static SessionFactory getSessionFactory() {
        if (!initialized) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

    private static void initializeSessionFactory() {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            initialized = true;
            System.out.println("✓ SessionFactory inicializado correctamente");
        } catch (Throwable ex) {
            System.err.println("✗ Error al inicializar SessionFactory: " + ex.getMessage());
            System.err.println("✗ Verifica que MySQL esté corriendo: docker-compose ps");
            System.err.println("✗ Si no está corriendo, inicia: docker-compose up -d");
            ex.printStackTrace();
            throw new RuntimeException("No se puede conectar a la base de datos", ex);
        }
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            getSessionFactory().close();
        }
    }
}
