<%-- 
    Document   : registrousuario
    Created on : 22-mar-2018, 20:54:50
    Author     : Edgar
--%>

<%@page import="modelo.Bancos"%>
<%@page import="db.ADOBancos"%>
<%@page import="db.ADOCuotas"%>
<%@page import="modelo.Cuotas"%>
<%@page import="modelo.Cuotas"%>
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
                <h1 class="h2">Registro de Pago</h1>
                <form action="ControladorPagos" method="POST">
                    <input type="hidden" name="accion" value="RegistroPago" />
                    
                    <label>Introduzca el Monto del Pago</label>
                    <br>
                    <input type="number" name="monto" value="" size="15" />
                    <br>
                    
                    <label>Introduzca la Fecha del Pago</label>
                    <br>
                    <input type="date" name="fecha" value="" />
                    <br>
                    
                    <label>Introduzca la Cédula del Depositante</label>
                    <br>
                    <input type="text" name="cedula" value="" size="15" />
                    <br>
                    
                    <label>Introduzca Nombre y Apellido del Depositante</label>
                    <br>
                    <input type="text" name="nombre" value="" size="15" />
                    <br>
                    
                    <label>Introduzca el Número de Referencia</label>
                    <br>
                    <input type="text" name="referencia" value="" size="15" />
                    <br>
                    
                    <label>Entidad Bancaria del Deposito</label>
                    <br>
                    <select name="idBanco">
                            <%
                                        ArrayList<Bancos> listab = ADOBancos.obtenerBancos();
                                        for (Bancos b : listab) {
                            %>
                            <option value = "<%= b.getIdBancos() %>">
                                <%= b.getNombreBanco() %>
                            </option>    
                            <%
                                        }
                            %>
                    </select>
                    <br>
                    
                    <label>Seleccione el Número de Casa</label>
                    <br>
                    <select name="numeroCasa">
                            <%
                                        ArrayList<Casas> lista = ADOCasas.obtenerCasas();
                                        for (Casas c : lista) {
                            %>
                            <option value="<%= c.getIdCasas() %>">
                                <%= c.getIdCasas() %>
                            </option>    
                            <%
                                        }
                            %>
                    </select>
                    <br>
                    
                    <label>Seleccione la Cuota a Cancelar</label>
                    <br>
                    <select name="cuota">
                            <%
                                        ArrayList<Cuotas> listac = ADOCuotas.obtenerCuotas();
                                        for (Cuotas c : listac) {
                            %>
                            <option value="<%= c.getIdCuotas()%>">
                                <%= c.getNombre()%>
                            </option>    
                            <%
                                        }
                            %>
                    </select>
                    <br>
                    <br>
                    <input type="submit" value="Registrar" name="Enviar" />
                </form>
                    <br>
                    <br>
            </div> 
            
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>