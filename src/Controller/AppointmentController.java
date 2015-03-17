package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import Model.Alarm;
import Model.Invite;
import Model.PersonalAppointment;
import Server.DatabaseServer;

public class AppointmentController implements Initializable{
	@FXML Button changeAppointment;
	@FXML Button createAlarm;
	@FXML Button deleteAppointment;
	@FXML Label apDate;
	@FXML Label apRoom;
	@FXML Label apTime;
	@FXML Label apDescription;
	@FXML Label invitedLabel;
	@FXML Label opprettetAv;
	@FXML ListView<String> invitedList;
	@FXML TextField alarmIn;
	@FXML ComboBox<String> alarmChoice;
	@FXML ToggleButton toogleGoing;
	@FXML ToggleButton toogleNotGoing;
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
		createAlarm = new Button();
		deleteAppointment = new Button();
		changeAppointment = new Button();
		alarmChoice = new ComboBox<String>();
		apDate = new Label();
		apRoom = new Label();
		apTime = new Label();
		opprettetAv = new Label();
		apDescription = new Label();
		stage = null;
		toogleGoing = new ToggleButton();
		toogleNotGoing = new ToggleButton();
		invitedList = new ListView<String>();
	}
	
	@FXML 
	 public void cancelAppointment(ActionEvent event) throws Exception{
		cvc.notifications();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	public void toogleGoing(ActionEvent event) throws IOException{
		
	}
	
	@FXML
	public void toogleNotGoing(ActionEvent event) throws IOException{
		
	}
	
	@FXML
	public void editAppointment(ActionEvent event) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/editEventView.fxml"));
		fxmlLoader.setController(new EditEventController(dbserver, pa, cvc));
		stage = new Stage();
		stage.setTitle("Appointment - ID." + pa.getAvtaleID());
		stage.setScene(new Scene((Parent) fxmlLoader.load()));
		stage.show();
		cvc.notifications();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	public void createAlarm(ActionEvent event) {
		Alarm alarm = new Alarm(dbserver);
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
		try {
			int not = Integer.parseInt(alarmIn.getText());
			if(not == 0){
				alarm.setTidspunkt(null);
			}
			if(((String)alarmChoice.getValue()).equalsIgnoreCase("minutes")){
				ts.setMinutes(ts.getMinutes()+not);
				alarm.setTidspunkt(ts);
			}
			if(((String)alarmChoice.getValue()).equalsIgnoreCase("hours")){
				ts.setHours(ts.getHours()+not);
				alarm.setTidspunkt(ts);
			}
			if(((String)alarmChoice.getValue()).equalsIgnoreCase("days")){
				ts.setDate(ts.getDay()+not);
				alarm.setTidspunkt(ts);
			}
			if(alarm.getTidspunkt()!= null){
				alarm.setAvtaleID(pa.getAvtaleID());
				alarm.setBrukernavn(dbserver.Username);
				dbserver.setAlarm(alarm);
				alarmChoice.setVisible(false);
				alarmIn.setVisible(false);
				createAlarm.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@FXML
	public void deleteAppointment(ActionEvent event) throws Exception {
		dbserver.respondOnInvite(dbserver.getInvite(dbserver.getParentEvent(pa)), false);
		dbserver.removeAppointment(pa);
		cvc.monthB();
		cvc.monthF();
		cvc.notifications();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	private void setAlarmComboValues() {
		List<String> list = new ArrayList<String>();
		list.add("minutes");
		list.add("hours");
		list.add("days");
		ObservableList<String> choices = FXCollections
				.observableArrayList(list);
		alarmChoice.setItems(choices);
		alarmChoice.setValue(choices.get(0));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setAlarmComboValues();
		toogleGoing.setVisible(false);
		toogleNotGoing.setVisible(false);
		ArrayList<String>status = new ArrayList<String>();
		try {
			for(Invite inv:dbserver.invitesSent(dbserver.getParentEvent(pa))){
				status.add(inv.getBrukernavn() + " - " + inv.isGodtatt());
			}
			invitedList.setItems(FXCollections.observableArrayList(status));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			if(cvc.groupCal != null || !pa.getOpprettetAv().equalsIgnoreCase(dbserver.Username) || dbserver.isChildEvent(pa)){
				changeAppointment.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		apDate.setText(pa.getDato() + "");
		apRoom.setText(pa.getRomnavn() + "");
		apTime.setText(pa.getStartTid() + "-" + pa.getSluttTid());
		apDescription.setText(pa.getBeskrivelse());
		opprettetAv.setText(pa.getOpprettetAv());
	}

}
