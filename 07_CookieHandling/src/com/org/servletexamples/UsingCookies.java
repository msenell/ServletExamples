package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    Formdan alınan isim ve soyisim bilgilerini cookie olarak kaydeden örnek.
*/
public class UsingCookies extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //isim ve soyisim için Cookie nesneleri oluşturulur:
        Cookie firstName = new Cookie("first_name", request.getParameter("first_name"));
        Cookie lastName = new Cookie("last_name", request.getParameter("last_name"));
        
        //Cookie'lerin geçerlilik süreleri 24 saat olarak ayarlanır:
        firstName.setMaxAge(24*60*60);
        lastName.setMaxAge(24*60*60);
        
        //Cookie'ler response nesnesine eklenir:
        response.addCookie(firstName);
        response.addCookie(lastName);
        
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter out = response.getWriter();
        String title = "Setting Cookies Example";
        String docType =
           "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        out.println(docType +
         "<html>\n" +
            "<head>" +
               "<title>" + title + "</title>" +
            "</head>\n" +
            
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<ul>\n" +
                  "  <li><b>First Name</b>: "
                  + request.getParameter("first_name") + "\n" +
                  "  <li><b>Last Name</b>: "
                  + request.getParameter("last_name") + "\n" +
               "</ul>\n" +
            "</body>" +
         "</html>"
      );
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
