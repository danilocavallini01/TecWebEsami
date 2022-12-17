<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>
<%@ page import="Beans.Articolo"%>
<%
  Articolo a = (Articolo)session.getAttribute("articolo");
%>
<html>
<head>
  <title>Articolo</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div>
<div class="center">
  <div class="center">
    <label for="start">
      Nome dell articolo
      <input id="articolo" name="articolo" type="text" size="30" onkeyup="addArticolo(this)"/>
    </label>
  </div>

  <form class="center" method="post" action="ModArticolo">
    <label id="text" for="content">Contenuto dell'articolo</label>
    
    <% if ( a != null ) { %>
      <input type="text" name="content" id="content" value="<%=a.getContent()%>" />
      <input type="submit" name="release" value="Rilascia permessi" />
      <input type="submit" name="write" value="Salva Modifiche" />
      <input id="name" type="hidden" name="name" value=<%=a.getName()%> />
    <% } else { %>
      <input type="text" name="content" id="content" ></input>
      <input type="submit" name="get" value="Richiedi permessi" />
      <input id="name" type="hidden" name="name" value=""/>
    <% } %>
  </form>

  <form class="center" method="POST" action="AdminLogin">
    <h1> Log in </h1>
    <label for="start">
      Email
      <input id="user" name="username" type="text" size="30" />
    </label>
    
    <label for="end">
      Password
      <input id="psw" name="password" type="password" size="30" />
    </label>
  
    <input type="submit" value="Login"/>
  </form>
</div>
</body>
</html>