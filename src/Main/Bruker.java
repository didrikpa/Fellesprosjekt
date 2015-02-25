import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class Bruker extends Application {
	private TextField textField;
    private TextField loginPaneUsername;
    private PasswordField loginPanePassword;
	FXMLLoader fxmlLoader;
	Stage ps;
    @Override
    public void start(Stage primaryStage) throws IOException {
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(this);
        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("loginPane.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void handleUpcaseAction(ActionEvent event){
    	//loginPaneUsername.setText(loginPaneUsername.getText().toUpperCase());
    	textField.setText(textField.getText().toUpperCase());
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}