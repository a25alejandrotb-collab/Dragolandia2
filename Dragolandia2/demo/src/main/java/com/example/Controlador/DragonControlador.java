package com.example.Controlador;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import com.example.Modelo.Dragon;

/**
 * Controlador para `Dragon`.
 *
 * Similar a `ControladorHechizo`, crea una `SessionFactory` local por
 * simplicidad. En un entorno real es mejor reutilizar una fábrica compartida.
 */
public class DragonControlador {
        public void guardarDragon(Dragon dragon) {
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Dragon: " + e.getMessage());
        }
    }

    /**
     * Actualiza un `Dragon` existente.
     */
    public void actualizarDragon(Dragon dragon) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Dragon: " + e.getMessage());
        }
    }

    /**
     * Elimina un `Dragon` de la base de datos.
     */
    public void eliminarDragon(Dragon dragon) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(dragon);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Dragon: " + e.getMessage());
        }
    }
}
