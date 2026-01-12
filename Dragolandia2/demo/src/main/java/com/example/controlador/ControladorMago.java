package com.example.controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Dragon;
import com.example.modelo.Mago;
import com.example.util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Mago`.
 *
 * Proporciona operaciones CRUD básicas. Usa `HibernateUtil.getSessionFactory()`
 * para obtener la fábrica de sesiones y abre/cierra `Session` en cada operación.
 */
public class ControladorMago {
           public void guardarMago(Mago mago) {
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Mago: " + e.getMessage());
        }
    }

    /**
     * Actualiza un `Mago` existente.
     */
    public void actualizarMago(Mago mago) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Mago: " + e.getMessage());
        }
    }

    /**
     * Elimina un `Mago` de la base de datos.
     */
    public void eliminarMago(Mago mago) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Mago: " + e.getMessage());
        }
    }
}
