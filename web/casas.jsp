<%-- 
    Document   : casas
    Created on : 24-mar-2018, 15:42:24
    Author     : Edgar
--%>

<%@page import="db.ADOCasas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Casas"%>
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
                    <h1 class="h2">Lista de Casas</h1>

                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID Casa</th>
                                <th scope="col">Nombre de Casa</th>
                                <th colspan="2">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%-- Lista de todos los Modulos --%>
                            <%
                                ArrayList<Casas> lista = ADOCasas.obtenerCasas();
                                for (Casas c : lista) {
                            %>
                            <tr>
                                <td><%= c.getIdCasas() %></td>
                                <td><%= c.getNombreCasa() %></td>
                                <%-- Enlaces a las paginas de Actualizar o eliminar Modulo --%>
                                <td>
                                    <a class="btn btn-info" role="button" href="actualizarcasa.jsp?id=<%= c.getIdCasas() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorCasas?accion=EliminarCasa&id=<%= c.getIdCasas() %>">Eliminar</a>
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
                    <a class="btn btn-success" role="button" href="registrocasa.jsp">Ingresar Nueva Casa</a>
                </div>
            </main>   
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>