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
                <h1 class="h2">Mis Pagos</h1>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID Pago</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Monto</th>
                            <th scope="col">ID Cuota</th>
                            <th scope="col">Nombre Cuota</th>
                            <th scope="col">ID Casa</th>
                            <th scope="col">Nombre Casa</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                        Habitantes h = (Habitantes) session.getAttribute("habitante");
                        ArrayList<CasasHasCuotas> lista = ADOPagos.obtenerPagosByHabitante(h.getIdHabitante());
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
                    </tbody>
                </table>
            </div>
                    
                    <form action="ControladorPagos" method="POST">
                        <input type="hidden" name="accion" value="ObtenerPagosPorHabitante" />
                        <input type="hidden" name="idHab" value="<%= h.getIdHabitante() %>" />
                        <input type="submit" value="Generar Reporte" />
                    </form>
            
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

