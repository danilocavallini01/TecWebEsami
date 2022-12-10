package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("users") == null) {
            UserDb users = new UserDb();
            char [][] cruciverba = new char[10][10];
            for ( int i = 0; i < 10; i++ ) {
                for ( int j = 0; j < 10; j++ ) {
                    cruciverba[i][j] = '0';
                }   
            }

            List<String> parole = new ArrayList<String>();
            List<String> trovate = new ArrayList<String>();

            try {
                BufferedReader bf = new BufferedReader(new FileReader(new File("cruciverba.txt")));
                String line;
                while((line = bf.readLine()) != null ) {
                    parole.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            User user = new User("a", "a");
            User usr1 = new User("b", "b");
            User usr2 = new User("c", "c");
            User usr3 = new User("d", "d");
    
            users.add(user); 
            users.add(usr1);
            users.add(usr2);
            users.add(usr3);

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("cruciverba", cruciverba);
            this.getServletContext().setAttribute("parole", parole);
            this.getServletContext().setAttribute("trovate", trovate);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDb users = (UserDb)this.getServletContext().getAttribute("users");
      
        User user = users.get(username);
        HttpSession session = request.getSession();

        if (username == null || user == null) {
            session.setAttribute("error", "username non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (user.getPassword().equals(password)) {

            this.getServletContext().getRequestDispatcher("/cruciverba.jsp").forward(request, response);
            return;
        } else {
            session.setAttribute("error", "psw o user errati");

            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}