package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import Model.PersonalAppointment;
import Server.DatabaseServer;

public class appointmentController implements Initializable{
	@FXML Label apDate;
	@FXML Label apRoom;
	@FXML Label apTime;
	@FXML Label apDescription;
	DatabaseServer dbserver;
	PersonalAppointment pa;
	public appointmentController(DatabaseServer server, PersonalAppointment psa){
		dbserver = server;
		pa = psa;
		init();
		initialize(null, null);
	}
	void init(){
		apDate = new Label();
		apRoom = new Label();
		apTime = new Label();
		apDescription = new Label();
	}
	
	@FXML 
	 public void cancelAppointment(ActionEvent event){
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	public void editAppointment(ActionEvent event){
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		apDate.setText(pa.getDato() + "");
		apRoom.setText(pa.getRomnavn() + "");
		apTime.setText(pa.getStartTid() + "-" + pa.getSluttTid());
		apDescription.setText(pa.getBeskrivelse());
	}
	
}
