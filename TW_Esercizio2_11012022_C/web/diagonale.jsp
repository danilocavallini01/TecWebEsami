<%@ page session="true"%>
<%@ page import="Beans.Result"%>
<%@ page import="com.google.gson.Gson"%>

<%
  String matrixString = request.getParameter("matrixs");
  Integer[] matrix = new Gson().fromJson(matrixString, Integer[].class);

  int diagonale1 = matrix[0] + matrix[5] + matrix[10] + matrix[15];
  int diagonale2 = matrix[3] + matrix[6] + matrix[9] + matrix[12];

  if ( diagonale1 != diagonale2) {
    response.getWriter().println(new Gson().toJson(new Result(false)));
  } else {
    response.getWriter().println(new Gson().toJson(new Result(true, diagonale2)));
  }
 
  return;
%>