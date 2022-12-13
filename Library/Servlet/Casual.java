package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Result;

public class Casual extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("sessioni") == null ) {
            Map<HttpSession,Integer> sessioni = new HashMap<HttpSession,Integer>();
            this.getServletContext().setAttribute("sessioni", sessioni );
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String text = (String)request.getSession(false).getAttribute("result");
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
        
        response.getWriter().println(gson.toJson(new Result(result)));
    }
}