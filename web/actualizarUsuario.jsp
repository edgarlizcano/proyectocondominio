<%-- 
    Document   : actualizarUsuario
    Created on : 23-mar-2018, 15:50:06
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
                <h1>Registro de Usuario</h1>
                <form action="ControladorUsuarios" method="POST">
                    <input type="hidden" name="accion" value="ActualizarUsuario" />
                    <label>Introduzca el Nombre de Usuario<label/>
                    <br>
                    <input type="text" name="nombreUsuario" value="" size="15" />
                    <br>
                    <label>Introduzca su Correo Electrónico<label/>
                    <br>
                    <input type="text" name="email" value="" size="15" />
                    <br>
                    <label>Escriba su Contraseña Actual<label/>
                    <br>
                    <input type="password" name="oldPass" value="" size="15" />
                    <br>
                    <label>Escriba su Nueva Contraseña<label/>
                    <br>
                    <input type="password" name="newPass" value="" size="15" />
                    <br>
                    <label>Repita su Nueva Contraseña<label/>
                    <br>
                    <input type="password" name="newPass" value="" size="15" />
                    <br>
                    <input type="submit" value="Registrar" name="Enviar" />
                </form>
            </div> 
            
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

