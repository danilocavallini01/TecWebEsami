package Servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.GroupDb;
import Beans.User;
import Beans.UserDb;

public class Register extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("users") == null
                || this.getServletContext().getAttribute("groups") == null) {
            GroupDb groups = new GroupDb();
            UserDb users = new UserDb();

            Group group1 = new Group(1);
            Group group2 = new Group(2);
            User admin = new User("admin", "admin");

            groups.add(group1);
            groups.add(group2);

            users.add(admin);

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("groups", groups);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        Integer groupId = -1;
        
        try {
            groupId = Integer.parseInt(request.getParameter("group"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "gruppo non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
      

        GroupDb groups = (GroupDb)this.getServletContext().getAttribute("groups");
        UserDb users = (UserDb)this.getServletContext().getAttribute("users");

        if ( users.containsKey(username)) {
            session.setAttribute("error", "nome gia' registrato");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        Group group = groups.get(groupId);

        if ( group == null) {
            session.setAttribute("error", "gruppo non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        User newUser = new User(username, password);
        newUser.setGroupId(groupId);

        group.addUser(newUser);

        users.add(newUser);
        
        this.getServletContext().setAttribute("users", users);
        this.getServletContext().setAttribute("groups", groups);

        session.removeAttribute("error");
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
