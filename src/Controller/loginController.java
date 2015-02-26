import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    	try {en.valid(loginPaneUsername.getText(), loginPanePassword.getText());} 
    	catch (SQLException e) {e.printStackTrace();}
    	if(en.gyldig){
    		en.Brukernavn = loginPaneUsername.getText();
    		en.Passord = loginPanePassword.getText();
    		stage = (Stage) loginPaneMain.getScene().getWindow();
    		stage.close();
    	}
    	else{
    		loginPaneUsername.clear();
    		loginPanePassword.clear();
    	}
    }
    @FXML
    public void newUser(ActionEvent event) throws Exception{
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("createUserPane.fxml"));
    		stage = (Stage) loginPaneMain.getScene().getWindow();
    		stage.setScene(new Scene(loader.load()));
    		stage.show();
    }
}