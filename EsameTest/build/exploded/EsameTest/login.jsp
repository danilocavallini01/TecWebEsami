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

    <% if ( session.getAttribute("error") != null ) { %>
      <p class="error"><%=(String)session.getAttribute("error")%> </p>
    <% } %>
    <% 
      if ( session.getAttribute("currentUser") != null ) { 
      User user = (User)session.getAttribute("currentUser");
    %>
      <p> Ciao <%=user.getUsername()%> del gruppo <%=user.getGroupId()%></p>
    <% } %>

    <% if ( session.getAttribute("currentGroup") != null ) {
      Group group = (Group)session.getAttribute("currentGroup");
      User user = (User)session.getAttribute("currentUser");
     
      session.removeAttribute("currentGroup");
      session.removeAttribute("currentUser");
      String result = "";
      if ( group.getSuccess() == 1 ) {
        result = "Hai concluso con successo il tuo acquisto";
      } else if ( group.getSuccess() == 2 ) {
        group.setSuccess(0);
        result = "Hai concluso con successo il tuo acquisto, anche gli altri del tuo gruppo";
      } else if ( group.getSuccess() == 3 ) {
        group.setSuccess(0);
        result = "Admin ha concluso il tuo acquisto";
      } else if ( group.getSuccess() == 4 ) {
        result = "Admin ha bloccato il tuo acquisto";
        group.setSuccess(0);
      } 

    %>
      <p><%=result%></p>
    <% } %>
    <input type="submit" value="Login"/>
  </form>

</body>
</html>