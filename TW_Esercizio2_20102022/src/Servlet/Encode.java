package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Encode extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String word = request.getParameter("word");
        boolean isVocale = Boolean.valueOf(request.getParameter("vocale"));
     
        List<String> result = new ArrayList<String>();
        
        while ( result.size() < 5 ) {
            if ( session.getAttribute("winner") != null ) {
                return;
            }

            String shuffle = createAnagram(word);
            char chr = shuffle.toUpperCase().charAt(0);
            boolean ok = false;


            if ( chr == 'A' || chr == 'E' || chr == 'O' || chr == 'I' || chr == 'U' ) {
                ok = isVocale;
            } else {
                ok = !isVocale;
            }


            if ( ok ) {
                result.add(shuffle);
            }
          
        }

        if ( session.getAttribute("winner") != null ) {
            return;
        }

        response.getWriter().println(gson.toJson(result));
    }

    private String createAnagram(String word) {
        Character [] chars = new Character[word.length()];

        for ( int i = 0; i < word.length(); i++ ) {
            chars[i] = word.charAt(i);
        }
       

        Comparator<Character> comparator = (Character c1, Character c2) -> {
            double rand = Math.random();
            if ( rand > 0.5d ) return 1;
            if ( rand < 0.5d ) return -1;
            return 0;
        };

        Arrays.sort(chars, comparator);
        String result = "";

        for ( int i = 0; i < chars.length; i++ ) {
            result += chars[i].charValue();
        }

        return result;
    }
}