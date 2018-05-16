package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckBoxData extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //Gelen değerlerin veri seti UTF-8 olarak ayarlanır:
        request.setCharacterEncoding("UTF-8");
        
        //response nesnesinin içerik tipi ayarlanır:
        response.setContentType("text/html; charset=utf-8");
        
        PrintWriter out = response.getWriter();
        
        String title = "Read From Data With getParameterNames()";
        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        /*out.println(docType +
         "<html>\n" +
            "<head>" + 
                 "<meta  charset = \"UTF-8\" />\n" +
                 "<title>" + title + "</title>" + 
            "</head>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<ul>\n" +
                  "  <li><b>Matematik : </b>"
                  + request.getParameter("maths") + "\n" +
                  "  <li><b>Fizik : </b>"
                  + request.getParameter("physics") + "\n" +
                  "  <li><b>Kimya : </b>"
                  + request.getParameter("chemistry") + "\n" +
               "</ul>\n" +
            "</body>" +
         "</html>"
      );*/
        
        //getParameterNames() methodu ile parametre isimlerini alarak döngü ile bastırmak:
        out.println(
                docType +
                "<html>\n" +
                        "<head>\n" +
                            "<meta charset = \"UTF-8\"/>" +
                            "<title>" + title + "</title>" +
                        "</head>" +
                        "<body bgcolor = \"#f0f0f0\">\n" +
                            "<h1 align = \"center\">" + title + "</h1>\n" +
                            "<table width = \"100%\" border = \"1\" align = \"center\" >\n" +
                                "<tr bgcolor = \"#949494\">\n" +
                                    "<th>Param Name</th>" +
                                    "<th>Param Value(s)</th>\n" +
                                "</tr>\n"
                        
        );
        
        //Parametre isimleri alınır:
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements())
        {
            String paramName = (String)paramNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>");
            String[] paramValues = request.getParameterValues(paramName);
            
            //Tek değerli veri ise:
            if(paramValues.length == 1)
            {
                String paramValue = paramValues[0];
                if(paramValue.length() == 0)
                    out.println("<i>No Value</i>");
                else
                    out.println("<td>" + paramValue + "</td></tr>");           
            }
            else
                {
                    //Çoklu değer varsa:
                    out.println("<td><ul>");
                    for(int i = 0; i < paramValues.length; i++)
                    {
                        out.println("<li>" + paramValues[i] + "</li>");
                    }
                    out.println("</ul></td></tr>");
                }
        }
        out.println("</table>\n</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        doGet(request, response);
    }

 }
