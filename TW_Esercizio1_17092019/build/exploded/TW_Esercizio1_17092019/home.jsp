<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.Prenotazioni"%>
<%@ page import="Beans.Albergo"%>

<% if ( request.getParameter("finalizza") != null ) {
    Prenotazioni miaPrenotazione = (Prenotazioni)session.getAttribute("prenotazione");
    miaPrenotazione.setFinalized(true);
    Map<Integer,Albergo> alberghi = (Map<Integer,Albergo>)application.getAttribute("alberghi");
    Albergo albergo = alberghi.get(miaPrenotazione.getId());
    albergo.setCamereTotali(albergo.getCamereTotali());
    session.removeAttribute("prenotazione");
  }
%>
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
  <div class="center">
    <h1> Prenota </h1>
    <label for="id">
      ID albergo
      <input id="id" name="id" type="text" size="30" onchange="prenota()"/>
    </label>
    
    <label for="check-in">
      Data check-in
      <input id="check-in" name="check-in" type="text" size="30" onchange="prenota()"/>
    </label>

    <label for="check-out">
      Data check-out
      <input id="check-out" name="check-out" type="text" size="30" onchange="prenota()" />
    </label>
    
    <p id="result"></p>
    <input id="idAlberghi" type="hidden" value=<%=(int)session.getAttribute("numAlberghi")%> />
  </div>

  <form class="center" method="GET" action="./home.jsp">
    <input name="finalizza" type="submit" value="finalizza"/>
  </form>
 
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
  </form>
</div>
</body>
</html>