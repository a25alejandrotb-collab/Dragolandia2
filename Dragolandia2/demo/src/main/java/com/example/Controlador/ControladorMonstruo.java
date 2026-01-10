package com.example.Controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.Modelo.Monstruo;
import com.example.Util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Monstruo`.
 *
 * Implementa operaciones CRUD con manejo explícito de `Session` y
 * `Transaction`. Los errores se imprimen por ahora en consola.
 */
public class ControladorMonstruo {
    private SessionFactory factory = HibernateUtil.getSessionFactory();

    /**
     * Persiste un `Monstruo` en la base de datos.
     */
    public void guardarMonstruo(Monstruo monstruo) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar el Monstruo: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Actualiza un `Monstruo` existente.
     */
    public void actualizarMonstruo(Monstruo monstruo) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.merge(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Monstruo: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Elimina un `Monstruo` de la base de datos.
     */
    public void eliminarMonstruo(Monstruo monstruo) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.remove(monstruo);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Monstruo: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
