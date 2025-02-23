/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servltes;

import DAO.proyectoDAO;
import entities.Proyectos;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListarProyectos", urlPatterns = {"/ListarProyectos"})
public class ListarProyectos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el parámetro "estado" del formulario
        String estado = request.getParameter("estado");
        System.out.println("Estado recibido: " + estado); // Depuración

        // Consultar la base de datos
        proyectoDAO proyectoDao = new proyectoDAO();
        List<Proyectos> proyectos = proyectoDao.listarProyectosPorEstado(estado);
        System.out.println("Número de proyectos encontrados: " + proyectos.size()); // Depuración

        // Pasar la lista al JSP
        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("gestorProyectos.jsp").forward(request, response);
    }
}

