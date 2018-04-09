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
                <h1>Registro de Usuario</h1>
                <form action="ControladorUsuarios" method="POST">
                    <input type="hidden" name="accion" value="RegistroUsuario" />
                    <br>
                    <label>Introduzca el Nombre de Usuario</label>
                    <br>
                    <input type="text" name="nombreUsuario" value="" size="30" required/>
                    <br>
                    <label>Introduzca su Correo Electrónico</label>
                    <br>
                    <input type="text" name="email" value="" size="30" required/>
                    <br>
                    <label>Introduzca su Número de Cédula</label>
                    <br>
                    <input type="text" name="cedula" value="" size="30" required/>
                    <br>
                    
                    <label>Introduzca Nombres</label>
                    <br>
                    <input type="text" name="nombres" value="" size="30" required/>
                    <br>
                    
                    <label>Introduzca Apellidos</label>
                    <br>
                    <input type="text" name="apellidos" value="" size="30" required/>
                    <br>
                    
                    <label>Introduzca su número de Teléfono Local</label>
                    <br>
                    <input type="text" name="tlfLocal" value="" size="30" required/>
                    <br>
                    
                    <label>Introduzca su número de Teléfono Celular</label>
                    <br>
                    <input type="text" name="tlfCelular" value="" size="30" required/>
                    <br>
                    
                    <label>Introduzca su Fecha de Nacimiento</label>
                    <br>
                    <input type="date" name="fechaNac" value="" required/>
                    <br>
                    <label>Introduzca su Contraseña</label>
                    <br>
                    <input type="password" name="clave" value="" size="15" required/>
                    <br>
                    <label>Repita su Contraseña</label>
                    <br>
                    <input type="password" name="clave" value="" size="15" required/>
                    <br>
                    <label>Seleccione el Número de Casa</label>
                    <br>
                    <select name="numeroCasa">
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