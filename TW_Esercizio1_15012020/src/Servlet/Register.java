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

import org.hsqldb.Session;

import com.google.gson.Gson;

import Beans.Group;
import Beans.User;

public class Register extends HttpServlet {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Integer groupId = Integer.parseInt(request.getParameter("group"));

        HttpSession session = request.getSession();

        Map<Integer, Group> groups = (Map<Integer, Group>)this.getServletContext().getAttribute("groups");
        Group group = groups.get(groupId);

        if ( group == null) {
            session.setAttribute("error", "gruppo non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        User newUser = new User(username, password);
        newUser.setGroupId(groupId);

        group.addUser(newUser);

        Map<String, User> users = (Map<String,User>)this.getServletContext().getAttribute("users");
        users.put(newUser.getUsername(),newUser);
        
        this.getServletContext().setAttribute("users", users);
        this.getServletContext().setAttribute("groups", groups);

        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
