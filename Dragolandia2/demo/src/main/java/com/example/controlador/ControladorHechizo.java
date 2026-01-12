package com.example.controlador;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Hechizo;

/**
 * Controlador para `Hechizo`.
 *
 * Nota: en este controlador se construye una `SessionFactory` localmente en
 * cada método para simplificar la implementación. En aplicaciones reales es
 * preferible reutilizar una fábrica (por ejemplo, `HibernateUtil`) para evitar
 * coste de creación repetida.
 */
public class ControladorHechizo {
    
    /**
     * Persiste un hechizo usando una SessionFactory.
     */
    public void guardarHechizo(Hechizo hechizo){
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()){
            session=factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Hechizo: " + e.getMessage());
        }
    }

    /**
     * Actualiza un hechizo.
     */
    public void actualizarHechizo(Hechizo hechizo){
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar elHechizo: " + e.getMessage());
        }
    }

    /**
     * Elimina un hechizo.
     */
    public void eliminarHechizo(Hechizo hechizo){
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(hechizo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Hechizo: " + e.getMessage());
        }
    }
}
