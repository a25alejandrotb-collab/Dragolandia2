package com.example.controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Dragon;
import com.example.modelo.Monstruo;
import com.example.util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Monstruo`.
 *
 * Implementa operaciones CRUD con manejo explícito de `Session` y
 * `Transaction`. Los errores se imprimen por ahora en consola.
 */
public class ControladorMonstruo {
           public void guardarMonstruo(Monstruo monstruo) {
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Monstruo: " + e.getMessage());
        }
    }

    /**
     * Actualiza un `Monstruo` existente.
     */
    public void actualizarMonstruo(Monstruo monstruo) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Monstruo: " + e.getMessage());
        }
    }

    /**
     * Elimina un `Monstruo` de la base de datos.
     */
    public void eliminarMonstruo(Monstruo monstruo) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Monstruo: " + e.getMessage());
        }
    }
}
