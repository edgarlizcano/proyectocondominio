<%-- 
    Document   : pagos
    Created on : 24-mar-2018, 15:40:48
    Author     : Edgar
--%>

<%@page import="modelo.Pagos"%>
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
                            <th scope="col">Banco</th>
                            <th scope="col">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%-- Lista de todos los Usuarios --%>
                    <%
                        ArrayList<Pagos> lista = (ArrayList) request.getSession().getAttribute("lista");
                        for (Pagos c : lista) {
                    %>
                    <tr>
                        <td><%= c.getIdPagos() %></td>
                        <td><%= c.getFecha() %></td>
                        <td><%= c.getMonto() %></td>
                        <td>
                            <%
                                if (c.isEstatus()){
                                    out.print("Confirmado");
                                }else{
                                    out.print("En Espera");
                                }
                            %>
                        </td>
                        <td><%= c.getBanco().getNombreBanco() %></td>
                        <td>
                            <%
                                if (c.isEstatus()){
                                    out.print("Verificado");
                                }else{
                                    %>
                                        <a class="btn btn-success" role="button" href="ControladorPagos?accion=ConfirmarPago&id=<%= c.getIdPagos() %>">Confirmar</a>
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
                    String men = (String) request.getSession().getAttribute("men");
                    if(men!= null){
                        %>
                            <h1><%=men%></h1>
                        <%
                        request.getSession().removeAttribute("men");
                    }
                %>
            <div class="container-fluid">
                    <a href="consultapagos.jsp"><input class="btn btn-info" type="button" value="Nueva Consulta" /></a> 
            </div>
            
            </div>
        </main>
        
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

