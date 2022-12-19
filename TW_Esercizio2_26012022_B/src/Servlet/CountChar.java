package Servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CountChar extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if (this.getServletContext().getAttribute("file") == null) {
            File f = new File("./cruciverba.txt");
            int length = 0;
            FileReader fr = null;

            try {
                fr = new FileReader(f);
                while (fr.read() != -1) {
                    length++;
                }
                fr.close();
            } catch (FileNotFoundException e) {
                throw new ServletException(e.getMessage());
            } catch (IOException e) {
                throw new ServletException(e.getMessage());
            }
            this.getServletContext().setAttribute("length", length);
            this.getServletContext().setAttribute("file", f);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        File f = (File) this.getServletContext().getAttribute("file");
        int length = (int) this.getServletContext().getAttribute("length");

        char chr = request.getParameter("char").charAt(0);
        int chrInt = chr;
        boolean isFirstHalf = Boolean.parseBoolean(request.getParameter("firstHalf"));
        
        FileReader fr = new FileReader(f);
        int count = 0;

        if ( isFirstHalf ) {
            for (int i = 0; i < length / 2; i++) {
                int read = fr.read();
                if ( read == chrInt ) {
                    count++;
                }
            }
        } else {
            for (int i = 0; i < length / 2; i++) {
                fr.read();
            }
            for (int i = 0; i < length / 2; i++) {
                int read = fr.read();
                if ( read == chrInt ) {
                    count++;
                }
            }
        }
        

        fr.close();

        response.getWriter().println(gson.toJson(count));
        return;
    }
}