package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import Server.*;
import Model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyGroupController implements Initializable {
	@FXML Pane myGroupsPopUp;
	@FXML ListView<Group> listviewGroups;
	ArrayList<Group> myGroups;
	DatabaseServer databaseServer = new DatabaseServer();
	Group group;
	CalendarViewController parent;

	public MyGroupController(DatabaseServer databaseServer, CalendarViewController par) throws Exception {
		this.databaseServer = databaseServer;
		this.parent = par;
		listviewGroups = new ListView<Group>();
		
	}
	@FXML
	public void closeWindow(ActionEvent e) throws Exception{
		parent.notifications();
        ((Node)(e.getSource())).getScene().getWindow().hide();
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
							parent.midViewEn.setMonth(parent.aar, parent.maned, newValue);
							parent.notifications();
							myGroupsPopUp.getScene().getWindow().hide();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			}
		});
	}
}
