package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Capitolo;



public class Content extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("capitoli") == null) {
            List<Capitolo> capitoli = new ArrayList<Capitolo>();

            for ( int i = 0; i < 10; i++ ) {
                capitoli.add(new Capitolo(i + 1));
            }

            this.getServletContext().setAttribute("capitoli", capitoli);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String content = request.getParameter("content");
        int cap = Integer.parseInt(request.getParameter("id"));
        int version = Integer.parseInt(request.getParameter("version"));

        synchronized(this) {
            List<Capitolo> capitoli = (List<Capitolo>)this.getServletContext().getAttribute("capitoli");
    
            Capitolo capitolo = capitoli.get(cap - 1);
            if ( version < capitolo.getVersion() ) {
                response.getWriter().println(gson.toJson("errore"));
            } else {
                capitolo.setContent(content);
                capitolo.addVersion();
                response.getWriter().println(gson.toJson(capitolo));
            }
        }
        return;
    }
}