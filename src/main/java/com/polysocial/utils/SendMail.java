package com.polysocial.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.polysocial.repo.UserRepo;

@Controller
public class SendMail {
    @Autowired
    UserRepo userRepo ;
    
  public void sendMail(String email) {
   // Recipient's email ID needs to be mentioned.
      System.out.println("start ");
      String to =  email;
//      String name = userRepo.findById(userRepo.getUserByEmail(email).getUserId()).get().getFullName();
//      
      String name = to;
      // Sender's email ID needs to be mentioned
      String from = "polysocial.network2022@gmail.com";

      // Assuming you are sending email from through gmails smtp
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "456");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.ssl.enable", "true");


      // Get the Session object.// and pass username and password
      Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

          protected PasswordAuthentication getPasswordAuthentication() {

              return new PasswordAuthentication("polysocial.network2022@gmail.com", "sopssuzbwqnfczjg");

          }

      });

      // Used to debug SMTP issues
      session.setDebug(true);

      try {
          // Create a default MimeMessage object.
          MimeMessage message = new MimeMessage(session);

          // Set From: header field of the header.
          message.setFrom(new InternetAddress(from));

          // Set To: header field of the header.
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

          // Set Subject: header field
          message.setSubject("Restore Password!");
          String code = Math.round(Math.random() * 89999) + 10000+"";
          Template temp = new Template();
          String sb = temp.setContent(name, code);
          // Now set the actual message
          message.setContent(sb,"text/html; charset=UTF-8");

          System.out.println("sending...");
          // Send message
          Transport.send(message);
          System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
          mex.printStackTrace();
      }

}
}
