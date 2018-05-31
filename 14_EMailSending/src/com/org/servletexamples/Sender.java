/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sender extends HttpServlet 
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        
        String title = "Send Email";
        String res = "";
        boolean result = Mail.send(to, subject, message);
        if(result)
            res = "Posta başarıyla gönderildi...";
        else
            res = "Posta gönderilirken hata oluştu!";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
         
         out.println(docType +
            "<html>\n" +
               "<head><title>" + title + "</title></head>\n" +
               "<body bgcolor = \"#f0f0f0\">\n" +
                  "<h1 align = \"center\">" + title + "</h1>\n" +
                  "<h3 align = \"center\">" + res + "</h3>\n" +
               "</body>" +
            "</html>"
         );
    }
}