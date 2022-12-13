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
import Beans.ForbiddenThread;

public class Choose extends HttpServlet {
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

            ForbiddenThread fb = new ForbiddenThread(capitoli);
            fb.start();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Capitolo> capitoli = (List<Capitolo>)this.getServletContext().getAttribute("capitoli");
        int cap = Integer.parseInt(request.getParameter("cap"));

        Capitolo capitolo = capitoli.get(cap - 1);
        response.getWriter().println(gson.toJson(capitolo));
    }
}