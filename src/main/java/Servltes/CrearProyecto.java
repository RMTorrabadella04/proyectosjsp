/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servltes;

import DAO.proyectoDAO;
import entities.Proyectos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "CrearProyecto", urlPatterns = {"/CrearProyecto"})
public class CrearProyecto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private proyectoDAO proyectoDAO;

    @Override
    public void init() {
        proyectoDAO = new proyectoDAO(); // Inicializa el DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Date fechaInicio = new Date(System.currentTimeMillis()); // Fecha actual
        String estado = request.getParameter("estado"); 

        // Crear un nuevo proyecto
        Proyectos proyecto = new Proyectos();
        proyecto.setNombreProyecto(nombre);
        proyecto.setDescripcion(descripcion);
        proyecto.setFechaInicio(fechaInicio);
        proyecto.setEstado(estado);

        // Guardar el proyecto en la base de datos
        proyectoDAO.crearProyecto(proyecto);

        // Redirigir a la página de gestión de proyectos
        response.sendRedirect("gestorProyectos.jsp");
    }
}
