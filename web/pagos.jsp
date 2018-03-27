<%-- 
    Document   : pagos
    Created on : 24-mar-2018, 15:40:48
    Author     : Edgar
--%>

<%@page import="db.ADOPagos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.CasasHasCuotas"%>
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
                <h1 class="h2">Lista de Pagos</h1>
                <table border="1">
                    <tr>
                        <td>ID Pago</td>
                        <td>Fecha</td>
                        <td>Monto</td>
                        <td>ID Cuota</td>
                        <td>Nombre Cuota</td>
                        <td>ID Casa</td>
                        <td>Nombre Casa</td>
                    </tr>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                                ArrayList<CasasHasCuotas> lista = ADOPagos.obtenerPagos();
                                for (CasasHasCuotas c : lista) {
                    %>
                    <tr>
                        <td><%= c.getPagos().getIdPagos() %></td>
                        <td><%= c.getPagos().getFecha() %></td>
                        <td><%= c.getPagos().getMonto() %></td>
                        <td><%= c.getCuotas().getIdCuotas() %></td>
                        <td><%= c.getCuotas().getNombre() %></td>
                        <td><%= c.getCasas().getIdCasas() %></td>
                        <td><%= c.getCasas().getNombreCasa() %></td>
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
