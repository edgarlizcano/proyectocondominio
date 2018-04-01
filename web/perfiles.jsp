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
                
                <table class="table">
                        <thead class="thead-dark">
                          <tr>
                            <th scope="col">ID Perfil</th>
                            <th scope="col">Nombre Perfil</th>
                            <th scope="col" colspan="2">Acciones</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            
                              <%-- Lista de todos los Perfiles --%>
                            <%
                                        ArrayList<Perfiles> lista = ADOPerfiles.obtenerPerfiles();
                                        for (Perfiles p : lista) {
                            %>
                            <tr>
                                <td><%= p.getIdPerfil() %></td>
                                <td><%= p.getNombrePerfil() %></td>
                                
                                <%-- Enlaces a las paginas de Actualizar o eliminar Perfil --%>
                                <td>    
                                    <a class="btn btn-info" role="button" href="actualizarperfil.jsp?id=<%= p.getIdPerfil() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorPerfiles?accion=EliminarPerfil&id=<%= p.getIdPerfil() %>">Eliminar</a>
                                </td>
                            </tr>
                            <%
                                        }
                            %>
                          </tr>
                        </tbody>
                      </table>
                    <%
                        String men = request.getParameter("men");
                        if(men!= null){
                            %>
                                <h1><%=men%></h1>
                            <%
                        }
                    %>
                
                
            </div> 
            <div class="container-fluid">
                <a class="btn btn-success" role="button" href="registroperfil.jsp">Ingresar Nuevo Perfil</a>
            </div>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>