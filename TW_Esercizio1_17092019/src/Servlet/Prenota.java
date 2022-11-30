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

import Beans.Albergo;
import Beans.Prenotazioni;

public class Prenota extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("alberghi") == null ) {
            List<Albergo> alberghi = new ArrayList<Albergo>();
            List<Prenotazioni> prenotazioni = new ArrayList<Prenotazioni>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(new File(config.getInitParameter("dir"))));
                String line;

                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line,",");
                    int id = Integer.parseInt(st.nextToken());
                    int camereTotali = Integer.parseInt(st.nextToken());
                    float prezzoStatico = Float.parseFloat(st.nextToken()); 

                    alberghi.add(new Albergo(id, camereTotali, prezzoStatico));
                }

                this.getServletContext().setAttribute("alberghi", alberghi);
                this.getServletContext().setAttribute("prenotazioni", prenotazioni);
            } catch (IOException e) {
                throw new ServletException(e.getMessage());
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Albergo> alberghi = (List<Albergo>)this.getServletContext().getAttribute("alberghi");
        HttpSession session = request.getSession();

        session.setAttribute("numAlberghi", alberghi.size());
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }
}
