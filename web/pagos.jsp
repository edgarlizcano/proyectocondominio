<%-- 
    Document   : pagos
    Created on : 24-mar-2018, 15:40:48
    Author     : Edgar
--%>

<%@page import="db.ADOPagos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.CasasHasCuotas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="access.jsp" %>
<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
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
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID Pago</th>
                            <th scope="col">Fecha</th>
                            <th scope="col">Monto</th>
                            <th scope="col">Estado del Pago</th>
                            <th scope="col">ID Cuota</th>
                            <th scope="col">Nombre Cuota</th>
                            <th scope="col">ID Casa</th>
                            <th scope="col">Nombre Casa</th>
                            <th scope="col">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                        ArrayList<CasasHasCuotas> lista = ADOPagos.obtenerPagos();
                        for (CasasHasCuotas c : lista) {
                    %>
                    <tr>
                        <td><%= c.getPagos().getIdPagos() %></td>
                        <td><%= c.getPagos().getFecha() %></td>
                        <td><%= c.getPagos().getMonto() %></td>
                        <td>
                            <%
                                if (c.getPagos().isEstatus()){
                                    out.print("Confirmado");
                                }else{
                                    out.print("En Espera");
                                }
                            %>
                        </td>
                        <td><%= c.getCuotas().getIdCuotas() %></td>
                        <td><%= c.getCuotas().getNombre() %></td>
                        <td><%= c.getCasas().getIdCasas() %></td>
                        <td><%= c.getCasas().getNombreCasa() %></td>
                        <td>
                            <%
                                if (c.getPagos().isEstatus()){
                                    out.print("Verificado");
                                }else{
                                    %>
                                        <a class="btn btn-success" role="button" href="ControladorPagos?accion=ConfirmarPago&id=<%= c.getPagos().getIdPagos() %>">Confirmar</a>
                                    <%
                                }
                            %>
                            
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
            <div class="container-fluid">
                    <form action="ControlReportes" method="POST">
                        <input type="hidden" name="accion" value="ReportePagos" />
                        <input class="btn btn-info" type="submit" value="Generar Reporte" />
                    </form>
            </div>
            
            </div>
        </main>
        
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

