<%@page import="DAO.tareasDAO"%>
<%@page import="entities.Tareas"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Tareas</title>
        <link rel="stylesheet" type="text/css" href="css/gestion.css">
    </head>
    <body>
        <h1>Gestor de Tareas</h1>
        <div class="contenedor">
            <div class="botones-container">

                <!-- Botón para volver al inicio -->
                <a href="index.jsp" id="volver">
                    <button type="button">Volver</button>
                </a>

                <%
                    // Obtener el valor de tipoUsuario enviado desde el enlace
                    String tipoUsuario = request.getParameter("tipoUsuario");
                    if (tipoUsuario != null) {
                        // Guardar el valor en la sesión
                        session.setAttribute("tipoUsuario", tipoUsuario);
                    }

                    // Obtener el valor de tipoUsuario de la sesión
                    tipoUsuario = (String) session.getAttribute("tipoUsuario");
                    if (tipoUsuario == null) {
                        tipoUsuario = "usuario"; // Valor por defecto
                    }
                %>

                <!-- Mostrar el botón "Agregar Tarea" -->
                <form method="post">
                    <input type="hidden" name="accion" value="crear">
                    <button type="submit">Agregar Tarea</button>
                </form>

                <!-- Mostrar el botón "Eliminar Tarea" solo si el usuario es administrador -->
                <% if ("administrador".equals(tipoUsuario)) { %>
                <form method="post">
                    <input type="hidden" name="accion" value="eliminar">
                    <button type="submit">Eliminar Tarea</button>
                </form>
                <% }%>

                <!-- Formulario para listar tareas de un proyecto específico -->
                <form method="post">
                    <input type="hidden" name="accion" value="listar">
                    ID del Proyecto: <input type="text" name="idProyecto" required>
                    <button type="submit">Listar Tareas</button>
                </form>
            </div>

            <hr>

            <p id="seleccionTipoUsuario">
                Selecciona tu tipo de usuario:
                <a href="gestorProyectos.jsp?tipoUsuario=usuario">Usuario</a> |
                <a href="gestorProyectos.jsp?tipoUsuario=administrador">Administrador</a>
            </p>

            <!-- Mostrar contenido dinámico -->
            <%
                String accion = request.getParameter("accion");
                if ("crear".equals(accion)) {
            %>
            <h2>Agregar Nueva Tarea</h2>
            <br>
            <form action="CrearTarea" method="post">
                <p>Descripción:</p> <textarea name="descripcion_tarea" required></textarea>
                <p>Responsable: </p><input type="text" name="responsable" required>
                <p>Estado: </p>
                <select name="estado" required>
                    <option value="pendiente">Pendiente</option>
                    <option value="en progreso">En Progreso</option>
                    <option value="completada">Completada</option>
                </select><br>
                <p>ID del Proyecto: </p><input type="text" name="proyecto" required>
                <button type="submit">Guardar</button>
            </form>

            <%
            } else if ("eliminar".equals(accion)) {
            %>
            <h2>Eliminar Tarea</h2>
            <br>
            <form action="EliminarTarea" method="post">
                <p>ID de la Tarea: </p><input type="text" name="id" required><br>
                <button type="submit">Eliminar</button>
            </form>

            <%
            } else if ("listar".equals(accion)) {
                // Obtener el ID del proyecto desde la solicitud
                String idProyectoStr = request.getParameter("idProyecto");
                if (idProyectoStr != null && !idProyectoStr.isEmpty()) {
                    int idProyecto = Integer.parseInt(idProyectoStr);

                    // Obtener las tareas del proyecto
                    tareasDAO tareaDao = new tareasDAO();
                    List<Tareas> tareas = tareaDao.listarTareasPorProyecto(idProyecto);
                    request.setAttribute("tareas", tareas);
                }
            %>
            <div class="table-container">
                <c:if test="${not empty tareas}">
                    <h2>Lista de Tareas</h2>
                    <br>
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Descripción</th>
                            <th>Responsable</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Estado</th>
                        </tr>
                        <c:forEach items="${tareas}" var="tarea">
                            <tr>
                                <td>${tarea.id}</td>
                                <td>${tarea.descripcionTarea}</td>
                                <td>${tarea.responsable}</td>
                                <td>${tarea.fechaInicio}</td>
                                <td>${tarea.fechaFin}</td>
                                <td>${tarea.estado}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${empty tareas}">
                    <p>No hay tareas registradas para este proyecto.</p>
                </c:if>
                <%
                } else {
                %>
                <p>Selecciona una opción para comenzar.</p>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>