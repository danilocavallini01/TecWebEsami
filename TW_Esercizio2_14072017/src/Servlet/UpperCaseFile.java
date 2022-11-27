package Servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class UpperCaseFile extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String filename = request.getParameter("name");
        String content = request.getParameter("content");
        int capsChar = 0;
        synchronized (this) {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            try {
                for ( int i = 0; i < content.length(); i++) {
                    char c = content.charAt(i);
                    if ( c >= 97 || c <= 122) {
                        capsChar++;
                        c -= 32;
                    }   
                    fw.write(c);
                }
            } catch ( IOException e ) {
                throw new ServletException(e.getMessage());
            }
            fw.close();
        }

        response.getWriter().println(gson.toJson(capsChar));
    }
}