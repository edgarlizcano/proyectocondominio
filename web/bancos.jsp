<%-- 
    Document   : modulos
    Created on : 24-mar-2018, 15:43:43
    Author     : Edgar
--%>

<%@page import="db.ADOBancos"%>
<%@page import="modelo.Bancos"%>
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
                <h1 class="h2">Lista de Bancos</h1>
                
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID Banco</th>
                            <th scope="col">Nombre del Banco</th>
                            <th colspan="2" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        <%-- Lista de todos los Usuarios --%>
                        <%
                                    ArrayList<Bancos> lista = ADOBancos.obtenerBancos();
                                    for (Bancos b : lista) {
                        %>
                        <tr>
                            <th scope="row"><%= b.getIdBancos() %></th>
                            <td><%= b.getNombreBanco()%></td>
                            <%-- Enlaces a las paginas de Actualizar o eliminar Usuario --%>
                            <td>
                                <a class="btn btn-info" role="button" href="actualizarbanco.jsp?id=<%= b.getIdBancos() %>">Actualizar</a>
                                <a class="btn btn-danger" role="button" href="ControladorBancos?accion=EliminarBanco&id=<%= b.getIdBancos() %>">Eliminar</a>
                            </td>
                        </tr>
                        <%
                                    }
                        %>
                    </tbody>
                  </table>
            </div>
            <div class="container-fluid">
                <a class="btn btn-success" role="button" href="registrobanco.jsp">Ingresar Nuevo Banco</a>
            </div>
            <br>
            <br>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>
