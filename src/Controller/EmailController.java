package Controller;

import Server.DatabaseServer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailController {

    DatabaseServer databaseServer = new DatabaseServer();

    @FXML
    AnchorPane resetPasswordView;
    @FXML
    TextField resetPasswordEmail;
    @FXML
    Label emailError;

    Stage stage;

    public void sendEmail() {
        try {
            if (databaseServer.emailExist(resetPasswordEmail.getText())) {
                emailError.setVisible(true);
                emailError.setStyle("-fx-text-fill: green");
                emailError.setText("Your password has been sent to " + resetPasswordEmail.getText() + ".");
               // Recipient's email ID needs to be mentioned.
                String to;
                to = resetPasswordEmail.getText();

                // Sender's email ID needs to be mentioned
                String from = "awesome@calendar.com";

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
                    try {
                        message.setText("Your password is " + databaseServer.getPassword(to) + "");

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    // Send message
                    Transport.send(message);
                    System.out.println("Sent message successfully....");

                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            } else {
                if(resetPasswordEmail.getText().isEmpty()){
                    emailError.setText("You need to type in an email address to proceed");
                    emailError.setVisible(true);
                }
                else {
                    emailError.setText("This email address does not belong to any accounts in this system");
                    emailError.setVisible(true);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
