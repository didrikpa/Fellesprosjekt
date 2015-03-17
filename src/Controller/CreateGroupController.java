package Controller;

import Model.User;
import Server.DatabaseServer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class CreateGroupController {

    @FXML
    TextField groupNameField;

    @FXML
    Button createGroupButton;

    @FXML
    Button cancelButton;

    @FXML
    Label groupNameLabel;
    
    CreateEventController cec;
    DatabaseServer server = new DatabaseServer();
    ArrayList<User>newGroup;
    public CreateGroupController(DatabaseServer server, ArrayList<User>nG, CreateEventController ce){
        this.server = server;
        cec = ce;
        newGroup = nG;
        groupNameLabel = new Label();
    }
    
    @FXML
    public void setCreateGroupButton(ActionEvent event) throws Exception{
        ArrayList<String> groupList = server.getAllGroups();
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
        	server.createGroup(groupname, newGroup);
            cec.setGroups();
            cec.createEventViewGroup.setValue(groupname);
            cec.participantList.setItems(FXCollections.observableArrayList(cec.selectedUsers));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    @FXML
    public void setCancelButton(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
