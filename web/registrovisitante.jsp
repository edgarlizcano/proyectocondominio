<%-- 
    Document   : registrousuario
    Created on : 22-mar-2018, 20:54:50
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
                <h1>Registro de Visitante</h1>
                <form action="ControladorVisitantes" method="POST">
                    <input type="hidden" name="accion" value="RegistroVisitante" />
                    <br>
                    <label>Introduzca los Nombres del Visitante</label>
                    <br>
                    <input type="text" name="nombres" value="" size="30" />
                    <br>
                    <label>Introduzca los Apellidos del Visitante</label>
                    <br>
                    <input type="text" name="apellidos" value="" size="30" />
                    <br>
                    <label>Introduzca el Número de Cédula del Visitante</label>
                    <br>
                    <input type="text" name="cedula" value="" size="30" />
                    <br>
                    <br>
                    <input type="submit" class="btn btn-success" value="Registrar" name="Enviar" />
                    <br>
                    <br>
                </form>
            </div> 
            
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>