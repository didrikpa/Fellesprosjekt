package Controller;

import java.awt.*;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
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
    @FXML private javafx.scene.control.Label fNameError;
    @FXML private Label lNameError;
    @FXML private Label emailError;
    @FXML private Label phoneError;
    @FXML private Label userError;
    @FXML private Label pw1Error;
    @FXML private Label pw2Error;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/loginPane.fxml"));
        stage = (Stage) createUserPaneMain.getScene().getWindow();
        Parent screen = loader.load();
        stage.setScene(new Scene(screen));
        stage.show();
    }
    private void createUser() throws Exception{
            User user = new User();
            user.setFirstname(createUserPaneFirst.getText().trim());
            user.setLastname(createUserPaneLast.getText().trim());
            user.setUsername(createUserPaneUsername.getText().trim());
            user.setPassword(createUserPanePassword.getText().trim());
            user.setEmail(createUserPaneEmail.getText().trim());
            user.setPhone(createUserPanePhone.getText().trim());
            en.addUser(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/loginPane.fxml"));
            stage = (Stage) createUserPaneMain.getScene().getWindow();
            Parent screen = loader.load();
            stage.setScene(new Scene(screen));
            stage.show();
    }
    private void validateNames(){
        if(createUserPaneFirst.getText().replaceAll(" ", "").length() < 2){
            createUserPaneFirst.clear();
            fNameError.setStyle("-fx-text-fill: red");
            fNameError.setText("First name has to be longer than two characters.");
        }else{fNameError.setVisible(false);}
        if(createUserPaneLast.getText().replaceAll(" ", "").length() < 2){
            createUserPaneLast.clear();
            lNameError.setStyle("-fx-text-fill: red");
            lNameError.setText("Last name has to be longer than two characters.");
        }
        else{
        lNameError.setVisible(false);}
    }

    private void validateMail() {
        String email = createUserPaneEmail.getText();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches()){
            createUserPaneEmail.clear();
            emailError.setStyle("-fx-text-fill: red");
            emailError.setText("Email-address has to contain '@', and a domain.");
        }else{
        emailError.setVisible(false);}
    }
    private void validatePhone(){
        if(!(createUserPanePhone.getText().replaceAll(" ", "").length() == 8)){
            createUserPanePhone.clear();
            phoneError.setStyle("-fx-text-fill: red");
            phoneError.setText("Phone number has to be extactly eight digits.");
        }else{
        phoneError.setVisible(false);}
    }
    private void validateUsername() throws SQLException{
        if(createUserPaneUsername.getText().length() > 5){
            if(en.userExist(createUserPaneUsername.getText())){
                createUserPaneUsername.clear();
                userError.setStyle("-fx-text-fill: red");
                userError.setText("Username already exists.");
            }
        }
        else if (createUserPaneUsername.getText().length() < 5){
            createUserPaneUsername.clear();
            userError.setStyle("-fx-text-fill: red");
            userError.setText("Username has to be at least 5 characters.");
        }else{
        userError.setVisible(false);}
    }
    private void validatePassword(){
        if(createUserPanePassword.getText().replaceAll(" ", "").length() < 8){
            createUserPanePassword.clear();
            createUserPanePasswordRepeat.clear();
            pw1Error.setStyle("-fx-text-fill: red");
            pw1Error.setText("Password needs to be at least 8 characters long.");
        }else{
        pw1Error.setVisible(false);}
        if(!createUserPanePassword.getText().equals(createUserPanePasswordRepeat.getText())){
            createUserPanePassword.clear();
            createUserPanePasswordRepeat.clear();
            pw2Error.setStyle("-fx-text-fill: red");
            pw2Error.setText("The passwords do not match.");
            pw2Error.setVisible(true);
        }else{
        pw2Error.setVisible(false);}
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
