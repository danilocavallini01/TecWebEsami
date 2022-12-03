package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Cart;
import Beans.Group;
import Beans.User;

public class Finalize extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String concludi = request.getParameter("concludi");

        HttpSession session = request.getSession(false);
        
        if ( session == null || concludi == null ) {
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        User user = (User)session.getAttribute("currentUser");
        Group group = (Group)session.getAttribute("group");

        if ( user.getSuccess() != 0 ) {
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        user.setWantToBuy(true);

        for ( User gUser : group.getUsers() ) {
            if ( !gUser.isWantToBuy() ) {
                user.setSuccess(1);
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        }
        
        group.setCart(new Cart());
        
        for ( User gUser : group.getUsers() ) {
            gUser.setWantToBuy(false);
            gUser.setSuccess(2);
        }

        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
