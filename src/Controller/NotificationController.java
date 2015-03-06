package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

public class NotificationController {
    Stage stage;

    public void exitNotifications(ActionEvent e) throws Exception{
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }

}
