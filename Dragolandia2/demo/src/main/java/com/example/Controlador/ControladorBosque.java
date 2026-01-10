package com.example.Controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.Modelo.Bosque;
import com.example.Util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Bosque`.
 *
 * Permite persistir, actualizar y eliminar bosques. Maneja de forma
 * explícita la apertura/cierre de `Session` y el commit/rollback de
 * transacciones.
 */
public class ControladorBosque {

    private SessionFactory factory = HibernateUtil.getSessionFactory();

    /**
     * Persiste un bosque junto con sus monstruos (cascade configurado en la
     * entidad `Bosque`).
     */
    public void guardarBosque(Bosque bosque) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Bosque: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Actualiza un `Bosque` existente.
     */
    public void actualizarBosque(Bosque bosque) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.merge(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Bosque: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Elimina un `Bosque` de la base de datos.
     */
    public void eliminarBosque(Bosque bosque) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.remove(bosque);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Bosque: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}