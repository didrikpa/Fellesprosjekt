package Tests;

/**
 * Created by henrikmm on 3/18/15.
 */
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class TimeEventTrue extends GuiTest{
    @Override
    protected Parent getRootNode() {
        try{
            return FXMLLoader.load(this.getClass().getResource("/Views/loginView.fxml"));
        }catch (IOException e){
            System.err.println(e);

        }
        return null;
    }

    @Test
    public void testTrueEventTime(){
        TextField usernameField = find("#loginPaneUsername");
        String text1 = "henrikmm";
        clickOn(usernameField).type(text1);
        PasswordField passwordField = find("#loginPanePassword");
        String text2 = "testtest";
        clickOn(passwordField).type(text2);
        clickOn("#loginPaneLoginButton");

        clickOn("#createEventCalendarView");
        clickOn("#createEventViewDatePicker");



    }
}
