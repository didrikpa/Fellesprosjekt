package Controller;

import Server.DatabaseServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//import Server.DatabaseServer;
 
public class createUserController{
    @FXML
    private TextField createUserPaneUsername;
    @FXML
    private PasswordField createUserPanePassword;
    @FXML
    private PasswordField createUserPanePasswordRepeat;
    @FXML
    private TextField createUserPaneFirst;
    @FXML
    private TextField createUserPaneLast;
    @FXML
    private TextField createUserPanePhone;
    @FXML
    private TextField createUserPaneEmail;
    @FXML
    private Pane createUserPaneMain;
    Stage stage;
    
    //Mangler litt validering
//    @FXML
//    public void createAction(ActionEvent event) throws Exception{
//    	DatabaseServer en = new DatabaseServer();
//    	if(!en.userExist(createUserPaneUsername.getText())){
//    		en.addUser(createUserPaneUsername.getText(), createUserPanePassword.getText(),createUserPaneFirst.getText(), createUserPaneLast.getText(), createUserPaneEmail.getText(), createUserPanePhone.getText());
//    		System.out.println("Vellykket");
//    		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPane.fxml"));
//    		stage = (Stage) createUserPaneMain.getScene().getWindow();
//    		stage.setScene(new Scene(loader.load()));
//    		stage.show();
//    	}
//    	else{
//    		createUserPaneUsername.clear();
//    	}
//    }
    @FXML
    public void cancelAction(ActionEvent event) throws Exception{
    	stage.close();
    }
}