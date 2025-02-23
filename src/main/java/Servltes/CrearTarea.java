package Servltes;

import DAO.proyectoDAO;
import DAO.tareasDAO;
import entities.Proyectos;
import entities.Tareas;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrearTarea", urlPatterns = {"/CrearTarea"})
public class CrearTarea extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del formulario
        int idProyecto = Integer.parseInt(request.getParameter("proyecto"));
        String descripcionTarea = request.getParameter("descripcion_tarea");
        String responsable = request.getParameter("responsable");
        Date fechaInicio = new Date(System.currentTimeMillis());
        String estado = request.getParameter("estado");

        proyectoDAO proyectoDao = new proyectoDAO();
        Proyectos proyecto = proyectoDao.obtenerProyectoPorId(idProyecto);

        if (proyecto != null) {
            // Crear la tarea y asociarla al proyecto
            Tareas tarea = new Tareas();
            tarea.setIdProyecto(proyecto); // Asociar el proyecto completo
            tarea.setDescripcionTarea(descripcionTarea);
            tarea.setResponsable(responsable);
            tarea.setFechaInicio(fechaInicio);
            tarea.setEstado(estado);

            // Guardar la tarea en la base de datos
            tareasDAO tareaDao = new tareasDAO();
            tareaDao.crearTarea(tarea);

            // Redirigir a la lista de tareas del proyecto
            response.sendRedirect("gestorTareas.jsp?idProyecto=" + idProyecto);
        } else {
            // Si no se encuentra el proyecto, mostrar un mensaje de error
            response.sendRedirect("gestorTareas.jsp?error=Proyecto no encontrado.");
        }
    }
}
