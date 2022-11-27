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
<body>
<div class="center">
  <h1> To Upper Case File </h1>
  <label for="name">
    Nome del file
  </label>
  <input id="name" type="text" />
  
  <div>
    <label for="content">Contenuto del file:</label>
    <textarea id="content" rows="3"></textarea>
  </div>
  <br>
  <br>
  <input type="submit" onclick="toUpperCaseFile()"/>

  <p id="result"></p>
</div>

</body>
</html>