<%-- 
    Document   : cuotas
    Created on : 24-mar-2018, 15:41:56
    Author     : Edgar
--%>

<%@page import="db.ADOCuotas"%>
<%@page import="modelo.Cuotas"%>
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
                <h1 class="h2">Lista de Cuotas</h1>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID Cuota</th>
                            <th scope="col">Nombre Cuota</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Monto</th>
                            <th scope="col" colspan="2">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                                ArrayList<Cuotas> lista = ADOCuotas.obtenerCuotas();
                                for (Cuotas c : lista) {
                    %>
                    <tr>
                        <td><%= c.getIdCuotas() %></td>
                        <td><%= c.getNombre() %></td>
                        <td><%= c.getFecha() %></td>
                        <td><%= c.getMonto() %></td>
                        <%-- Enlaces a las paginas de Actualizar o eliminar Cuotas--%>
                        <td>
                                    <a class="btn btn-info" role="button" href="actualizarcuota.jsp?id=<%= c.getIdCuotas() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorCuotas?accion=EliminarCuota&id=<%= c.getIdCuotas() %>">Eliminar</a>
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
                <a class="btn btn-success" role="button" href="registrocuota.jsp">Ingresar Nueva Cuota</a>
            </div>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

