/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servltes;

import DAO.proyectoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "EliminarProyecto", urlPatterns = {"/EliminarProyecto"})
public class EliminarProyecto extends HttpServlet {

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

        int id = Integer.parseInt(request.getParameter("id"));
        
        proyectoDAO.eliminarProyecto(id);
        
        response.sendRedirect("gestorProyectos.jsp");
    }

}
