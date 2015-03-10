package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import Server.*;
import Model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyGroupController implements Initializable {
	@FXML
	ListView<Group> listviewGroups;
	ArrayList<Group> myGroups;
	DatabaseServer databaseServer = new DatabaseServer();
	Group group;
	CalendarViewController parent;

	public MyGroupController(DatabaseServer databaseServer, CalendarViewController par) throws Exception {
		this.databaseServer = databaseServer;
		this.parent = par;
		listviewGroups = new ListView<Group>();
		
	}

	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Group> pas = null;
		try {
			pas = databaseServer.getGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		listviewGroups.setItems(FXCollections.observableArrayList(pas));
		listviewGroups.getSelectionModel().selectedItemProperty()
		.addListener(new ChangeListener<Group>() {
			@Override
			public void changed(
					ObservableValue<? extends Group> observable,
					Group oldValue, Group newValue) {
					if(!parent.midViewEn.equals(null)){
						try {
							parent.groupCal = newValue;
							parent.midViewEn.setMonthGroup(parent.aar, parent.maned, newValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}
		});
	}
}
