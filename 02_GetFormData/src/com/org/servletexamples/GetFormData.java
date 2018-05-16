package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Form üzerinden GET methodu ile veri alan basit bir örnek.

public class GetFormData extends HttpServlet 
{

    //GET Taleplerini Karşılayacak Olan Method:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //Gelen değerlerin veri seti UTF-8 olarak ayarlanır:
        request.setCharacterEncoding("UTF-8");
        
        //response nesnesinin içerik tipi ayarlanır:
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter out = response.getWriter();
        
        String title = "Read Form Data With GET Method";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        out.println(
        docType +
                "<html>\n" +
                    "<head>" +
                        "<meta charset=\"UTF-8\">" +
                        "<title>" + title + "</title>" +
                    "</head>\n" +
                    "<body bgcolor = \"f0f0f0\">\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                            "<li><b>First Name</b>:" +
                            request.getParameter("first_name") + "</li>\n" +
                            "<li><b>Last Name</b>:" +
                            request.getParameter("last_name") + "</li>\n" +
                        "</ul>\n" +
                    "</body>" +
                "</html>"
        );
    }
    
    
    //POST Taleplerini karşılayacak olan method:
    public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}