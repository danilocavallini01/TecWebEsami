package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.User;
import Beans.UserDb;

public class CheckParola extends HttpServlet {
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
                    parole.add(line.toUpperCase(Locale.ITALIAN));
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
       
        HttpSession session = request.getSession(false);

        char chr = request.getParameter("char").charAt(0);
        int row = Integer.parseInt(request.getParameter("row"));
        int col = Integer.parseInt(request.getParameter("col"));

        char [][] cruciverba =  (char[][])this.getServletContext().getAttribute("cruciverba");
        List<String> parole = (List<String>)this.getServletContext().getAttribute("parole");
        List<String> trovate = (List<String>)this.getServletContext().getAttribute("trovate");

        cruciverba[row][col] = chr;
        int numCaratteri;
        String parola;

        synchronized(this) {
            if ( session.getAttribute("operazioni") == null ) {
                session.setAttribute("operazioni", 0 ); 
            } else {
                if ( (int)session.getAttribute("operazioni") >= 499 ) {
                    response.sendRedirect("/login");
                    this.getServletContext().getRequestDispatcher("/login.jsp");
                    return;
                }
                session.setAttribute("operazioni",(int) session.getAttribute("operazioni") + 1); 
            }
        }
        

        for ( int i = 0; i < 10; i++ ) {
            numCaratteri = 0;
            parola = new String();

            for ( int j = 0; j < 10; j++ ) {
                if ( cruciverba[i][j] != '0' ) {
                    numCaratteri++;
                    parola += cruciverba[i][j];
                }   
            }

            if ( numCaratteri == 10 ) {
                if ( parole.contains(parola) ) {
                    if ( !trovate.contains(parola) ) {
                        trovate.add(parola);
                        parole.remove(parola.indexOf(parola));
                    }
                }
            }
        }

        for ( int i = 0; i < 10; i++ ) {
            numCaratteri = 0;
            parola = new String();

            for ( int j = 0; j < 10; j++ ) {
                if ( cruciverba[j][i] != '0' ) {
                    numCaratteri++;
                    parola += cruciverba[j][i];
                }   
            }

            if ( numCaratteri == 10 ) {
                if ( parole.contains(parola) ) {
                    if ( !trovate.contains(parola) ) {
                        trovate.add(parola);
                        parole.remove(parola.indexOf(parola));
                    }
                }
            }
        }
       
        response.getWriter().println(gson.toJson(trovate));
    }

}