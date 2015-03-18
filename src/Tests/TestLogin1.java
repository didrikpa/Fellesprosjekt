package Tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class TestLogin1 extends GuiTest{
	
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
    public void testWrongPswd() {
        TextField usernameField = find("#loginPaneUsername");
        String text1 = "henrikmm";
        clickOn(usernameField).type(text1);
        PasswordField passwordField = find("#loginPanePassword");
        String text2 = "tull";
        clickOn(passwordField).type(text2);
        clickOn("#loginPaneLoginButton");
        assertEquals("Username or password do not\n match or do not exist.",((Label) find("#userError")).getText());
    }
	
	@Test
    public void testWrongUsername() {
        TextField usernameField = find("#loginPaneUsername");
        String text1 = "wrong";
        clickOn(usernameField).type(text1);
        TextField passwordField = find("#loginPanePassword");
        String text2 = "correct";
        clickOn(passwordField).type(text2);
        clickOn("#loginPaneLoginButton");
        assertEquals("Username or password do not\n match or do not exist.",((Label) find("#userError")).getText());
    }


}
