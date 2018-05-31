package com.org.servletexamples;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Localization extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Locale locale = request.getLocale();
            
            String date =DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, locale).format(new Date());
            
            NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
            String formattedCur = nf.format(1000000);
            String formattedPer = nf.format(0.51);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Localization</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Localization at " + request.getContextPath() + "</h1>");
            out.println("<table align=\"center\" border = \"1px solid black\">" + 
                            "<tr><th>Method</th><th>Output</th></tr>" +
                            "<tr><td>getCountry()</td><td>" + locale.getCountry() + "</td></tr>" +
                            "<tr><td>getDisplayCountry()</td><td>" + locale.getDisplayCountry() + "</td></tr>" +
                            "<tr><td>getLanguage()</td><td>" + locale.getLanguage() + "</td></tr>" +
                            "<tr><td>getDisplayLanguage()</td><td>" + locale.getDisplayLanguage() + "</td></tr>" +
                            "<tr><td>getISO3Country()</td><td>" + locale.getISO3Country() + "</td></tr>" +
                            "<tr><td>getISO3Language()</td><td>" + locale.getISO3Language() + "</td></tr>" +
                            "<tr><td>Locale Date</td><td>" + date + "</td></tr>" +
                            "<tr><td>Locale Currency</td><td>" + formattedCur + "</td></tr>" +
                            "<tr><td>Locale Percentage</td><td>" + formattedPer + "</td></tr>" +
                        "</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
