package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Model.PersonalAppointment;
import Server.DatabaseServer;

public class AppointmentController implements Initializable{
	@FXML Label apDate;
	@FXML Label apRoom;
	@FXML Label apTime;
	@FXML Label apDescription;
	CalendarViewController cvc;
	DatabaseServer dbserver;
	Stage stage;
	PersonalAppointment pa;
	public AppointmentController(DatabaseServer server, PersonalAppointment psa, CalendarViewController cc){
		dbserver = server;
		pa = psa;
		cvc = cc;
		init();
		initialize(null, null);
	}
	void init(){
		apDate = new Label();
		apRoom = new Label();
		apTime = new Label();
		apDescription = new Label();
		stage = null;
	}
	
	@FXML 
	 public void cancelAppointment(ActionEvent event){
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	public void editAppointment(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/EditEventController.fxml"));
		fxmlLoader.setController(new EditEventController(dbserver, pa, cvc));
		stage = new Stage();
		stage.setTitle("Appointment - ID." + pa.getAvtaleID());
		stage.setScene(new Scene((Parent) fxmlLoader.load()));
		stage.show();
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
