package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Invite;
import Server.DatabaseServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class NotificationController implements Initializable{
    Stage stage;
    @FXML ListView<Invite> notificationList;
    @FXML Button notificationsAccept;
    @FXML Button notifictionsDecline;
    CalendarViewController parent;
    DatabaseServer server;
    public NotificationController(DatabaseServer dbserver, CalendarViewController par){
    	parent = par;
    	server = dbserver;
    	notificationList = new ListView<Invite>();
    	initialize(null, null);
    }
    @FXML
    public void exitNotifications(ActionEvent e) throws Exception{
        ((Node)(e.getSource())).getScene().getWindow().hide();
    }
    
    @FXML
    public void notificationsAccept(ActionEvent event) throws Exception {
    	ObservableList<Invite> invitasjoner = notificationList.getSelectionModel().getSelectedItems();
    	server.respond(invitasjoner.get(0),true);
    	notificationList.setItems(FXCollections.observableArrayList(server.getInvites()));
    	parent.monthB();
    	parent.monthF();
    }
    
    @FXML
    public void notificationsDecline(ActionEvent event) throws Exception {
    	ObservableList<Invite> invitasjoner = notificationList.getSelectionModel().getSelectedItems();
    	server.respond(invitasjoner.get(0),false);
    	notificationList.setItems(FXCollections.observableArrayList(server.getInvites()));
    }
    
    public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Invite> pas = null;
		try {
			pas = server.getInvites();
		} catch (Exception e) {
			e.printStackTrace();
		}
		notificationList.setItems(FXCollections.observableArrayList(pas));
	}
}
