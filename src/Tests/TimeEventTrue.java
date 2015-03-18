package Tests;

/**
 * Created by henrikmm on 3/18/15.
 */
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import Server.DatabaseServer;
import Controller.CalendarViewController;
import Model.User;


import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class TimeEventTrue extends GuiTest{
    DatabaseServer server;
    CalendarViewController controller;
    User user;
    public TimeEventTrue(){
        user = new User();
        user.setUsername("henrikmm");
        user.setPassword("testtest");
        server = new DatabaseServer();
        try{
            server.login("henrikmm", "testtest");}
        catch (SQLException e){
            System.out.println(e);
        }
        try{
        controller = new CalendarViewController(server);}
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    protected Parent getRootNode() {
        try{
            return FXMLLoader.load(this.getClass().getResource("/Views/createEventView.fxml"));
        }catch (IOException e){
            System.err.println(e);

        }
        return null;
    }

    @Test
    public void testTrueEventTime(){
        DatePicker date = find("#createEventViewDatePicker");
        String date2 = "3/20/2015";
        clickOn(date).type(date2);
        Object startHour = ((ComboBox) find("#createEventViewStartHours")).getValue();
        Object startMin = ((ComboBox) find("#createEventViewStartMinutes")).getValue();
        Object endHour = ((ComboBox) find("#createEventViewEndHours")).getValue();
        Object endMin = ((ComboBox) find("#createEventViewEndMinutes")).getValue();

        TextArea desc = find("#createEventViewTextArea");
        String descr = "TestDescription.";
        clickOn(desc).type(descr);


        clickOn("#createEventViewCreate");
    }

}
