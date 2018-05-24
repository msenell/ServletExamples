package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author senel
 */
public class UseDate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html; charset = utf-8");
        
        PrintWriter out = response.getWriter();
        long ms;
        try
        {
            ms = Long.parseLong( request.getParameter("milisecond") );
            Date dt = new Date(ms);
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss");
            String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
            out.print
            (
                    docType +
                    "<html>" +
                            "<head>" +
                                "<title>Your Date</title>" +
                                "<meta charset = \"utf-8\" />" +
                            "</head>" +
                            "<body>" +
                                "<h2>Your Date is:</h2>" +
                                "<h4>" + df.format(dt) + "</h4>" +
                            "</body>" +
                    "</html>"
                                
                            
            );
        }catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
