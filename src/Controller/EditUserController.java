package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import Model.User;
import Server.DatabaseServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditUserController implements Initializable{
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
    Label newPw1Error;
    @FXML 
    AnchorPane editUserPane;
    
    DatabaseServer server;

    public EditUserController(DatabaseServer databaseServer){
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
            phoneError.setText("Phone number has to be exactly eight digits.");
            phoneError.setVisible(true);
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
        newPw2Error.setText("Skal prokke");
        newPw2Error.setVisible(true);

        if (!editUserPaneOldPassword.getText().equals(user.getPassword())){
            editUserPaneOldPassword.clear();
            oldPwError.setStyle("-fx-text-fill: red");
            oldPwError.setText("Old password does not match.");
            oldPwError.setVisible(true);
            }
        else{
            oldPwError.setVisible(false);
            }

        if((editUserPaneNewPassword.getText().replaceAll(" ", "").length()) < 8){
            editUserPaneOldPassword.clear();
            editUserPaneNewPassword.clear();
            editUserPaneRepeatPassword.clear();
            newPw1Error.setStyle("-fx-text-fill: red");
            newPw1Error.setText("Password needs to be at least\n 8 characters long.");
            newPw1Error.setVisible(true);
        }else{
            newPw1Error.setVisible(false);}
        if(!editUserPaneNewPassword.getText().trim().equals(editUserPaneRepeatPassword.getText().trim())){
            editUserPaneNewPassword.clear();
            editUserPaneOldPassword.clear();
            editUserPaneRepeatPassword.clear();
            newPw2Error.setStyle("-fx-text-fill: red");
            newPw2Error.setText("The new passwords do not match.");
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
                if(!editUserPanePhone.getText().trim().isEmpty() && !editUserPaneEmail.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().trim().isEmpty() && !editUserPaneRepeatPassword.getText().trim().isEmpty()){
                    updateUser(event);
                }

            }else if(!editUserPaneEmail.getText().trim().isEmpty() && !editUserPanePhone.getText().trim().isEmpty() && isPasswordEmpty()){
                validateMail();
                validatePhone();
                if (!editUserPaneEmail.getText().trim().isEmpty() && !editUserPanePhone.getText().trim().isEmpty() && isPasswordEmpty()){
                    updateUser(event);
                }

            }else if (!editUserPanePhone.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                validatePhone();
                validatePassword();
                if (!editUserPanePhone.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                    updateUser(event);
                }

            }else if (!editUserPaneEmail.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                validatePassword();
                validateMail();
                if (!editUserPaneEmail.getText().trim().isEmpty() && !editUserPaneOldPassword.getText().trim().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                    updateUser(event);
                }

            }
            else if (!editUserPaneOldPassword.getText().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                validatePassword();
                if (!editUserPaneOldPassword.getText().isEmpty() && !editUserPaneNewPassword.getText().isEmpty() && !editUserPaneRepeatPassword.getText().isEmpty()){
                    updateUser(event);
                }

            }else if (!editUserPanePhone.getText().isEmpty() && isPasswordEmpty()){
                validatePhone();
                if (!editUserPanePhone.getText().isEmpty()){
                    updateUser(event);
                }

            }else if (!editUserPaneEmail.getText().isEmpty() && isPasswordEmpty()){
                validateMail();
                if (!editUserPaneEmail.getText().isEmpty()){
                    updateUser(event);
                }

            }else if(!editUserPaneOldPassword.getText().isEmpty()){
                validatePassword();
            }else if(!editUserPaneOldPassword.getText().isEmpty() && !editUserPaneNewPassword.getText().isEmpty()){
                validatePassword();
            }else if (!editUserPaneOldPassword.getText().isEmpty() && editUserPaneRepeatPassword.getText().isEmpty()){
                validatePassword();
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
    public void updateUser(ActionEvent event) throws Exception{
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
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public boolean isEmptyFields(){
        if (!editUserPaneEmail.getText().trim().isEmpty() || !editUserPanePhone.getText().trim().isEmpty() || !editUserPaneNewPassword.getText().trim().isEmpty() || !editUserPaneNewPassword.getText().trim().isEmpty() || !editUserPaneRepeatPassword.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }

    @FXML
    public void cancelAction(ActionEvent event) throws Exception{
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public boolean isPasswordEmpty(){
        if(editUserPaneOldPassword.getText().isEmpty() && editUserPaneNewPassword.getText().isEmpty() && editUserPaneRepeatPassword.getText().isEmpty()){
            return true;
        }
        return false;
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
        editUserPanePhone.setText(user.getPhone());
		
	}

}
