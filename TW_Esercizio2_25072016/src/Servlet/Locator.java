package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Entity;

public class Locator extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("entities") == null ) {
            Random random = new Random();
            List<Entity> entities = new ArrayList<Entity>();
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "pickachu", "240 V a 60Hz"));
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "pidgeon", "piccione di piazza San Marco"));
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "giratina", "pokemon leggendario che controlla il tempo"));
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "vermillion", "boh conosco solo il nome"));
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "executtor", "pokemon palma di 6 metri spettacolare"));
            entities.add(new Entity(random.nextInt(80), random.nextInt(80), "diglet", "ti esce dal water"));
        
            this.getServletContext().setAttribute("entities", entities);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Entity> entities = (List<Entity>)this.getServletContext().getAttribute("entities");
        
        List<Entity> visibleEntities = new ArrayList<Entity>();
        int i = 0;

        int x = Integer.parseInt(request.getParameter("x")) + 50;
        int y = Integer.parseInt(request.getParameter("y"));

        for ( Entity entity : entities) {
            int deltaX = Math.abs(entity.getX() - x);
            int deltaY = Math.abs(entity.getY() - y);

            double delta = Math.sqrt(deltaY*deltaY + deltaX*deltaX);
            System.out.println(entity.getX()+ ":" + entity.getY() + "-" + delta);
            if ( delta <= 10d ) {
                visibleEntities.add(entity);        
            }
        }

        response.getWriter().println(gson.toJson(visibleEntities));
    }
}