package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.Properties;

public class EmailController {

    @FXML
    TextField resetPasswordEmail;

    public void sendEmail() {
        // Recipient's email ID needs to be mentioned.
        String to;
        to = resetPasswordEmail.getText();

        // Sender's email ID needs to be mentioned
        String from = "calendar@email.com";

        // Assuming you are sending email from localhost
        String host = "smtp.stud.ntnu.no";

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
            message.setSubject("Reset Password");

            // Now set the actual message
            message.setText("Your password is password");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
