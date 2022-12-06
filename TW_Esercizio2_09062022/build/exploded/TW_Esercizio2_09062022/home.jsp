<%@ page session="true"%>
<%@ page import="java.util.Date"%>
<html>
<head>
  <title>Anagramma</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Anagramma  </h1>
  <label for="text">
    parola
  </label>
  <input id="text" type="text" onkeyup="checkWord()"/>
  
  <br>
  <p id="result"></p>
</div>

</body>
</html>