//package cn.techtutorial.dao;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import javax.servlet.http.HttpServletResponse;
//
//public class EmailSender {
//
//    public static void sendOTPEmail(String toEmail, String otp, boolean bodyisHTML) throws MessagingException {
//        // Set up the properties for the mail server
//        Properties props = new Properties();
////        props.put("mail.smtp.host", "smtp.gmail.com");
////        props.put("mail.smtp.port", "587");
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.socketFactory.port", "587");
//        
//        props.put("mail.smtp.auth", "true");
//        
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
////        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("mail.smtp.starttls.enable", "true");
//        //String pass="ghss qhak wepm njin";
//        //String username = "yoyiseo12@gmail.com";
//        
//        String pass="cyac wjwj wdcs flob";
//        String username = "thedan1304@gmail.com";
//        // Get the Session object
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, pass);
//            }
//        });
//        session.setDebug(true);
//        // Create a message
//        Message message = new MimeMessage(session);
//        message.setSubject("OTP Verification");
//        message.setContent(otp,"text/html;charset=utf-8");
//        message.setText(otp);
//
//        // 3 - address the message
//        Address fromAddress = new InternetAddress(username);
//        Address toAddress = new InternetAddress(toEmail);
//        message.setFrom(fromAddress);
//        message.setRecipient(Message.RecipientType.TO, toAddress);
//        // Send the message
//        Transport.send(message);
//
//        System.out.print("Tôi yêu lập trình.");
//    }
//}
//package cn.techtutorial.dao;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//public class EmailSender {
//
//    public static void sendOTPEmail(String toEmail, String otp, boolean bodyisHTML) throws MessagingException {
//        // Set up the properties for the mail server
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        // Use environment variables or a configuration file to store sensitive information
//        String pass = "zwdk eoin bsej qcev";
//        String username = "yoyiseo12@gmail.com";
//
//        // Get the Session object
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, pass);
//            }
//        });
//
//        // Create a message
//        Message message = new MimeMessage(session);
//        message.setSubject("OTP Verification");
//        message.setContent(otp, "text/html;charset=utf-8");
//
//        // Address the message
//        Address fromAddress = new InternetAddress(username);
//        Address toAddress = new InternetAddress(toEmail);
//        message.setFrom(fromAddress);
//        message.setRecipient(Message.RecipientType.TO, toAddress);
//        Transport.send(message);
//        // Send the message
//        try {
//            Transport.send(message);
//        } catch (MessagingException e) {
//            // Handle or log the exception
//            throw new RuntimeException("Failed to send OTP email", e);
//        }
//    }
//}
package cn.techtutorial.dao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;

public class EmailSender {

    public static void sendOTPEmail(String toEmail, String otp) throws MessagingException {
        // Set up the properties for the mail server
        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        String pass="zwdk eoin bsej qcev";
        // Replace with your email credentials
        String username = "yoyiseo12@gmail.com";

        // Get the Session object
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });
        session.setDebug(true);
        // Create a message
        Message message = new MimeMessage(session);
        message.setSubject("OTP Verification");
        
        message.setText(otp);

        // 3 - address the message
        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(toEmail);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        // Send the message
        Transport.send(message);

        System.out.print("Tôi yêu lập trình.");
    }
}
