package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import Server.DatabaseServer;
import javafx.stage.StageStyle;

public class LoginController {
    @FXML
    private TextField loginPaneUsername;
    @FXML
    private TextField loginPanePassword;
    @FXML
    private Pane loginPaneMain;
    @FXML private Label userError;


    Stage stage;

    @FXML
    public void login(ActionEvent event) throws Exception{
    	DatabaseServer en = new DatabaseServer();
    	if(en.login(loginPaneUsername.getText(), loginPanePassword.getText())){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/calendarView.fxml"));
                loader.setController(new CalendarViewController(en));
                Parent screen = loader.load();
                stage = (Stage) loginPaneMain.getScene().getWindow();
                stage.setScene(new Scene(screen));
                stage.setTitle("Calendar");
                stage.show();
                userError.setVisible(false);
            }
            catch (Exception e) { System.out.print(e);}
    	}
    	else{
    		loginPaneUsername.clear();
    		loginPanePassword.clear();
            userError.setVisible(true);
            userError.setStyle("-fx-text-fill: red");
            userError.setText("Username or password do not\n match or do not exist.");

    	}
    }
    @FXML
     public void newUser(ActionEvent event) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/createUserView.fxml"));
            stage = (Stage) loginPaneMain.getScene().getWindow();
            Parent screen = loader.load();
            stage.setScene(new Scene(screen));
            stage.show();
        }
        catch (Exception e) { System.out.print(e);}
    }

    @FXML
    public void getPassword(ActionEvent event) throws Exception{
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/resetPasswordView.fxml"));
//            loader.setController(new EmailController());
//            Parent screen = (Parent) loader.load();
//            stage = new Stage();
//            stage.setTitle("Retrieve password");
//            stage.setScene(new Scene(screen, 500, 300));
//            stage.show();
//        }
//        catch (Exception e) { System.out.print(e);}
    }

    @FXML
    public void loginWithEnter(KeyEvent event) throws Exception{
        DatabaseServer en = new DatabaseServer();
        try{
            if(event.getCode() == KeyCode.ENTER){
                if(en.login(loginPaneUsername.getText(), loginPanePassword.getText())){
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/calendarView.fxml"));
                        loader.setController(new CalendarViewController(en));
                        Parent screen = loader.load();
                        stage = (Stage) loginPaneMain.getScene().getWindow();
                        stage.setScene(new Scene(screen));
                        stage.setTitle("Calendar");
                        stage.show();
                        userError.setVisible(false);
                    }
                    catch (Exception e) { System.out.print(e);}
                }
                else{
                    loginPaneUsername.clear();
                    loginPanePassword.clear();
                    userError.setVisible(true);
                    userError.setStyle("-fx-text-fill: red");
                    userError.setText("Username or password do not\n match or do not exist.");

                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
