<%@ page session="true"%>
<html>
<head>
  <title>Matrix Diff</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Matrix Diff </h1>
   Matrice 2x2
  
  <label for="matrixA">
    Valori della matrice A separati da ","
  </label>
  <input id="matrixA" type="text" onchange="checkIfValid()" />

  <label for="matrixB">
    Valori della matrice B separati da ","
  </label>
  <input id="matrixB" type="text" onchange="checkIfValid()" />

  <br>
  <br>
  <input type="submit" onclick="matrixDiff(false)" class="hide"/>
  <input type="submit" onclick="matrixDiff(true)" value="invia concorrenziale" class="hide"/>

  <p id="result"></p>
</div>

<form class="center" action="./AdminLogin" method="post">
  <h1> Admin Login </h1>
  
  <label for="username">
    Username
  </label>
  <input id="username" name="username" type="text" onchange="checkIfValid()" />

  <label for="password">
    Password
  </label>
  <input id="password" name="password" type="text" onchange="checkIfValid()" />

  <br>
  <br>
  <input type="submit"/>

  <p id="result"></p>
</div>

</body>
</html>