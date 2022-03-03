/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Asus
 */
public class JavaMailUtil {
    public static void sendEmail(String recepient,String s) 
    {
        System.out.println("Prepare");
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccountEmail="e.citoyen.tunisie@gmail.com";
        String password="E-citoyen2022";
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail,password);
                
            }
        });
        Message message = prepareMessage(session,myAccountEmail,recepient,s);
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("envoyee avec succes");
        
        

    
    }
    

   
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String msg) {
        try {
            Message message=new MimeMessage(session);
        message.setFrom(new InternetAddress (myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress (recepient));
        message.setSubject("Attente de Confirmation d'inscription");
        message.setText(msg);
        message.reply(false);
        return message;
            
        }
        catch(Exception e)
        {
           e.getMessage();
        }
        return null;
    }
    
}
