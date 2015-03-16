package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Model.Alarm;
import Model.Invite;
import Model.Påminnelse;
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
	@FXML ListView<Påminnelse> notificationList;
	@FXML Button notificationsAccept;
	@FXML Button notifictionsDecline;
	ArrayList<Påminnelse> pas;
	CalendarViewController parent;
	DatabaseServer server;
	
	public NotificationController(DatabaseServer dbserver, CalendarViewController par){
		parent = par;
		server = dbserver;
		pas = new ArrayList<Påminnelse>();
		notificationList = new ListView<Påminnelse>();
		initialize(null, null);
	}
	@FXML
	public void exitNotifications(ActionEvent event) throws Exception{
		try{
            		parent.notifications();
        	}catch(Exception e){
            		System.out.println(e);
        	}
		parent.notifications();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	public void notificationsAccept(ActionEvent event) throws Exception {
		ObservableList<Påminnelse> invitasjoner = notificationList.getSelectionModel().getSelectedItems();
		if(invitasjoner.get(0) instanceof Invite){
			Invite inv = (Invite)invitasjoner.get(0);
			server.respondOnInvite(inv,true);
			loadList();
			notificationList.setItems(FXCollections.observableArrayList(pas));
			parent.monthB();
			parent.monthF();
			parent.notifications();
		}
		if(invitasjoner.get(0) instanceof Alarm){
			Alarm larm = (Alarm)invitasjoner.get(0);
			server.removeAlarm(larm);
			loadList();
			notificationList.setItems(FXCollections.observableArrayList(pas));
		}
	}

	@FXML
	public void notificationsDecline(ActionEvent event) throws Exception {
		ObservableList<Påminnelse> invitasjoner = notificationList.getSelectionModel().getSelectedItems();
		if(invitasjoner.get(0) instanceof Invite){
			Invite inv = (Invite)invitasjoner.get(0);
			server.respondOnInvite(inv,false);
			loadList();
			notificationList.setItems(FXCollections.observableArrayList(pas));
			parent.monthB();
			parent.monthF();
			parent.notifications();
		}
		if(invitasjoner.get(0) instanceof Alarm){
			Alarm larm = (Alarm)invitasjoner.get(0);
			server.removeAlarm(larm);
			loadList();
			notificationList.setItems(FXCollections.observableArrayList(pas));
		}

	}
	
	private void loadList() throws Exception {
		pas.clear();
		for(Invite ene : server.getMyInvites()){
			pas.add(ene);
		}
		for(Alarm tone : server.getAlarm()){
			pas.add(tone);
		}
	}
	
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			loadList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		notificationList.setItems(FXCollections.observableArrayList(pas));
	}
}
