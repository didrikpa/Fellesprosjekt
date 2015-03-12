package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import Server.*;
import Model.*;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TodaysEventsController implements Initializable {
	@FXML AnchorPane window;
	@FXML ListView<PersonalAppointment> todaysEventsList;
	DatabaseServer databaseServer = new DatabaseServer();
	CalendarViewController parent;
	Date datoen;

	public TodaysEventsController(DatabaseServer dbServer, Date date, CalendarViewController par) throws Exception {
		this.databaseServer = dbServer;
		this.datoen = date;
		todaysEventsList = new ListView<PersonalAppointment>();
		parent = par;
		initialize(null, null);
	}
	@FXML
	public void closeWindow(ActionEvent e) throws Exception{
		todaysEventsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ObservableList<PersonalAppointment> pap = todaysEventsList.getSelectionModel().getSelectedItems();
        if(pap.size()>0){
		PersonalAppointment pa = pap.get(0);
		parent.openAppointment(pa);
		((Node)(e.getSource())).getScene().getWindow().hide();
        }
    }
	
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<PersonalAppointment> pas = null;
		try {
			if(parent.groupCal == null)pas = databaseServer.getAppointment(datoen);
			else{
				pas = databaseServer.getAppointment(datoen,parent.groupCal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		todaysEventsList.setItems(FXCollections.observableArrayList(pas));
	}
}
