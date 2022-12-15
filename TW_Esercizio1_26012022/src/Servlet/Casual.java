package Servlet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;

import Beans.TotalRequest;

public class Casual extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        
        if ( this.getServletContext().getAttribute("sessions") == null ) {
        	
        	List<HttpSession> sessions = new ArrayList<HttpSession>();
        	
        	this.getServletContext().setAttribute("sessions", sessions);
        	this.getServletContext().setAttribute("totalRequest", new TotalRequest());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    	HttpSession session = request.getSession(false);
    	
    	
    	synchronized (this) {
    		if ( session.getAttribute("operation") == null) {
        		
        		List<HttpSession> sessions = (List<HttpSession>)this.getServletContext().getAttribute("sessions");
        		session.setAttribute("valid", true);
        		sessions.add(session);
        		
        		session.setAttribute("operation", 1);
        		
        	} else {
        		session.setAttribute("operation", (int)session.getAttribute("operation") + 1);
        	}
    		
    		TotalRequest tRequest = (TotalRequest)this.getServletContext().getAttribute("totalRequest");
    		
    		if ((new Date().getTime() - tRequest.getDate().getTime()) >= 60 * 60 * 1000) {
    			tRequest.setDate(new Date(0));
    		} else {
    			tRequest.addOperation();
    		}
    		
		}
    	
    	
        String text = (String)request.getParameter("file");
        
        File file = new File(text);
        FileReader fReader = new FileReader(file);
        
        Random random = new Random();
        int randomChr = random.nextInt(25);
        int bannedChr1 = randomChr + 65;
        int bannedChr2 = randomChr + 97;
        
        String result = "";
        int chr;
        
        while ( (chr = fReader.read()) != -1 ) {
        	if ( chr != bannedChr1 && chr != bannedChr2 ) {
                result += Character.toString(chr);
            } 
        }

        fReader.close();
        
        session.setAttribute("result", result );
        this.getServletContext().getRequestDispatcher("/casualJsp.jsp").forward(request,response);
    }
}