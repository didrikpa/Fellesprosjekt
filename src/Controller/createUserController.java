package Controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Server.DatabaseServer;
public class createUserController extends Application {
	@FXML
	FXMLLoader fxmlLoader;
	@FXML
	Parent root;
	@FXML
	Stage ps;

    @Override
    public void start(Stage primaryStage) throws IOException {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(this);
        root = (Parent) fxmlLoader.load(this.getClass().getResource("Views/createUserPane.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
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
    
    
    //Mangler litt validering
    @FXML
    public void createAction(ActionEvent event) throws Exception{
    	DatabaseServer en = new DatabaseServer();
    	if(!en.userExist(createUserPaneUsername.getText())){
    		en.addUser(createUserPaneUsername.getText(), createUserPanePassword.getText(),createUserPaneFirst.getText(), createUserPaneLast.getText(), createUserPaneEmail.getText(), createUserPanePhone.getText());
    		System.out.println("Vellykket");
    	}
    	else{
    		createUserPaneUsername.clear();
    	}
    }
    @FXML
    public void cancelAction(ActionEvent event) throws Exception{
    	ps.close();
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}