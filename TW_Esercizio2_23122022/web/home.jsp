<%@ page session="true"%>
<%@ page import="java.util.Date"%>
<html>
<head>
  <title>Matrice 8x8</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Matrice 8x8  </h1>
  <label for="num">
    Numero di matrici n x n
  </label>
  <input id="num" type="text" />
  
  <div>
    <label id="text" for="content">Contenuto della 1 matrice (separato da ,):</label>
    <textarea id="content" rows="3" onkeyup="checkMatrix()"></textarea>
  </div>
  <br>
  <br>

  <p id="result"></p>
</div>

</body>
</html>