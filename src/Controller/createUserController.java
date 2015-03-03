package Controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import Server.*;
import Model.User;
import sun.reflect.annotation.ExceptionProxy;

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
    DatabaseServer en;
    @FXML
    public void createAction(ActionEvent event) throws Exception{
        en = new DatabaseServer();
        validateNames();
        validateMail();
        validatePhone();
        validatePassword();
        validateUsername();
        if(!emptyFields()){
            createUser();
        }
    }
    @FXML
    public void cancelAction(ActionEvent event) throws Exception{
        stage = (Stage) createUserPaneMain.getScene().getWindow();
        stage.close();
    }
    private void createUser() throws Exception{
        User user = new User();
		user.setFirstname(createUserPaneFirst.getText());
		user.setLastname(createUserPaneLast.getText());
		user.setUsername(createUserPaneUsername.getText());
		user.setPassword(createUserPanePassword.getText());
		user.setEmail(createUserPaneEmail.getText());
		user.setPhone(createUserPanePhone.getText());
        en.addUser(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPane.fxml"));
        stage = (Stage) createUserPaneMain.getScene().getWindow();
        Parent screen = loader.load();
        stage.setScene(new Scene(screen));
        stage.show();
    }
    private void validateNames(){
        if(createUserPaneFirst.getText().replaceAll(" ", "").length() < 2){
            createUserPaneFirst.clear();
        }
        if(createUserPaneLast.getText().replaceAll(" ", "").length() < 2){
            createUserPaneLast.clear();
        }
    }

    private void validateMail() {
        String email = createUserPaneEmail.getText();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches()){
            createUserPaneEmail.clear();
        }
    }
    private void validatePhone(){
        if(!(createUserPanePhone.getText().replaceAll(" ", "").length() == 8)){
            createUserPanePhone.clear();
        }
    }
    private void validateUsername() throws SQLException{
        if(createUserPaneUsername.getText().length() > 5){
            if(en.userExist(createUserPaneUsername.getText())){
                createUserPaneUsername.clear();
            }
            else{

            }
        }
        else{
            createUserPaneUsername.clear();
        }
    }
    private void validatePassword(){
        if(createUserPanePassword.getText().replaceAll(" ", "").length() < 8){
            createUserPanePassword.clear();
            createUserPanePasswordRepeat.clear();
        }
        if(!createUserPanePassword.getText().equals(createUserPanePasswordRepeat.getText())){
            createUserPanePassword.clear();
            createUserPanePasswordRepeat.clear();
        }
    }
    private boolean emptyFields(){
        if(createUserPaneFirst.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPaneLast.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPaneUsername.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPanePassword.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPanePasswordRepeat.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPaneEmail.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        if(createUserPanePhone.getText().replaceAll(" ", "").isEmpty()){
            return true;
        }
        return false;
    }
}
