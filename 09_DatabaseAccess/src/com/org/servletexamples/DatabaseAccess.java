package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
        //Veritabanı sürücü ve adres bilgisi verilir:
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/TEST?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&AutoReconnect=true&useSSL=false";
        
        //Veritabanı erişim bilgileri:
        final String USER = "root";
        final String PASS = "1234";
        
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
        
        try
        {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("test");
            while(rs.next())
            {
                int id = rs.getInt("id");
                String firstName = rs.getString("first");
                String lastName = rs.getString("last");
                int age = rs.getInt("age");
                out.print(
                "<tr><td>" + id + "</td><td>" + 
                        firstName + "</td><td>" + 
                        lastName + "</td><td>" +
                        age +"</td></tr>");
            }
            out.print("</table></body></html>");
            
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
            if(con != null)
                con.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
