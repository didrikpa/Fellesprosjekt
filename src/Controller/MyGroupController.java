package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import Server.*;
import Model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by masoom on 09.03.15.
 */
public class MyGroupController implements Initializable {
    @FXML
    Label myGroups1;
    @FXML
    Label myGroups2;
    List<String> myGroups;

    DatabaseServer databaseServer = new DatabaseServer();
    Group group;
    public MyGroupController(DatabaseServer databaseServer){
        this.databaseServer = databaseServer;
    }

    @FXML
    public void getGroupNames(){
        try {
            myGroups = databaseServer.getGroups();
            myGroups1.setText(myGroups.get(0)  + "\n Members:" + databaseServer.getGroupMembers(myGroups.get(0)));
            myGroups2.setText(myGroups.get(1) + "\n Members:" + databaseServer.getGroupMembers(myGroups.get(1)));
        }
        catch (Exception e) { System.out.println(e);}
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        try{

            getGroupNames();
        }
        catch (Exception e) { System.out.println(e); }
    }
}
