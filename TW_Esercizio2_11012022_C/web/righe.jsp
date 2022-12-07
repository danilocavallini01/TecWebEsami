<%@ page session="true"%>
<%@ page import="Beans.Result"%>
<%@ page import="com.google.gson.Gson"%>

<%
  String matrixString = request.getParameter("matrixs");
  Integer[] matrix = new Gson().fromJson(matrixString, Integer[].class);
  int sumRiga = matrix[0] + matrix[1] + matrix[2] + matrix[3];
  int sum = 0;

  for ( int i = 0; i < 4; i++ ) {
    for ( int j = 0; j < 4; j++) {
        sum += matrix[i*4 + j];
    }

    if ( sum != sumRiga ) {
        response.getWriter().println(new Gson().toJson(new Result(false)));
        return;
    }
    sum = 0;
  }

  response.getWriter().println(new Gson().toJson(new Result(true, sumRiga)));
  return;
%>