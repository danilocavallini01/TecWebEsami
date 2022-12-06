package Servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        char [] chars;

        chars = text.toCharArray();
        
        if ( isVocali == 1 ) {
            for ( int i = 0; i < 10; i++ ) {
                for ( int j = 0; j < chars.length ; j++ ) {
                    if ( chars[j] == 97 || chars[j] == 101 || chars[j] == 105 || chars[j] == 111 || chars[j] == 117 ) {
                        result[i] = new String();
                        result[i] += chars[i];
                        chars[j] = 0;
                        break;
                    }
                }

                for ( int j = 0; j < chars.length - 1; j++ ) {
                    if ( chars[j] != 0 ) {
                        result[i] += chars[j];
                        chars[j] = 0;
                        break;
                    }
                }
            }
        } else {
            for ( int i = 0; i < 10; i++ ) {
                for ( int j = 0; j < chars.length ; j++ ) {
                    if ( chars[j] != 97 && chars[j] != 101 && chars[j] != 105 && chars[j] != 111 && chars[j] != 117 ) {
                        result[i] = new String();
                        result[i] += chars[i];
                        chars[j] = 0;
                        break;
                    }
                }

                for ( int j = 0; j < chars.length - 1; j++ ) {
                    if ( chars[j] != 0 ) {
                        result[i] += chars[j];
                        chars[j] = 0;
                        break;
                    }
                }
            }
        }

        response.getWriter().println(gson.toJson(result));
    }
}