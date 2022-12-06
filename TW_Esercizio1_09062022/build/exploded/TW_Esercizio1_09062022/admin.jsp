<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<html>
<head>
  <title>Admin</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>

<div class="center">
  <h1> Admin </h1>
  <p> Numero totale di operazioni : <%=(int)application.getAttribute("totalOperation")%></p>
</div>

</body>
</html>