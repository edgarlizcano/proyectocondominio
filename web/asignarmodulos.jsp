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
                <h1 class="h2">Asignar Módulo a Perfil</h1>
                <form action="ControladorModulos" method="POST">
                    <input type="hidden" name="accion" value="AsignaModulo" />
                    
                    <label>Nombre del Módulo</label>
                    <br>
                    <select name="modulo">
                            <%
                                        ArrayList<Modulos> lista = ADOModulos.obtenerModulos();
                                        for (Modulos m : lista) {
                            %>
                            <option value = "<%= m.getIdModulo() %>">
                                <%= m.getNombreModulo() %>
                            </option>    
                            <%
                                        }
                            %>
                    </select>
                    <br>
                    
                    <label>Seleccione el Prefil</label>
                    <br>
                    <select name="perfil">
                            <%
                                        ArrayList<Perfiles> listap = ADOPerfiles.obtenerPerfiles();
                                        for (Perfiles p : listap) {
                            %>
                            <option value="<%= p.getIdPerfil() %>">
                                <%= p.getNombrePerfil() %>
                            </option>    
                            <%
                                        }
                            %>
                    </select>
                    <br>
                    <br>
                    <input class="btn btn-success" type="submit" value="Registrar" name="Enviar" />
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