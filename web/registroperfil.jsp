<%-- 
    Document   : registroperfil
    Created on : 23-mar-2018, 12:41:43
    Author     : Edgar
--%>

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
                <h1>Registro de Perfil</h1>
                <form action="ControladorPerfiles" method="POST">
                    <input type="hidden" name="accion" value="RegistroPerfil" />
                    <label>Introduzca el Nombre del Perfil</label>
                    <br>
                    <input type="text" name="nombrePerfil" value="" size="30" />
                    <br>
                    <br>
                    <input class="btn btn-success" type="submit" value="Registrar" name="Enviar" />
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