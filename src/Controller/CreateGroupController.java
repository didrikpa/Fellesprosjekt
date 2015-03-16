package Controller;

import Server.DatabaseServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class CreateGroupController {

    @FXML
    TextField groupNameField;

    @FXML
    Button createGroupButton;

    @FXML
    Button cancelButton;

    @FXML
    Label groupNameLabel;

    DatabaseServer server = new DatabaseServer();

    public CreateGroupController(DatabaseServer server){
        this.server = server;
    }

    public void setCreateGroupButton(ActionEvent event) throws Exception{
        List groupList = server.getAllGroups();
        if(groupNameField.getText().isEmpty()){
            groupNameLabel.setText("This field can't be empty");
            groupNameLabel.setVisible(true);
        }
        else if (groupList.contains(groupNameField.getText())){
            groupNameLabel.setText("This name is already in use");
            groupNameLabel.setVisible(true);
        }
        else{
            createGroup(groupNameField.getText());
            groupNameLabel.setVisible(false);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void createGroup(String groupname){
        try {
            server.createGroup(groupname);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void setCancelButton(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}