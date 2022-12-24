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
public class SendMailDeadline {
    @Autowired
    UserRepo userRepo ;
    
  public void sendMail(String email, String nameUser, String groupName, String nameTeacher, String deadline) {
   // Recipient's email ID needs to be mentioned.
      System.out.println("start ");
      String to =  email;
//      String name = userRepo.findById(userRepo.getUserByEmail(email).getUserId()).get().getFullName();
//      
      String name = userRepo.findOneByEmail(email).getFullName();
      // Sender's email ID needs to be mentioned
      String from = "polysocial.network2022@gmail.com";

      // Assuming you are sending email from through gmails smtp
      String host = "smtp.gmail.com";



      // Setup mail server
      Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


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
          //set utf-8
          message.addHeader("Content-Type", "text/html; charset=UTF-8");

          // Set To: header field of the header.
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

          // Set Subject: header field
          message.setSubject("Bạn có một bài tập mới");
          TemplateDealine temp = new TemplateDealine();
          String sb = temp.setContent(nameUser, groupName, nameTeacher, deadline);
          // Now set the actual message
          message.setContent(sb,"text/html; charset=UTF-8");

          // Send message
          Transport.send(message);
      } catch (MessagingException mex) {
          mex.printStackTrace();
      }

}
}
