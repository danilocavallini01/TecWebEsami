<%@ page session="true"%>
<%@ page import="java.util.Date"%>
<html>
<head>
  <title>Files View</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<%
  if ( (boolean)session.getAttribute("valid") ) {
%>
<body>
<div class="center">
  <h1> Integrale di f(x) = x </h1>
  <label for="start">
    Estremo alto d'integrazione
    <input id="start" type="number" />
  </label>
  
  <label for="end">
    Estremo alto d'integrazione
    <input id="end" type="number" />
  </label>
  
  <input type="submit" onclick="calculateIntegral()"/>

  <p id="result"></p>
</div>

</body>
<% } else { %>
  <p> Hai eseguito troppe operazioni o la sessione è scaduta</p>
<% } %>
</html>