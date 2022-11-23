package Servlet;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.BeanCounter;
import Beans.Result;

public class Integral extends HttpServlet
{
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        float start = Float.parseFloat(request.getParameter("start"));
        float end  = Float.parseFloat(request.getParameter("end"));
    
        float result = ((end*end)/2f) - ((start*start)/2f);

        response.getWriter().println(gson.toJson(result));
    }
}