package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
    Kullanıcı oturum bilgisi oluşturma ve kullanıcıyı tanıma.
*/
public class SessionTrack extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //Session nesnesi mevcut değilse yeni bir session oluşturur:
        HttpSession session = request.getSession(true);
        
        //Session oluşturulma zamanı alınır:
        Date creationTime = new Date(session.getCreationTime());
        
        //Bu sayfaya son erişim zamanı alınır:
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        
        String title = "Sayfamıza tekrar hoşgeldiniz";
        Integer visitCount = new Integer(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("User1");
        
        //Kullanıcı ilk defa geliyorsa:
        if(session.isNew())
        {
            title = "Sayfamıza hoşgeldiniz";
            session.setAttribute(userIDKey, userID);
        }
        else
        {
            visitCount = (Integer)session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID = (String)session.getAttribute(userIDKey);
        }
        session.setAttribute(visitCountKey, visitCount);
        
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter out = response.getWriter();
        String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
        
        out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<h2 align = \"center\">Session Infomation</h2>\n" +
               "<table border = \"1\" align = \"center\">\n" +
                    "<tr><th>Info Type</th><th>Value</th></tr>" +
                    "<tr><td>id</td><td>" + session.getId() + "</td></tr>" +
                    "<tr><td>Oluşturma Zamanı</td><td>" + creationTime + "</td></tr>" +
                    "<tr><td>Son Ziyaret Zamanı</td><td>" + lastAccessTime + "</td></tr>" +
                    "<tr><td>UserID</td><td>" + userID + "</td></tr>" +
                    "<tr><td>Ziyaret Sayısı</td><td>" + visitCount + "</td></tr>" +
               "</table>" +
            "</body" +
        "</html>");
        //5. ziyaaretten sonra session bilgisi silinir:
        if(visitCount >= 5)
        {
            session.invalidate();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    }
}
