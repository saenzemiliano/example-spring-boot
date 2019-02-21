/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.common;

import com.example.springboot.demospringboot.common.PwdGenerator.PwdGeneratorBuilder;

/**
 *
 * @author emiliano
 */
public class Main {
    public static void main(String [] args) {
    	PwdGenerator passwordGenerator = new PwdGeneratorBuilder()
    	        .useDigits(true, 2)
    	        .useLower(true, 1)
    	        .useUpper(true, 1)
    	        .build();
    	
    	
    	for (int i = 0; i < 500; i++) {
    		System.out.println(passwordGenerator.generate(8));
		}
        
    }
    
    
     /*  public static void main(String [] args) {    
      // Recipient's email ID needs to be mentioned.
      String to = "abcd@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "web@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }*/

    
}



