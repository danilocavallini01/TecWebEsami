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

public class FileManager extends HttpServlet    
{
    private Gson gson;
    private File dir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        dir = new File(this.getServletContext().getInitParameter("dir"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ( dir.exists() && dir.isDirectory()) {
            HttpSession session = request.getSession();
            session.setAttribute("files", dir.list());
            this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] filenames = new String[3];

        String startString = this.getServletContext().getInitParameter("dir");
        
        filenames[0] = startString + File.separator + request.getParameter("file1");
        filenames[1] = startString + File.separator + request.getParameter("file2");
        filenames[2] = startString + File.separator + request.getParameter("file3");

        BeanCounter counter = new BeanCounter(filenames);
        counter.start();

        int count = 0;
        long time = new Date().getTime();

        int c;
        for ( int i = 0; i < 3;  i++ ) {
            try {
                FileInputStream ir = new FileInputStream(new File(filenames[i]));
                
                while((c = ir.read()) > 0) {
                    if ( c >= 65 && c <= 90 ) {
                        count++;
                    }
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        time = new Date().getTime() - time;

        try {
            counter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Result result = new Result();
        result.setServerTime(time);
        result.setBeanTime(counter.getElapsedTime());
        result.setServerCount(count);
        result.setBeanCount(counter.getCount());
        System.out.println(result);

        response.getWriter().println(gson.toJson(result));
    }
}