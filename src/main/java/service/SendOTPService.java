// package service;

// import jakarta.mail.*;
// import jakarta.mail.internet.InternetAddress;
// import jakarta.mail.internet.MimeMessage;
// import java.util.Properties;

// public class SendOTPService {
//     public static void sendOTP(String email, String genOTP) {
//         // Recipient's email ID
//         String to = email;

//         // Sender's email ID
//         String from = "harsh.dtu2025@gmail.com";

//         // SMTP Server
//         String host = "smtp.gmail.com";

//         // Get system properties
//         Properties properties = new Properties();

//         // Setup mail server properties
//         properties.put("mail.smtp.host", host);
//         properties.put("mail.smtp.port", "465");
//         properties.put("mail.smtp.ssl.enable", "true");
//         properties.put("mail.smtp.auth", "true");

//         // Debugging info (for troubleshooting)
//         properties.put("mail.debug", "true");

//         // Get credentials from environment variables (more secure)
//         final String username = from;
//         final String password = System.getenv("EMAIL_PASSWORD"); // Set this in environment variables

//         if (password == null) {
//             System.out.println("Error: EMAIL_PASSWORD environment variable is not set!");
//             return;
//         }

//         // Get the Session object and authenticate
//         Session session = Session.getInstance(properties, new Authenticator() {
//             protected PasswordAuthentication getPasswordAuthentication() {
//                 return new PasswordAuthentication(username, password);
//             }
//         });

//         try {
//             // Create a default MimeMessage object
//             MimeMessage message = new MimeMessage(session);

//             // Set From: header field
//             message.setFrom(new InternetAddress(from));

//             // Set To: header field
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//             // Set Subject
//             message.setSubject("File Enc OTP");

//             // Set the actual message
//             message.setText("Your One-Time Password (OTP) for File Enc app is: " + genOTP);

//             System.out.println("Sending email...");
            
//             // Send message
//             Transport.send(message);
            
//             System.out.println("Email sent successfully!");
//         } catch (MessagingException mex) {
//             mex.printStackTrace();
//         }
//     }
// }

package service;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTPService {
    public static void sendOTP(String email, String genOTP) {
        // Load .env file
        Dotenv dotenv = Dotenv.load();

        // Retrieve credentials from .env file
        String from = "harsh.dtu2025@gmail.com";
        String password = dotenv.get("EMAIL_PASSWORD");

        if (password == null || password.isEmpty()) {
            System.out.println("Error: EMAIL_PASSWORD is not set in .env file!");
            return;
        }

        // SMTP Server
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = new Properties();

        // Setup mail server properties
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Debugging info
        properties.put("mail.debug", "true");

        // Get the Session object and authenticate
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject
            message.setSubject("File Hider OTP");

            // Set the actual message
            message.setText("Your One-Time Password (OTP) for File Hider app is: " + genOTP);

            System.out.println("Sending email...");
            
            // Send message
            Transport.send(message);
            
            System.out.println("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
