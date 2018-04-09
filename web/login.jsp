<%-- 
    Document   : Logins
    Created on : 12-feb-2018, 22:01:48
    Author     : Edgar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
  <body>    
    <div class="container-fluid">
      <div class="row">
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
              <h1 class="h2">Condominio El Cardenal</h1>
            </div>
            <div class="container-fluid">
                <h1 class="h2">Login</h1>
                <form method="POST" action="LoginServlet">
                    <label>Nombre de Usuario</label><br>
                    <input type="text" name="usuario" value="" /><br>
                    <label>Contraseña</label><br>
                    <input type="password" name="passw" value="" /><br>
                    <p><a href="#">Olvido su clave?</a></p>
                    <input type="submit" value="Entrar" name="Enviar" />
                </form>
                <%
                    String men = (String) request.getSession().getAttribute("men");
                    if(men!= null){
                        %>
                            <h1><%=men%></h1>
                        <%
                        request.getSession().removeAttribute("men");
                    }
                %>
            </div> 
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

