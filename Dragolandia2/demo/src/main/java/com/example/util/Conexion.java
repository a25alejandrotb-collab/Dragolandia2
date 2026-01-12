package com.example.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase auxiliar de conexión a Hibernate.
 *
 * Proporciona métodos estáticos para obtener la `SessionFactory` y abrir
 * sesiones. Aunque no se usa actualmente (se prefiere `HibernateUtil`), esta
 * clase puede servir como alternativa para gestionar conexiones.
 */
public class Conexion {

    // SessionFactory se construye una sola vez al cargar la clase (inicialización estática)
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    /**
     * Obtiene la `SessionFactory` singleton para toda la aplicación.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Abre una nueva `Session` para realizar operaciones con la BD.
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Cierra la `SessionFactory` y libera recursos de conexión.
     */
    public static void cerrar() {
        sessionFactory.close();
    }
}

