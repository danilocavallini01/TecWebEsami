<%@ page session="true"%>
<%@ page import="java.util.Date"%>

<html>
<head>
  <title>Quadrato Magico 4x4</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Quadrato Magico 4x4  </h1>
  
  <div>
    <label id="text" for="content">Contenuto del quadrato magico (separato da ,):</label>
    <textarea id="content" rows="3" onkeyup="checkMatrix()"></textarea>
  </div>
  <br>
  <br>

  <p id="result"></p>
</div>

</body>
</html>