package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Kendini 5 saniyede bir yenileyerek mevcut zamanı ekrana yazan Servlet Uygulaması:

public class HTTPResponse extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        response.setIntHeader("Refresh", 5);
        
        response.setContentType("text/html");
        
        Calendar cal = new GregorianCalendar();
        String am_pm;
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        
        if(cal.get(Calendar.AM_PM) == 0)
            am_pm = "AM";
        else
            am_pm = "PM";
        String CT = hour + ":" + minute + ":" + second + " " + am_pm;
        
        PrintWriter out = response.getWriter();
        String title = "Auto Refresh Header Setting";
        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//tr\">\n";
        
        out.println(
                docType +
                "<html>\n" +
                        "<head><title>" + title + "</title></head>" +
                        "<body bgcolor = \"#f0f0f0\">\n" +
                            "<h1 align = \"center\">" + title + "</h1>\n" +
                            "<p>Current type is: " + CT + "</p>\n" +
                        "</body>\n" +
                "</html>"
        );
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
