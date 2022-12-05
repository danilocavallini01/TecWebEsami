<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.GroupDb"%>
<%@ page import="Beans.Prodotto"%>
<%@ page import="java.util.*"%>


<%

  Group group = (Group)session.getAttribute("currentGroup");
  User user = (User)session.getAttribute("currentUser");


  Prodotto acquistato = group.getAcquisto();
  Prodotto prodotto = group.getProdotto();

  if (acquistato == null) {
      group.setAcquisto(new Prodotto(prodotto.getNome(), 0));
      acquistato = group.getAcquisto();
  }
%>

<html>
<head>
  <title>Catalogo Prodotti</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Prodotto </h1>
  <table class="formdata">
    <tr>
      <th>
        Nome
      </th>
      <th>
        Quantita
      </th>
    </tr>
    <form method="POST" action="Finalize">
        <td>
            <%=prodotto.getNome()%>
        </td>
        <td>
            <%=prodotto.getQuantita()%>
        </td>
        <td class="add-button">
            <input type="submit" name="add" value="+" />
        </td>
    </form>
  </table>

  <h1> Prodotto aquistato dal gruppo </h1>
  <table class="formdata">
    <tr>
      <th>
        Nome
      </th>
      <th>
        Quantita
      </th>
    </tr>
   <tr>
        <td>
            <%=acquistato.getNome()%>
        </td>
        <td>
            <%=acquistato.getQuantita()%>
        </td>
    </tr>
  </table>
  <form method="POST" action="Finalize">
    <input type="submit" name="concludi" value="concludi"></input>
  </form>
</div>

</body>
</html>