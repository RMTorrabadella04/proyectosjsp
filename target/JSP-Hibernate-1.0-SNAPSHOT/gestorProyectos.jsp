<%@page import="entities.Proyectos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor de Proyectos</title>
        <link rel="stylesheet" type="text/css" href="css/gestion.css">
    </head>
    <body>
        <h1>Gestor de Proyectos</h1>
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

                <!-- Mostrar el botón "Listar Proyectos" solo si el usuario es administrador -->
                <% if ("administrador".equals(tipoUsuario)) { %>    
                <form method="post">
                    <input type="hidden" name="accion" value="eliminar">
                    <button type="submit">Eliminar Proyecto</button>
                </form>
                <% }%>

                <!-- Botones para seleccionar la acción -->
                <form method="post">
                    <input type="hidden" name="accion" value="crear">
                    <button type="submit">Crear Proyecto</button>
                </form>

                <form action="ListarProyectos" method="post">
                    <input type="hidden" name="accion" value="listar">
                    <select name="estado">
                        <option value="en curso">En Curso</option>
                        <option value="completado">Completado</option>
                    </select>
                    <button type="submit">Listar Proyectos</button>
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
            <h2>Crear Proyecto</h2><br>
            <form action="CrearProyecto" method="post">
                <p>Nombre: </p><input type="text" name="nombre">
                <p>Descripción: </p><textarea name="descripcion"></textarea><br>
                <select name="estado">
                    <option value="en curso">En Curso</option>
                    <option value="completado">Completado</option>
                </select>
                <button type="submit">Guardar</button>
            </form>

            <%
            } else if ("eliminar".equals(accion)) {
            %>
            <h2>Eliminar Proyecto</h2>
            <form action="EliminarProyecto" method="post"><br>
                <p>ID del Proyecto: </p><input type="text" name="id"><br>
                <button type="submit">Eliminar</button>
            </form>

            <%
            } else if ("listar".equals(accion)) {
            %>
            <c:if test="${not empty proyectos}">
                <div class="table-container">
                    <h2>Lista de Proyectos</h2>
                    <br>
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Estado</th>
                        </tr>
                        <c:forEach items="${proyectos}" var="proyecto">
                            <tr>
                                <td>${proyecto.id}</td>
                                <td>${proyecto.nombreProyecto}</td>
                                <td>${proyecto.descripcion}</td>
                                <td>${proyecto.estado}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${empty proyectos}">
                    <p>No hay proyectos registrados.</p>
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