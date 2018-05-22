package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadAndDeleteCookie extends HttpServlet 
{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        Cookie cookie = null;
        Cookie[] cookies = null;
        
        //request nesnesi üzerinden cooki'ler alınır:
        cookies = request.getCookies();
        
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter out = response.getWriter();
        String title = "Reading and Delete Cookies Example";
        String docType =
           "<!doctype html public \"-//w3c//dtd html 4.0 " +
           "transitional//en\">\n";

        out.println(docType +
           "<html>\n" +
           "<head><title>" + title + "</title></head>\n" +
           "<body bgcolor = \"#f0f0f0\">\n" );
        
        if(cookies != null)
        {
            out.println("<h2> Found Cookies Name and Value</h2>");
            for(int i = 0; i < cookies.length; i++)
            {
                cookie = cookies[i];
                
                //first_name isimli cookie tespit edilerek silinir.
                //Silme işlemi bir sonraki talep ile gerçekleştirilir.
                if((cookie.getName()).equals("first_name"))
                {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    out.print("<p>Deleted Cookie : " + cookie.getName() + "</p>");
                }
                out.print("Name : " + cookie.getName() + ", ");
                out.print("Value : " + cookie.getValue() + "<br/>");
            }
        }
        else
        {
            out.println("No cookies found.");
        }
        out.println("</body>");
        out.println("</html>");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
    }

}
