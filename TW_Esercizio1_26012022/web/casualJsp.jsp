<%@ page session="true"%>

<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%> 
<%@ page import="Beans.Result"%> 

<%  
  String text = (String)session.getAttribute("result");

  Gson gson = new Gson();
  Random random = new Random();
  int randomChr = random.nextInt(25);
  int bannedChr1 = randomChr + 65;
  int bannedChr2 = randomChr + 97;

  String result = "";
  int count = 0;
  
  for ( int i = 0; i < text.length() - 1; i++ ) {

    if ( text.codePointAt(i) != bannedChr1 && text.codePointAt(i) != bannedChr2 ) {
      result += text.charAt(i);
      
    }

    if ( text.codePointAt(i) >= 65 && text.codePointAt(i) <= 90 ) {
      count++;
    }
    
  }
  
  response.getWriter().println(gson.toJson(new Result(result,count)));
%>