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
                            <td><%= m.getIdModulo() %></td>
                            <td><%= m.getNombreModulo() %></td>
                            <td><%= m.getUrlModulo() %></td>
                            <%-- Enlaces a las paginas de Actualizar o eliminar Modulo --%>
                            <td>
                                <a class="btn btn-info" role="button" href="actualizarModulo.jsp?id=<%= m.getIdModulo() %>">Actualizar</a>
                                <a class="btn btn-danger" role="button" href="ControladorModulos?accion=EliminarModulo&id=<%= m.getIdModulo() %>">Eliminar</a>
                            </td>
                        </tr>
                        <%
                                    }
                        %>
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
                <a class="btn btn-success" role="button" href="registromodulo.jsp">Ingresar Nuevo Módulo</a>
            </div>
        </main>
      </div>
    </div>
            <br>
            <br>
  </body>
  <%@include file ="scripts.jsp" %>
</html>
