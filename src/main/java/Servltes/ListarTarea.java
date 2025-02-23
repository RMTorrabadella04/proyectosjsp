/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servltes;

import DAO.tareasDAO;
import entities.Tareas;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "ListarTarea", urlPatterns = {"/ListarTarea"})
public class ListarTarea extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        // Obtener el parámetro "idProyecto" del formulario
        String idProyectoStr = request.getParameter("idProyecto");
        System.out.println("ID del proyecto recibido: " + idProyectoStr); // Depuración

        // Convertir el ID del proyecto a un número entero
        int idProyecto;
        try {
            idProyecto = Integer.parseInt(idProyectoStr);
        } catch (NumberFormatException e) {
            // Manejar el error si el ID no es un número válido
            System.out.println("Error: El ID del proyecto no es válido."); // Depuración
            response.sendRedirect("gestorTareas.jsp?error=El ID del proyecto no es válido.");
            return;
        }

        // Consultar la base de datos
        tareasDAO tareaDao = new tareasDAO();
        List<Tareas> tareas = tareaDao.listarTareasPorProyecto(idProyecto);
        System.out.println("Número de proyectos encontrados: " + tareas.size()); // Depuración

        // Pasar la lista al JSP
        request.setAttribute("tareas", tareas);
        request.getRequestDispatcher("gestorTareas.jsp").forward(request, response);
    }

}
