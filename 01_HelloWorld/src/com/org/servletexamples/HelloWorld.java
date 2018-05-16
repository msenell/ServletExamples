/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author senel
 */


public class HelloWorld extends HttpServlet
{
    private String message;
    
    //Servlet'i oluşturmak için bir kez çalışacak olan method:
    @Override
    public void init() throws ServletException
    {
        message = "Hello World!";
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //Response nesnesinin içerik tipi belirlenir:
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }
    
    public void destroy()
    {
        
    }
}
