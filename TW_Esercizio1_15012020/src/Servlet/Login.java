package Servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.User;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        Map<String, User> users = new HashMap<String, User>();
        Map<Integer, Group> groups = new HashMap<Integer, Group>();

        Group group1 = new Group(1);
        Group group2 = new Group(2);
        
        groups.put(group1.getId(),group1);
        groups.put(group2.getId(),group2);

        User admin = new User("admin", "admin");
        users.put(admin.getUsername(),admin);

        this.getServletContext().setAttribute("users", users);
        this.getServletContext().setAttribute("groups", groups);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, User> users = (Map<String,User>)this.getServletContext().getAttribute("users");

        User user = users.get(username);
        HttpSession session = request.getSession();

        if ( username == null ) {
            session.setAttribute("error", "username non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        System.out.println(password);
        System.out.println(user.getPassword());

        if ( username.equals("admin") && user.getPassword().equals(password) ) {

            session.setAttribute("currentUser", user);
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);

        } else if ( user.getPassword().equals(password) ) {
            Map<Integer, Group> groups = (Map<Integer, Group>)this.getServletContext().getAttribute("groups");
            Group group = groups.get(user.getGroupId());
            
            if ((new Date().getTime() - user.getLastModifyOnPsw().getTime()) < 1000 * 60 * 60 * 24 * 60) {
                user.setValid(false);

                int countInvalid = 0;
                for ( User gUser : group.getUsers()) {
                    if ( !gUser.isValid()) {
                        countInvalid++;
                    }
                }

                if ( countInvalid >= 3) {
                    for ( User gUser : group.getUsers()) {
                        gUser.setValid(false);
                    }
                }
                
                user.setTryiedPsw(0);
                session.setAttribute("currentUser", user);
                session.removeAttribute("error");
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            } else {

                session.setAttribute("error", "la psw e' scaduta reimpostarla");
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            
            user.setTryiedPsw(user.getTryiedPsw() + 1);
            session.setAttribute("error", "psw sbagliata");
            if ( user.getTryiedPsw() >= 3 ) {
                session.setAttribute("error", "hai provato la password troppe volte !");
            }

            user.setPassword("djnaksdmnaiksdmaiksdmalsndklasmdkamsd");
            
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}