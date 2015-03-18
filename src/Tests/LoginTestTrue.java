package Tests;

import Controller.CreateEventController;
import org.loadui.testfx.GuiTest;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import Server.DatabaseServer;
import Controller.CalendarViewController;

import org.junit.Test;

/**
 * Created by henrikmm on 3/18/15.
 */
public class LoginTestTrue extends GuiTest{

    @Override
    protected Parent getRootNode() {
        try {
            return FXMLLoader.load(this.getClass().getResource("/Views/loginView.fxml"));
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
    @Test
    public void testCorrectUserAndPw(){
        TextField usernameField = find("#loginPaneUsername");
        String text1 = "henrikmm";
        clickOn(usernameField).type(text1);
        PasswordField passwordField = find("#loginPanePassword");
        String text2 = "testtest";
        clickOn(passwordField).type(text2);
        clickOn("#loginPaneLoginButton");

    }
}
