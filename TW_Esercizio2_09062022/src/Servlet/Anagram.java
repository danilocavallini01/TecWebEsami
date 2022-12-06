package Servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Anagram extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String text = request.getParameter("text");
        int isVocali = Integer.valueOf(request.getParameter("vocali"));
        String [] result = new String[10];
        Character [] chars = new Character[text.length()];

        for ( int i = 0; i < text.length(); i++ ) {
            chars[i] = text.charAt(i);
        }
       
        
        if ( isVocali == 1 ) {
            for ( int i = 0; i < 10; i++ ) {
                result[i] = new String(); 
                Comparator<Character> comparator = (Character c1, Character c2) -> {
                    double rand = Math.random();
                    if ( rand > 0.5d ) return 1;
                    if ( rand < 0.5d ) return -1;
                    return 0;
                };

                Arrays.sort(chars, comparator);
                
                for ( int j = 1; j < chars.length ; j++ ) {
                    if ( chars[j] == 97 || chars[j] == 101 || chars[j] == 105 || chars[j] == 111 || chars[j] == 117 ) {
                        char c = chars[0];
                        chars[0] = chars[j];
                        chars[j] = c;
                        break;
                    }
                }

                System.out.println(Arrays.toString(chars));
                for ( int x = 0; x < text.length(); x++ ) {
                    result[i] += chars[x];
                    chars[x] = text.charAt(x);
                }
            }
        } else {
            for ( int i = 0; i < 10; i++ ) {
                result[i] = new String(); 
                Comparator<Character> comparator = (Character c1, Character c2) -> {
                    double rand = Math.random();
                    if ( rand > 0.5d ) return 1;
                    if ( rand < 0.5d ) return -1;
                    return 0;
                };

                Arrays.sort(chars, comparator);
                
                for ( int j = 1; j < chars.length ; j++ ) {
                    if ( chars[j] != 97 && chars[j] != 101 && chars[j] != 105 && chars[j] != 111 && chars[j] != 117 ) {
                        char c = chars[0];
                        chars[0] = chars[j];
                        chars[j] = c;
                        break;
                    }
                }

                System.out.println(Arrays.toString(chars));
                for ( int x = 0; x < text.length(); x++ ) {
                    result[i] += chars[x];
                    chars[x] = text.charAt(x);
                }
            }
        }

        response.getWriter().println(gson.toJson(result));
    }
}