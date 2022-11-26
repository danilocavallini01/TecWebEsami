package Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Integral extends HttpServlet
{
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("startDate",new Date());
        session.setAttribute("operateCount",0);
        session.setAttribute("valid", true);
        session.setMaxInactiveInterval(600);
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        float start = Float.parseFloat(request.getParameter("start"));
        float end  = Float.parseFloat(request.getParameter("end"));
        
        float result = ((end*end)/2f) - ((start*start)/2f);

        synchronized(this) {
            HttpSession session = request.getSession();
        
            Date sessionStart = (Date)session.getAttribute("startDate");
            int operateCount = (int)session.getAttribute("operateCount");
            session.setAttribute("operateCount", operateCount + 1);
            System.out.println(operateCount);
            if ( operateCount + 1 >= 20) {
                session.setAttribute("valid", false);
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            }
        
        }
        
        response.getWriter().println(gson.toJson(result));
    }
}