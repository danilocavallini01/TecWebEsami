<%@ page session="true"%>
<html>
<head>
  <title>Redirect and Read</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<form class="center" method="post" action="Redirect">
  <h1> Redirect and Read </h1>
  
  <label for="redirectLeft">
    Numero di Redirezioni
  </label>
  <input id="redirectLeft" name="redirectLeft" type="text" />

  <br>
  <input type="submit" />

    <% if ( session.getAttribute("result") != null ) { %>
      <p> Numero totale di numeri nei file </p>
      <p id="result"><%=(int)session.getAttribute("result")%></p>
    <% } %>
</form>

</body>
</html>