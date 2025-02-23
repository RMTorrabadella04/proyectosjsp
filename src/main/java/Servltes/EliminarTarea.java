/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servltes;

import DAO.tareasDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet(name = "EliminarTarea", urlPatterns = {"/EliminarTarea"})
public class EliminarTarea extends HttpServlet {

    private tareasDAO tareaDAO;

    @Override
    public void init() {
        tareaDAO = new tareasDAO(); // Inicializa el DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        
        tareaDAO.eliminarTarea(id);
        
        response.sendRedirect("gestorTareas.jsp");
    }

}
