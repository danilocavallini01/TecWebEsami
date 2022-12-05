<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Group"%>
<%@ page import="java.util.*"%>

<html>
<head>
  <title>Login</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div>
<div class="center">
  <form class="center" method="POST" action="Register">
    <h1> Register </h1>
    <label for="start">
      Email
      <input id="user" name="username"type="text" size="30" />
    </label>
    
    <label for="end">
      Password
      <input id="psw" name="password" type="password" size="30" />
    </label>

    <input type="submit" value="Register"/>
  </form>


  <form class="center" method="POST" action="Login">
    <h1> Log in </h1>
    <label for="start">
      Email
      <input id="user" name="username" type="text" size="30" />
    </label>
    
    <label for="end">
      Password
      <input id="psw" name="password" type="password" size="30" />
    </label>

    <% if ( session.getAttribute("error") != null ) { %>
      <p class="error"><%=(String)session.getAttribute("error")%> </p>
    <% } %>
    <% 
      if ( session.getAttribute("currentUser") != null ) { 
      User user = (User)session.getAttribute("currentUser");
    %>
      <p> Ciao <%=user.getUsername()%> del gruppo <%=user.getGroupId()%></p>
    <% }
      if ( session.getAttribute("conclude") != null) {
     %>
    <p> Il tuo gruppo ha chiuso l'ordine </p>
     <% } %>
    <input type="submit" value="Login"/>
  </form>
</div>
</body>
</html>