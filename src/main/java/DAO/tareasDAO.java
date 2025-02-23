/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Tareas;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author raul
 */
public class tareasDAO {
  
     public void crearTarea(Tareas tarea) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tarea);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Listar tareas de un proyecto específico
    public List<Tareas> listarTareasPorProyecto(int idProyecto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tareas WHERE id_proyecto = :idProyecto", Tareas.class)
                          .setParameter("idProyecto", idProyecto)
                          .list();
        }
    }

    // Eliminar una tarea por ID
    public void eliminarTarea(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tareas tarea = session.get(Tareas.class, id);
            if (tarea != null) {
                session.delete(tarea);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
}
