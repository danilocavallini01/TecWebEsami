<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.ArticoloAsta"%>
<%
  List<ArticoloAsta> asta = ( List<ArticoloAsta>)application.getAttribute("asta");
  ArticoloAsta selected = (ArticoloAsta)session.getAttribute("selected");
%>

<html>
<head>
  <title>Asta</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Asta </h1>
  <table class="formdata">
    <tr>
      <th>
        Nome
      </th>
      <th>
        Prezzo
      </th>
      <th>
        Scadenza
      </th>
    </tr>
    <%
      for ( ArticoloAsta a : asta) {
    %>
      <tr>
        <td>
          <%=a.getName()%>
        </td>
        <td>
          <%=a.getPrice()%>
        </td>
        <td>
          <%=a.getExprire().getTime() - new Date().getTime() %> millisecondi
        </td>
        <td class="add-button">
          <form method="POST" action="Choose">
            <input type="hidden" name="name" value=<%=a.getName()%>>
            <input type="submit" name="cancel" value="Scegli" />
          </form>
        </td>
      </tr>
    <%
      }
    %>
  </table>

  <% if ( selected != null ) { %>
    <div class="center">
      <p> Prezzo piu alto: <%=selected.getPrice()%></p>
      <label for="price">
        Prezzo
        <input id="price" name="price" type="text" size="30" onchange="checkPrice(this)"/>
      </label>
      <p id="result"></p>
    </div>
  <% } %>
  
</div>

</body>
</html>