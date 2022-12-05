package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.GroupDb;
import Beans.Prodotto;
import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("users") == null
                || this.getServletContext().getAttribute("groups") == null) {
            GroupDb groups = new GroupDb();
            UserDb users = new UserDb();

            String prodotto = "";
            String file = "";   

            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("./prodotti.json")));

                while ((prodotto = br.readLine()) != null ) {
                    file += prodotto.replace("[", "").replace("]", "");
                }
            } catch (IOException e) {
                throw new ServletException(e.getMessage());
            }
           
            StringTokenizer st = new StringTokenizer(file, ",\n");
            List<Prodotto> prodotti = new ArrayList<Prodotto>();

            for ( int i = 0; i <= st.countTokens() / 2; i++ ) {
                prodotto = st.nextToken() + "," + st.nextToken();
                System.out.println(prodotto); 
                prodotti.add(gson.fromJson(prodotto, Prodotto.class));
            }
            
            
            int i = 0;
            for ( Prodotto prod : prodotti ) {
                Group group1 = new Group(i++, prod);
                groups.add(group1);
            }

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("groups", groups);
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
            
            session.setAttribute("currentUser", user);
            session.removeAttribute("error");

            user.setSession(session);

            this.getServletContext().getRequestDispatcher("/registerGroup.jsp").forward(request, response);
            return;
        } else {
            session.setAttribute("error", "psw o user errati");

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}