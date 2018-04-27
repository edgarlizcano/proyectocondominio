<%-- 
    Document   : casas
    Created on : 24-mar-2018, 15:42:24
    Author     : Edgar
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
                <h1 class="h2">Consulta de Pagos</h1>
                <br>
                <form action="ControladorPagos" method="post">
                    <input type="hidden" name="accion" value="ConsultaPorFiltro"/>
                    
                    <fieldset class="form-group">
                        <h5>Seleccione las opciones para la Consulta</h5>
                        <div class="container-fluid">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="todos" checked onclick="filtro(this)">
                                <label class="form-check-label" for="gridRadios1">
                                    Todos
                                </label>
                            </div>
   
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="pendientes" onclick="filtro(this)">
                                <label class="form-check-label" for="gridRadios2">
                                  Pagos Pendientes
                                </label>
                            </div>
                            
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="confirmados" onclick="filtro(this)">
                                <label class="form-check-label" for="gridRadios3">
                                  Pagos Confirmados
                                </label>
                            </div>
                            
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gridRadios" id="radioCasa" value="porCasa" onclick="filtro(this)">
                                <label class="form-check-label" for="radioCasa">
                                  Pagos por Casa
                                </label>
                                <br>
                                <div id ="formNumCasa" style="display: none"> 
                                    <label class="form-check-label">
                                    Elija el Número de Casa
                                    </label>
                                    <select name="numCasa">
                                        <%
                                            ArrayList<Casas> listac = ADOCasas.obtenerCasas();
                                            for (Casas c : listac) {
                                        %>
                                                <option value="<%= c.getIdCasas()%>">
                                                    <%= c.getIdCasas() %>
                                                </option>    
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                
                            </div>
                            <div class="form-check" >
                                <input class="form-check-input" type="radio" name="gridRadios" id="radioFecha" value="porFecha" onclick="filtro(this)">
                                <label class="form-check-label" for="radioFecha">
                                  Pagos por Fecha
                                </label>
                                <br>
                                <%
                                    Date now = new Date();
                                    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
                                    String currentDate = ft.format(now);
                                %>
                                <div id ="formFecha" style="display: none">
                                    <label class="form-check-label" for="gridRadios3">
                                        Seleccione el rango de fechas
                                    </label>
                                    Entre <input type="date" name="fechainicial" value="<%=currentDate%>" /> hasta <input type="date" name="fechafinal" value="<%=currentDate%>" />
                                </div>
                                
                            </div>
                          
                        </div>
                    </fieldset>
                    
                    <div class="form-group row">
                      <div class="col-sm-10">
                        <button type="submit" class="btn btn-primary">Consultar</button>
                      </div>
                    </div>
                  </form>
            </div>
            <br>
            <br>
            <%
                String men=request.getParameter("men");
                if(men!= null){
                    %>
                        <h1><%=men%></h1>
                    <%
                }
            %>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
  <script src="js/consulta.js" language="JavaScript"></script>
</html>