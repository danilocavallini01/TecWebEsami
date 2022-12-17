<%@ page session="true"%>
<%@ page import="java.util.Date"%>

<html>
<head>
  <title>Tennisti</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Tennisti </h1>
  
  <div>
    <label id="text" for="name">Cognome del tennista:</label>
    <input id="name" type="text" ></input>
    <input type="button" value="Cerca" onclick="checkSurname()"/>
  </div>
  <br>
  <br>

  <p id="result"></p>
</div>

</body>
</html>