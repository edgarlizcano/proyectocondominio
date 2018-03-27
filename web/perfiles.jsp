<%-- 
    Document   : usuarios
    Created on : 23-mar-2018, 13:52:55
    Author     : Edgar
--%>

<%@page import="db.ADOPerfiles"%>
<%@page import="modelo.Perfiles"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
                <h1 class="h2">Lista de Perfiles</h1>
                <table border="1">
                    <tr>
                        <td>ID Perfil</td>
                        <td>Nombre Perfil</td>
                        <td colspan="2">Acciones</td>
                    </tr>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                                ArrayList<Perfiles> lista = ADOPerfiles.obtenerPerfiles();
                                for (Perfiles p : lista) {
                    %>
                    <tr>
                        <td><%= p.getIdPerfil() %></td>
                        <td><%= p.getNombrePerfil() %></td>
                        <%-- Enlaces a las paginas de Actualizar o eliminar Usuario --%>
                        <td>
                            <form action="ControladorUsuarios">
                                <input type="hidden" name="accion" value="EliminarUsuario" />
                                <input type="hidden" name="id" value="<%= p.getIdPerfil() %>" />
                                <input type="submit" value="Eliminar" name="Eliminar" />
                            </form>
                            <form action="actualizarUsuario.jsp">
                                <input type="hidden" name="accion" value="ActualizarUsuario" />
                                <input type="hidden" name="id" value="<%= p.getIdPerfil() %>" />
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