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
<div class="center">
  <form class="center" method="POST" action="Register">
    <h1> Register </h1>
    <label for="start">
      Username
      <input id="user" name="username"type="text" size="30" />
    </label>
    
    <label for="end">
      Password
      <input id="psw" name="password" type="password" size="30" />
    </label>

    <label for="group">
      Scegli un gruppo
      <input id="group" name="group" type="text" size="30" />
    </label>
    
    <input type="submit" value="Register"/>
  </form>

 
  <form class="center" method="POST" action="Login">
    <% if ( session.getAttribute("error") != null ) { 
    %>
      <p><%=(String)session.getAttribute("error")%> </p>
    <% } %>
    <% if ( session.getAttribute("currentUser") != null ) { 
      User user = (User)session.getAttribute("currentUser");
    %>
      <h2> Ciao <%=user.getUsername()%> del gruppo <%=user.getGroupId()%></h2>
    <% } else { %> 

    <h1> Log in </h1>
    <label for="start">
      Username
      <input id="user" name="username"type="text" size="30" />
    </label>
    
    <label for="end">
      Password
      <input id="psw" name="password" type="password" size="30" />
    </label>
    
    <input type="submit" value="Login"/>
  </form>
  <% } %>
</div>
</body>
</html>