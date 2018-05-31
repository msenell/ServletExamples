package com.org.servletexamples;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


public class Mail 
{
    protected static boolean send(String to, String subject, String message)
    {
        String from = "msenell.and@gmail.com";
        String pass = "XXX";
        boolean res = true;
        
        //1. adımda oturum nesnesi oluşturulur:
        Properties prp = new Properties();
        prp.put("mail.smtp.host", "smtp.gmail.com");
        prp.put("mail.smtp.socketFactory.port", "465"); //SSL Port
	prp.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
        prp.put("mail.smtp.port", "465");
        prp.put("mail.smtp.ssl.enable", "true");
        prp.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(prp, 
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication(from, pass);
                    }
                });
        //2. adımda mesaj hazırlanır:
        try
        {
            MimeMessage mmsg = new MimeMessage(session);
            mmsg.setFrom(new InternetAddress(from));
            mmsg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mmsg.setSubject(subject, "UTF-8");
            mmsg.setText(message, "UTF-8");
            
            
            //3. adımda mesaj gönderilir:
            Transport.send(mmsg);
        } catch (AddressException ex) {
            res = false;
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            res = false;
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
}
