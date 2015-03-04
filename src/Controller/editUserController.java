package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Model.User;
import Server.DatabaseServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class editUserController implements Initializable{
    User user;
    Stage stage;

    @FXML 
    TextField editUserPaneFirst;
    @FXML 
    TextField editUserPaneLast;
    @FXML 
    TextField editUserPaneEmail;
    @FXML 
    TextField editUserPanePhone;
    @FXML 
    TextField editUserPaneUsername;
    @FXML 
    PasswordField editUserPaneOldPassword;
    @FXML 
    PasswordField editUserPaneNewPassword;
    @FXML 
    PasswordField editUserPaneRepeatPassword;
   
    @FXML 
    Label phoneError;
    @FXML 
    Label emailError;
    @FXML 
    Label oldPwError;
    @FXML 
    Label newPw2Error;
    
    @FXML 
    AnchorPane editUserPane;
    
    DatabaseServer server;

    public editUserController(DatabaseServer databaseServer){
        server = databaseServer;
        try {
            user = databaseServer.getUser();
        }
        catch (Exception e){
            System.out.println("User not found.");
        }
        
        editUserPaneFirst = new TextField();
        editUserPaneLast = new TextField();
        editUserPaneEmail = new TextField();  
        editUserPaneUsername = new TextField();
        editUserPanePhone = new TextField(); 
        initialize(null, null);
    }

    private void validatePhone(){
        if(!(editUserPanePhone.getText().replaceAll(" ", "").length() == 8)){
            editUserPanePhone.clear();
            phoneError.setStyle("-fx-text-fill: red");
            phoneError.setText("Phone number has to be extactly eight digits.");
        }else{
            phoneError.setVisible(false);}
    }

    private void validateMail() {
        String email = editUserPaneEmail.getText();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches()){
            editUserPaneEmail.clear();
            emailError.setStyle("-fx-text-fill: red");
            emailError.setText("Email-address has to contain '@', and a domain.");
        }else{
            emailError.setVisible(false);}
    }

    private void validatePassword(){
        if (!editUserPaneOldPassword.equals(user.getPassword())){
            editUserPaneOldPassword.clear();
            oldPwError.setStyle("-fx-text-fill: red");
            oldPwError.setText("Old password does not match.");
            oldPwError.setVisible(true);
        }else{
            oldPwError.setVisible(false);
        }
        if(editUserPaneNewPassword.getText().replaceAll(" ", "").length() < 8){
            editUserPaneNewPassword.clear();
            editUserPaneRepeatPassword.clear();
            newPw2Error.setStyle("-fx-text-fill: red");
            newPw2Error.setText("Password needs to be at least 8 characters long.");
        }else{
            newPw2Error.setVisible(false);}
        if(!editUserPaneNewPassword.getText().equals(editUserPaneRepeatPassword.getText())){
            editUserPaneNewPassword.clear();
            editUserPaneRepeatPassword.clear();
            newPw2Error.setStyle("-fx-text-fill: red");
            newPw2Error.setText("The passwords do not match.");
            newPw2Error.setVisible(true);
        }else{
            newPw2Error.setVisible(false);}
    }
    @FXML
    public void updateAction(ActionEvent event) throws Exception{

        if(!isEmptyFields()){
            if (!editUserPanePhone.getText().trim().isEmpty() && !editUserPaneEmail.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().trim().isEmpty() && !editUserPaneRepeatPassword.getText().trim().isEmpty()){
                validatePhone();
                validatePassword();
                validateMail();
                updateUser();
            }else if(!editUserPaneEmail.getText().trim().isEmpty() && !editUserPanePhone.getText().trim().isEmpty()){
                validateMail();
                validatePhone();
                updateUser();
            }else if (!editUserPanePhone.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty()){
                validatePhone();
                validatePassword();
                updateUser();
            }else if (!editUserPaneEmail.getText().trim().isEmpty() && editUserPaneOldPassword.getText().trim().isEmpty()){
                validatePassword();
                validateMail();
                updateUser();
            }
        }

    }
    private boolean mailChanged(){
        if (editUserPaneEmail.getText().trim().equals(user.getEmail()) || editUserPaneEmail.getText().isEmpty()){
            return false;
        }
        return true;
    }
    private boolean pwChanged(){
        if (editUserPaneNewPassword.getText().trim().equals(user.getPassword()) || editUserPaneNewPassword.getText().isEmpty()){
            return false;
        }
        return true;
    }
    private boolean phoneChanged(){
        if (editUserPanePhone.getText().trim().equals(user.getPhone()) || editUserPanePhone.getText().isEmpty()){
            return false;
        }
        return true;
    }
    public void updateUser() throws Exception{
        if (mailChanged()){
            user.setEmail(editUserPaneEmail.getText());
        }
        if (phoneChanged()){
            user.setPhone(editUserPanePhone.getText());
        }
        if (pwChanged()){
            user.setPassword(editUserPaneNewPassword.getText());
        }
        server.editUser(user);
        switchView();
    }

    public boolean isEmptyFields(){
        if (editUserPaneEmail.getText().trim().isEmpty() || editUserPanePhone.getText().trim().isEmpty() || editUserPaneNewPassword.getText().trim().isEmpty() || editUserPaneNewPassword.getText().trim().isEmpty() || editUserPaneRepeatPassword.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    @FXML
    public void cancelAction(ActionEvent event) throws Exception{
    	switchView();
    }
    private void switchView() throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("mainViewMonth.fxml"));
        loader.setController(new MonthViewController(server));
        stage = (Stage) editUserPane.getScene().getWindow();
        Parent scene = loader.load();
        //stage.setScene(new Scene(loader.load()));
        stage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		editUserPaneFirst.setText(user.getFirstname());
        editUserPaneFirst.setDisable(true);
        editUserPaneLast.setText(user.getLastname());
        editUserPaneLast.setDisable(true);
        editUserPaneUsername.setText(user.getUsername());
        editUserPaneUsername.setDisable(true);
        editUserPaneEmail.setText(user.getEmail());
        editUserPaneEmail.setDisable(true);
        editUserPanePhone.setText(user.getPhone());
		
	}

}
