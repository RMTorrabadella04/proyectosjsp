/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Proyectos;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul
 */
public class proyectoDAO {

    public void crearProyecto(Proyectos proyecto) {
        Transaction transaction = null;
        Session session = null; // Declarar la sesión fuera del try
        try {
            session = HibernateUtil.getSessionFactory().openSession(); // Abrir la sesión
            transaction = session.beginTransaction(); // Iniciar la transacción
            session.save(proyecto); // Guardar el proyecto
            transaction.commit(); // Confirmar la transacción
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Revertir la transacción si hay un error
            }
            e.printStackTrace(); // Imprimir la traza del error
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Cerrar la sesión
            }
        }
    }

    // Listar proyectos filtrados por estado
    public List<Proyectos> listarProyectosPorEstado(String estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Proyectos   WHERE estado = :estado", Proyectos.class)
                    .setParameter("estado", estado)
                    .list();
        }
    }

    // Eliminar un proyecto por ID
    public void eliminarProyecto(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Proyectos proyecto = session.get(Proyectos.class, id);
            if (proyecto != null) {
                session.delete(proyecto);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Para poder obtener el proyecto a la hora de crear una tarea
    
    public Proyectos obtenerProyectoPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Proyectos.class, id);
        }
    }
}
