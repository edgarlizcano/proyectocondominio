<%-- 
    Document   : habitantes
    Created on : 24-mar-2018, 15:41:16
    Author     : Edgar
--%>

<%@page import="db.ADOHabitantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Habitantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
  <%@include file="access.jsp" %>
  <body>    
    <%@include file="nav.jsp" %>
    <div class="container-fluid">
      <div class="row">
              <%@include file ="menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
              <h1 class="h2">Condominio El Cardenal</h1>
            </div>
            <div class="container-fluid">
                <h1 class="h2">Lista de Habitantes</h1>
                    <table border="1">
                        <tr>
                            <td>ID Habitante</td>
                            <td>Cédula</td>
                            <td>Nombres</td>
                            <td>Apellidos</td>
                            <td>Fecha de Nacimiento</td>
                            <td>ID Casa</td>
                            <td>Nombre Casa</td>
                            <td>ID Calle</td>
                            <td>Nombre Calle</td>
                            <td>Estatus</td>
                            <td colspan="2">Acciones</td>
                        </tr>
                        <%-- Lista de todos los Usuarios --%>
                        <%
                                    ArrayList<Habitantes> lista = ADOHabitantes.obtenerHabitantes();
                                    for (Habitantes h : lista) {
                        %>
                        <tr>
                            <td><%= h.getIdHabitante() %></td>
                            <td><%= h.getCedula() %></td>
                            <td><%= h.getNombres() %></td>
                            <td><%= h.getApellidos() %></td>
                            <td><%= h.getFechaNacimiento() %></td>
                            <td><%= h.getCasas().getIdCasas() %></td>
                            <td><%= h.getCasas().getNombreCasa() %></td>
                            <td><%= h.getCasas().getCalles().getIdCalles() %></td>
                            <td><%= h.getCasas().getCalles().getNombreCalle()%></td>
                            <%-- Muestra Activo o Inactivo según estado del Usuario --%>
                            <td><%
                                    if (h.isEstatus()){
                                        out.print("Activo");
                                    }else{
                                        out.print("Inactivo");
                                    }
                                %>
                            </td>
                            <%-- Enlaces a las paginas de Actualizar o eliminar Usuario --%>
                            <td>
                                <form action="ControladorUsuarios">
                                    <input type="hidden" name="accion" value="EliminarUsuario" />
                                    <input type="hidden" name="id" value="<%= h.getIdHabitante() %>" />
                                    <input type="submit" value="Eliminar" name="Eliminar" />
                                </form>
                                <form action="actualizarUsuario.jsp">
                                    <input type="hidden" name="accion" value="ActualizarUsuario" />
                                    <input type="hidden" name="id" value="<%= h.getIdHabitante() %>" />
                                    <input type="submit" value="Actualizar" name="Actualizar" />
                                </form>
                            </td>
                        </tr>
                        <%
                                    }
                        %>

                    </table>
            </div>
            
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

