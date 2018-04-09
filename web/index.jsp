<%-- 
    Document   : index
    Created on : 18-mar-2018, 22:37:51
    Author     : Edgar
--%>

<%@page import="modelo.Usuarios"%>
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
                <h1 class="h2">Bienvenidos - Página Principal</h1>
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
