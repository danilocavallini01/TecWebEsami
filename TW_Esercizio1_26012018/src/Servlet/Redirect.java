package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.BeanCounter;

public class Redirect extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        if (session.getAttribute("redirectLeft") == null) {
            List<String> files = new ArrayList<String>();

            session.setAttribute("files", files);
            session.setAttribute("redirectLeft", Integer.parseInt(request.getParameter("redirectLeft")));

            this.getServletContext().getRequestDispatcher("/string.jsp").forward(request, response);
            return;
        } else {

            int redirectLeft = (int) session.getAttribute("redirectLeft") - 1;

            session.setAttribute("redirectLeft", redirectLeft);

            List<String> files = (List<String>) session.getAttribute("files");
            files.add(request.getParameter("file"));
            session.setAttribute("files", files);

            if (redirectLeft <= 0) {
                BeanCounter[] counters = new BeanCounter[files.size()];
                int result = 0;

                for (int i = 0; i < files.size(); i++) {
                    counters[i] = new BeanCounter(files.get(i));
                }

                for (BeanCounter counter : counters) {
                    counter.start();
                }

                try {
                    for (BeanCounter counter : counters) {
                        counter.join();
                        result += counter.getCounter();
                    }
                } catch (InterruptedException e) {
                    throw new ServletException(e.getMessage());
                }

                session.setAttribute("result", result);
                session.removeAttribute("files");
                session.removeAttribute("redirectLeft");

                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/string.jsp").forward(request, response);
            }
            return;
        }

    }

}