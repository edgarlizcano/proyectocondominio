<%-- 
    Document   : calles
    Created on : 24-mar-2018, 15:42:48
    Author     : Edgar
--%>

<%@page import="db.ADOCalles"%>
<%@page import="modelo.Calles"%>
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
                <h1 class="h2">Lista de Calles</h1>
                <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID Calle</th>
                                <th scope="col">Nombre de la Calle</th>
                                <th colspan="2">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%-- Lista de todas las Calles --%>
                            <%
                                ArrayList<Calles> lista = ADOCalles.obtenerCalles();
                                for (Calles c : lista) {
                            %>
                            <tr>
                                <td><%= c.getIdCalles() %></td>
                                <td><%= c.getNombreCalle() %></td>
                                <%-- Enlaces a las paginas de Actualizar o eliminar Calle --%>
                        
                                <td>
                                    <a class="btn btn-info" role="button" href="actualizarcalle.jsp?id=<%= c.getIdCalles() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorCalles?accion=EliminarCalle&id=<%= c.getIdCalles() %>">Eliminar</a>
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
                    <a class="btn btn-success" role="button" href="registrocalle.jsp">Ingresar Nueva Calle</a>
                </div>
            <br>
            <br>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>