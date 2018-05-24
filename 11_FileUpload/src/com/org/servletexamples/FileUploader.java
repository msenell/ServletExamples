package com.org.servletexamples;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUploader extends HttpServlet 
{
    
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50*1024;
    private int maxMemSize = 4*1024;
    private File file;
    
    public void init()
    {
        filePath = getServletContext().getInitParameter("file-upload");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        throw new ServletException("GET method used with " +
            getClass( ).getName( )+": POST method required.");
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //Herhangi bir dosya yükleme talebi var mı?:
        isMultipart = ServletFileUpload.isMultipartContent(request);
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        
        if( !isMultipart ) 
        {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>"); 
            out.println("</body>");
            out.println("</html>");
            return;
         }
        
        DiskFileItemFactory dff = new DiskFileItemFactory();
        
        //Hafızada saklanacak maximum boyut:
        dff.setSizeThreshold(maxMemSize);
        
        //maxMemSize'dan büyük boyutlu verilerin kaydedileceği lokasyon:
        dff.setRepository(new File("c:\\temp"));
        
        //Dosya yükleme operatörü tanımlanır:
        ServletFileUpload sfUpload = new ServletFileUpload(dff);
        
        //Yüklenebilecek maximum dosya boyutu:
        sfUpload.setSizeMax(maxFileSize);
        
        try
        {
            //Request nesnesinden yüklenecek dosyalar ayrıştırılır:
            List fileItems = sfUpload.parseRequest(request);
            
            //Yüklenecek dosyalar işlenir:
            Iterator i = fileItems.iterator();
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");  
            out.println("</head>");
            out.println("<body>");
            
            while(i.hasNext())
            {
                FileItem fi = (FileItem) i.next();
                if(!fi.isFormField())
                {
                    //Yüklenen dosya parametreleri alınır:
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    
                    //Dosya yazılır:
                    if(fileName.lastIndexOf("\\") >= 0)
                    {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    }
                    else
                    {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1));
                    }
                    fi.write(file);
                    out.println("Uploaded file name : " + fileName + "<br/>");
                }
            }
            out.println("</body>");
            out.println("</html>");
         
        } catch (FileUploadException ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FileUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
