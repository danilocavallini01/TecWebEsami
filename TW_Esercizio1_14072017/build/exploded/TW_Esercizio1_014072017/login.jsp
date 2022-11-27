<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>

<html>
<head>
  <title>Login  </title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<form class="center" method="POST" action="Login">
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
  <% 
  if ( session.getAttribute("currentUser") != null ) {
    User user = (User)session.getAttribute("currentUser");
    int success = user.getSuccess();
    System.out.println(success);
    if (success == 1) {
      %>
        <p>il tuo ordine e' stato completato, aspetta gli altri</p>
      <%
    }
    if (success == 2) {
      %>
        <p>il tuo ordine e' stato completato e anche quello degli altri</p>
      <%
    }
    if (success == 3) {
      %>
        <p>il tuo ordine e' stato completato dall'admin</p>
      <%
    }
    if (success == 4) {
      %>
        <p>il tuo ordine e' stato rimosso dall'admin</p>
      <%
    }
    user.setSuccess(0);
    session.invalidate();
  }
  %>
</form>
</body>
</html>