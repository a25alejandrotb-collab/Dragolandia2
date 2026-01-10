package com.example.Controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.example.Modelo.Mago;
import com.example.Util.HibernateUtil;

/**
 * Controlador (DAO) para la entidad `Mago`.
 *
 * Proporciona operaciones CRUD básicas. Usa `HibernateUtil.getSessionFactory()`
 * para obtener la fábrica de sesiones y abre/cierra `Session` en cada operación.
 */
public class ControladorMago {
    private SessionFactory factory = HibernateUtil.getSessionFactory();

    /**
     * Persiste un `Mago` en la base de datos.
     */
    public void guardarMago(Mago mago) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(mago);
            tx.commit();
        } catch (Exception e) {
            // Mensaje claro para depuración; en producción usar logger
            System.out.println("Error al guardar el Mago: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Actualiza los datos de un `Mago` existente usando `merge`.
     */
    public void actualizarMago(Mago mago) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.merge(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar el Mago: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Elimina un `Mago` de la base de datos.
     */
    public void eliminarMago(Mago mago) {
        Session session = null;
        try {
            session = factory.openSession();
            Transaction tx = session.beginTransaction();
            session.remove(mago);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar el Mago: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
