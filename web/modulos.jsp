<%-- 
    Document   : modulos
    Created on : 24-mar-2018, 15:43:43
    Author     : Edgar
--%>

<%@page import="modelo.Modulos"%>
<%@page import="db.ADOModulos"%>
<%@page import="java.util.ArrayList"%>
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
                <h1 class="h2">Lista de Módulos</h1>
                
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID Módulo</th>
                            <th scope="col">Nombre de Módulo</th>
                            <th scope="col">URL de Módulo</th>
                            <th colspan="2" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%-- Lista de todos los Usuarios --%>
                        <%
                                    ArrayList<Modulos> lista = ADOModulos.obtenerModulos();
                                    for (Modulos m : lista) {
                        %>
                        <tr>
                            <th scope="row"><%= m.getIdModulo() %></th>
                            <td><%= m.getNombreModulo() %></td>
                            <td><%= m.getUrlModulo() %></td>
                            <%-- Enlaces a las paginas de Actualizar o eliminar Usuario --%>
                            <td>
                                <form action="ControladorUsuarios">
                                    <input type="hidden" name="accion" value="EliminarUsuario" />
                                    <input type="hidden" name="id" value="<%= m.getIdModulo() %>" />
                                    <input type="submit" value="Eliminar" name="Eliminar" />
                                </form>
                                <form action="actualizarUsuario.jsp">
                                    <input type="hidden" name="accion" value="ActualizarUsuario" />
                                    <input type="hidden" name="id" value="<%= m.getIdModulo() %>" />
                                    <input type="submit" value="Actualizar" name="Actualizar" />
                                </form>
                            </td>
                        </tr>
                        <%
                                    }
                        %>
                    </tbody>
                  </table>
            </div>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>
