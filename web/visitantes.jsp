<%-- 
    Document   : usuarios
    Created on : 23-mar-2018, 13:52:55
    Author     : Edgar
--%>

<%@page import="modelo.Visitantes"%>
<%@page import="db.ADOVisitantes"%>
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
                <h1 class="h2">Lista de Visitantes</h1>
                
                <table class="table">
                        <thead class="thead-dark">
                          <tr>
                            <th scope="col">ID Visitante</th>
                            <th scope="col">Cédula</th>
                            <th scope="col">Nombres</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col" colspan="2">Acciones</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            
                              <%-- Lista de todos los Perfiles --%>
                            <%
                                        ArrayList<Visitantes> lista = ADOVisitantes.obtenerVisitantes();
                                        for (Visitantes v : lista) {
                            %>
                            <tr>
                                <td><%= v.getIdVisitantes() %></td>
                                <td><%= v.getCedula() %></td>
                                <td><%= v.getNombre() %></td>
                                <td><%= v.getApellido() %></td>
                                
                                <%-- Enlaces a las paginas de Actualizar o eliminar Perfil --%>
                                <td>    
                                    <a class="btn btn-info" role="button" href="actualizarvisitante.jsp?id=<%= v.getIdVisitantes() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorPerfiles?accion=EliminarPerfil&id=<%= v.getIdVisitantes() %>">Eliminar</a>
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
                <a class="btn btn-success" role="button" href="registrovisitante.jsp">Ingresar Nuevo Visitante</a>
            </div>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>