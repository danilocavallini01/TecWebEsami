<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.Tavolo"%>
<%@ page import="Beans.Drink"%>
<%
  List<Drink> drinks = ( List<Drink>)application.getAttribute("drinks");
  Tavolo table = (Tavolo)session.getAttribute("table");
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
  <h1> Ordina </h1>
  <table class="formdata">
    <tr>
      <th>
        Drink
      </th>
      <th>
        Prezzo
      </th>
      <th>
        Ordina
      </th>
    </tr>
    <%
      for ( Drink d : drinks) {
    %>
      <tr>
        <td>
          <%=d.getName()%>
        </td>
        <td>
          <%=d.getPrice()%>
        </td>
        <td class="add-button">
          <form method="POST" action="OrderDrink">
            <input type="hidden" name="name" value=<%=d.getName()%>>
            <input type="submit" name="cancel" value="Scegli" />
          </form>
        </td>
      </tr>
    <%
      }
    %>
  </table>
  <h1> Drink del tuo gruppo</h1>
  <table class="formdata">
    <tr>
      <th>
        Drink
      </th>
      <th>
        Prezzo
      </th>
      <th>
        Consegnato
      </th>
    </tr>
    <%
      for ( Drink d : table.getDrinks() ) {
    %>
      <tr>
        <td>
          <%=d.getName()%>
        </td>
        <td>
          <%=d.getPrice()%>
        </td>
        <td>
          <%=d.isDelivered()%>
        </td>
      </tr>
    <%
      }
    %>
  </table>
</div>

</body>
</html>