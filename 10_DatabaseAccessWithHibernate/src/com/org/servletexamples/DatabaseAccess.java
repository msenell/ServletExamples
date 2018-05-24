package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    JDBC ile vefritabanı erişimi sağlayıp,
    tablodan okuduğu verileri ekrana basan örnek.
*/
public class DatabaseAccess extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        DatabaseFunctions df = new DatabaseFunctions(HibernateUtil.getSessionAnnotationFactory());
        
        List employees = df.listEmployees();
        
        response.setContentType("text/html; charset = utf-8");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        
        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n" +
         "<table><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Age</th></tr>");
        
        if(employees != null)
        {
            for(Iterator iterator = employees.iterator(); iterator.hasNext();)
            {
                Employee employee = (Employee) iterator.next();
                out.print(
                "<tr><td>" + employee.getID() + "</td><td>" + 
                        employee.getFirstName() + "</td><td>" + 
                        employee.getLastName() + "</td><td>" +
                        employee.getAge() +"</td></tr>");
            }
        }
        out.print("</table></body></html>");
 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
