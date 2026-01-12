package com.example.controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.modelo.Bosque;
import com.example.modelo.Dragon;
import com.example.util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Bosque`.
 *
 * Permite persistir, actualizar y eliminar bosques. Maneja de forma
 * explícita la apertura/cierre de `Session` y el commit/rollback de
 * transacciones.
 */
public class ControladorBosque {
       public void guardarBosque(Bosque bosque) {
        Session session = null;
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory()) {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Bosque: " + e.getMessage());
        }
    }

    /**
     * Actualiza un `Bosque` existente.
     */
    public void actualizarBosque(Bosque bosque) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Bosque: " + e.getMessage());
        }
    }

    /**
     * Elimina un `Bosque` de la base de datos.
     */
    public void eliminarBosque(Bosque bosque) {
        try (SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
                Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Bosque: " + e.getMessage());
        }
    }
}