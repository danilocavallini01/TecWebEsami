<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>
<%
  Articolo a = session.getAttribute("articolo");
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
      <input id="articolo" name="articolo" type="text" size="64" />
    </label>
    <input type="button" value="Seleziona" onclick="addArticolo()"/>
  </div>

  <form class="center" method="post" action="ModArticolo">
    <label id="text" for="content">Contenuto dell'articolo</label>
    <input type="text" name="content" id="content" ></input>

    <input type="submit" name="write" value="Salva Modifiche" />
    <input type="submit" name="get" value="Richiedi permessi" />
    <% if ( a != null ) { %>
      <input type="submit" name="release" value="Rilascia permessi" />
      <input id="name" type="hidden" name="name" value=<%=a.getName()%>/>
    <% } else { %>
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