package Controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import Controller.StageController;
import Server.DatabaseServer;

public class loginController{
    @FXML
    private TextField loginPaneUsername;
    @FXML
    private TextField loginPanePassword;
    @FXML
    private Pane loginPaneMain;
    Stage stage;

    @FXML
    public void login(ActionEvent event) throws Exception{
    	DatabaseServer en = new DatabaseServer();
    	if(en.login(loginPaneUsername.getText(), loginPanePassword.getText())){
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("monthViewPane.fxml"));
//    		Istedenfor å deklarere kontroller i fxml, så gjøres dette
//    		"manuelt" for å videreføre DatabaseServer-objektet

    		loader.setController(new MonthViewController(en));
            Parent screen = loader.load();
    		stage = (Stage) loginPaneMain.getScene().getWindow();
    		stage.setScene(new Scene(screen));
    		stage.show();
    	}
    	else{
    		loginPaneUsername.clear();
    		loginPanePassword.clear();
    	}
    }
    @FXML
    public void newUser(ActionEvent event) throws Exception{
        try {
//Need to try some stuff with the stageController here
//            StageController newView = new StageController();
//            newView.setStage("creatUserPane.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/createUserPane.fxml"));
            stage = (Stage) loginPaneMain.getScene().getWindow();
            Parent screen = loader.load();
            stage.setScene(new Scene(screen));
            stage.show();
        }
        catch (Exception e) { System.out.print(e);}


    }
}
