<%@ page session="true"%>

<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.*"%> 

<%
  synchronized(this) {
    if ( session.getAttribute("richieste") != null ) {
      session.setAttribute("richieste",  (int)session.getAttribute("richieste") + 1);
    } else {
      session.setAttribute("richieste",  1);
    }
  
    if ( application.getAttribute("sessioni") == null ) {
      Map<HttpSession,Integer> sessioni = new HashMap<HttpSession,Integer>();
      application.setAttribute("sessioni", sessioni );
    } else {
      Map<HttpSession,Integer> sessioni = ( Map<HttpSession,Integer>)application.getAttribute("sessioni");
      sessioni.putIfAbsent(session, (int)session.getAttribute("richieste"));
      sessioni.replace(session, (int)session.getAttribute("richieste"));
    }  
  }
  
  String text = request.getParameter("text");
  Random random = new Random();
  int randomChr = random.nextInt(25);
  int bannedChr1 = randomChr + 65;
  int bannedChr2 = randomChr + 97;

  String result = "";
  for ( int i = 0; i < text.length() - 1; i++ ) {
      if ( text.codePointAt(i) != bannedChr1 && text.codePointAt(i) != bannedChr2 ) {
          result += text.charAt(i);
      } 
  }
  
  session.setAttribute("result", result );
  this.getServletContext().getRequestDispatcher("/Casual").forward(request,response);
%>