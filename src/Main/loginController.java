package Main;

import java.io.IOException;
import java.sql.SQLException;

import Server.DatabaseServer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class loginController extends Application {
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
        root = (Parent) fxmlLoader.load(this.getClass().getResource("/loginPane.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @FXML
    private TextField loginPaneUsername;
    @FXML
    private TextField loginPanePassword;
    

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(this.getClass().getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = ps.getScene();
        if (scene == null) {
            scene = new Scene(page, 600, 450);
            scene.getStylesheets().add(this.getClass().getResource("demo.css").toExternalForm());
            ps.setScene(scene);
        } else {
            ps.getScene().setRoot(page);
        }
        ps.sizeToScene();
        return page;
    }
    
    @FXML
    public void handleUpcaseAction(ActionEvent event) throws Exception{
    	DatabaseServer en = new DatabaseServer();
    	try {en.valid(loginPaneUsername.getText(), loginPanePassword.getText());} 
    	catch (SQLException e) {e.printStackTrace();}
    	if(en.gyldig){
    		en.Brukernavn = loginPaneUsername.getText();
    		en.Passord = loginPanePassword.getText();
    		//Settes videre til kalenderView, og fyller det med avtaler
    		//relatert til brukeren som nå er innlogget
    		replaceSceneContent("createUserPane.fxml");
    		//fxmlLoader.setController(new createUserController());
    	}
    	else{
    		loginPaneUsername.clear();
    		loginPanePassword.clear();
    	}
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}