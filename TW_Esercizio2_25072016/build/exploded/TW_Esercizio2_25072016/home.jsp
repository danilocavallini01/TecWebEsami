<%@ page session="true"%>

<html>
<head>
  <title>Pokemon Go Finder</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Pokemon Go Finder  </h1>
  
  <label for="start">
    x
    <input id="x" name="x" type="text" size="30" />
  </label>
  
  <label for="end">
    y
    <input id="y" name="y" type="text" size="30" />
  </label>

  <p id="result"></p>
  <input type="button" value="Invia Dati" onclick="sendPosition()"/>
</div>

</body>
</html>