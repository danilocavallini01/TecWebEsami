<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.Tavolo"%>
<%@ page import="Beans.Drink"%>
<%
  Tavolo table = (Tavolo)session.getAttribute("table");
  List<Drink> ordered = (List<Drink>)session.getAttribute("drinks");
  int total = 0;
  for ( Drink d : ordered ) {
    total += d.getPrice();
  }
%>

<html>
<head>
  <title>Ordina</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Review </h1>
  <p> Costo Totale del tavolo <%= table.getTotalPrice() %></p>
  <p> Costo Totale dei tuoi drink <%= total %></p>
</div>

</body>
</html>