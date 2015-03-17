package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import Server.*;
import Model.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EditEventController implements Initializable, EventController {
	@FXML
	AnchorPane createEventViewMainPane;
	@FXML
	DatePicker createEventViewDatePicker;
	@FXML
	ComboBox<Integer> createEventViewStartHours;
	@FXML
	ComboBox<Integer> createEventViewStartMinutes;
	@FXML
	ComboBox<Integer> createEventViewEndHours;
	@FXML
	ComboBox<Integer> createEventViewEndMinutes;
	@FXML
	ComboBox<String> createEventViewRoom;
	@FXML
	TextField createEventViewSearch;
	@FXML
	TextArea createEventViewTextArea;
	@FXML
	CheckBox allDayCheck;
	@FXML
	ComboBox<String> createEventViewGroup;
	@FXML
	private Label roomError;
	@FXML
	private Label startError;
	@FXML
	private Label endError;
	@FXML
	private Label dateError;

	@FXML
	Button inviteUserButton;
	@FXML
	RadioButton personalRadio;
	@FXML
	RadioButton meetingRadio;
	@FXML
	Label roomLabel;
	@FXML
	Label groupLabel;
	@FXML
	TextField notifyInt;
	@FXML
	ComboBox<String> notifyCombo;
	@FXML
	ListView<User> userList;
	@FXML
	ListView<User> participantList;

	int gruppeid;
	PersonalAppointment personalAppointment;
	PersonalAppointment opprinneligPa;
	DatabaseServer databaseServer = new DatabaseServer();
	Alarm alarm;
	CalendarViewController parent;
	Stage stage;
	ArrayList<User> selectedUsers = new ArrayList<User>();

	public EditEventController(DatabaseServer server,PersonalAppointment pa ,CalendarViewController pt) {
		databaseServer = server;
		alarm = new Alarm(databaseServer);
		personalAppointment = pa;
		opprinneligPa = fillIt(pa);
		parent = pt;
		gruppeid = 0;
		init();
		initialize(null, null);
	}

	private PersonalAppointment fillIt(PersonalAppointment a){
		PersonalAppointment ab = new PersonalAppointment();
		ab.setAvtaleID(a.getAvtaleID());
		ab.setBeskrivelse(a.getBeskrivelse());
		ab.setDato(a.getDato());
		ab.setOpprettetAv(a.getOpprettetAv());
		ab.setRomnavn(a.getRomnavn());
		ab.setStartTid(a.getStartTid());
		ab.setSluttTid(a.getSluttTid());
		return ab;

	}

	private void init(){
		createEventViewDatePicker = new DatePicker();
		createEventViewStartMinutes = new ComboBox<Integer>();
		createEventViewEndMinutes = new ComboBox<Integer>();
		createEventViewStartHours = new ComboBox<Integer>();
		createEventViewEndHours = new ComboBox<Integer>();
		createEventViewRoom = new ComboBox<String>();
		createEventViewTextArea = new TextArea();
		createEventViewSearch = new TextField();
		createEventViewGroup = new ComboBox<String>();
		participantList = new ListView<User>();
		notifyCombo = new ComboBox<String>();
	}

	public void initialize(URL url, ResourceBundle resourceBundle) {
		java.util.Date fire = new java.util.Date(personalAppointment.getDato().getTime());
		LocalDate date = fire.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		createEventViewDatePicker.setValue(date);
		setDatePicker(createEventViewDatePicker);
		createEventViewDatePicker.setValue(personalAppointment.getDato().toLocalDate());
		setHourFrom();
		setMinuteFrom();
		setHourToo();
		setMinuteToo();
		createEventViewStartMinutes.setValue(personalAppointment.getStartTid().getMinutes());
		createEventViewStartHours.setValue(personalAppointment.getStartTid().getHours());
		createEventViewEndMinutes.setValue(personalAppointment.getSluttTid().getMinutes());
		createEventViewEndHours.setValue(personalAppointment.getSluttTid().getHours());
		createEventViewRoom.setValue(personalAppointment.getRomnavn());
		setRoom();
		if(createEventViewRoom.getValue().equalsIgnoreCase("PersonalRoom")){
			createEventViewSearch.setDisable(true);
			createEventViewRoom.setDisable(true);
			createEventViewGroup.setDisable(true);
		}
		createEventViewTextArea.setText(personalAppointment.getBeskrivelse());
		setNotifyComboValues();
		setGroups();
		try {
			createEventViewGroup.setValue(databaseServer.getGroup(opprinneligPa.getGruppeID()).getGroupName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		createEventViewGroup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if((createEventViewGroup.getSelectionModel().getSelectedItem()).equalsIgnoreCase("Ny gruppe")){
					gruppeid = 0;
					createEventViewSearch.setDisable(false);
					try {
						participantList.setItems(FXCollections.observableArrayList(new ArrayList<User>()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						gruppeid = databaseServer.getGroupId((createEventViewGroup.getSelectionModel().getSelectedItem()));
					} catch (Exception e) {
						System.out.println(e);
					}
					createEventViewSearch.setDisable(true);
					try {
						participantList.setItems(FXCollections.observableArrayList(new ArrayList<User>()));
						participantList.setItems(FXCollections.observableArrayList(databaseServer.getGroupMembers(gruppeid)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
	}


	@FXML
	public void searchUser() throws Exception {
		userList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ArrayList<User> users = new ArrayList<User>();
		users = userSearch(createEventViewSearch.getText(),
				databaseServer.getUsers());
		userList.setItems(FXCollections.observableArrayList(users));
	}

	@FXML
	public void inviteUser(ActionEvent event) throws Exception {
		participantList.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE);
		ObservableList<User> users = userList.getSelectionModel()
				.getSelectedItems();
		// Iterates through selected items and adds them to the selected user
		// list if not already there.
		for (int i = 0; i < users.size(); i++) {
			if (!selectedUsers.contains(users.get(i))) {
				selectedUsers.add(users.get(i));
			}
		}
		// Adds all the participants in the selected user list to the invited
		// user pan.
	}

	public void createGroup() throws Exception{
		ArrayList<User> al = selectedUsers;
		al.add(databaseServer.getUser());
		HashSet hs = new HashSet();
		hs.addAll(al);
		al.clear();
		al.addAll(hs);
		if(!databaseServer.groupExists(al)){ 		
			try{
				FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/Views/createGroupView.fxml")));
				fxmlLoader.setController(new CreateGroupController(databaseServer, al, this));
				stage = new Stage();
				stage.setTitle("Create group");
				stage.setScene(new Scene((Parent) fxmlLoader.load()));
				stage.show();
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		else{
			System.out.println("Group do exist");
		}
	}

	@FXML
	public void deleteInvitedUser(ActionEvent event) throws Exception {
		ObservableList<User> participants = participantList.getSelectionModel()
				.getSelectedItems();
		selectedUsers.removeAll(participants);
		participantList.setItems(FXCollections
				.observableArrayList(selectedUsers));
	}

	ArrayList<User> userSearch(String search, ArrayList<User> namesIn) {
		String searcher = search;
		searcher = searcher.toLowerCase();
		ArrayList<User> navnene = namesIn;
		ArrayList<User> namesOut = new ArrayList<User>();
		while (!searcher.equals("")) {
			for (User namn : navnene) {
				if ((namn.getFirstname() + "" + namn.getLastname())
						.toLowerCase().contains(searcher)) {
					if (!namesOut.contains(namn)) {
						namesOut.add(namn);
					}
				}
			}
			String[] con = searcher.split("");
			searcher = "";
			int ant = con.length - 1;
			for (int i = 0; i < ant; i++) {
				if (!(searcher.length() == ant) || searcher.length() < 3)
					searcher += con[i];
			}
		}
		return namesOut;
	}

	@FXML
	public static void setDatePicker(final DatePicker calender) {
		calender.setValue(LocalDate.now());
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {

					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isBefore(LocalDate.now())) {
							setDisable(true);
							setStyle("-fx-background-color: pink;");
						}
					}
				};
			}
		};
		calender.setDayCellFactory(dayCellFactory);
	}

	@FXML
	public void setHourFrom() {
		List<Integer> someVariableName = new ArrayList();
		for (int i = 0; i < 24; i++)
			someVariableName.add(i);
		ObservableList<Integer> hours = FXCollections
				.observableArrayList(someVariableName);
		createEventViewStartHours.setItems(hours);
		createEventViewStartHours.setValue(LocalTime.now().getHour() - 1);
	}

	@FXML
	public void setMinuteFrom() {
		List<Integer> someVariableName = new ArrayList();
		for (int i = 0; i < 60; i += 5)
			someVariableName.add(i);
		ObservableList<Integer> minutes = FXCollections
				.observableArrayList(someVariableName);
		createEventViewStartMinutes.setItems(minutes);
		createEventViewStartMinutes.setValue(5 * (Math.round((LocalTime.now()
				.getMinute() + 5) / 5)));
		createEventViewStartHours.setValue(LocalTime.now().getHour() + 1);
	}

	@FXML
	public void setNotifyComboValues() {
		List<String> list = new ArrayList<String>();
		list.add("minutes");
		list.add("hours");
		list.add("days");
		ObservableList<String> choices = FXCollections
				.observableArrayList(list);
		notifyCombo.setItems(choices);
		notifyCombo.setValue(choices.get(0));
	}

	@FXML
	public void setHourToo() {
		DecimalFormat formatter = new DecimalFormat("00");
		int n = createEventViewStartHours.getValue();
		List<Integer> hours = new ArrayList();
		for (int i = n; i < 24; i++)
			hours.add(i);
		ObservableList<Integer> availableHours = FXCollections
				.observableArrayList(hours);
		createEventViewEndHours.setItems(availableHours);
		createEventViewEndHours.setValue(n + 1);
	}

	@FXML
	public void setMinuteToo() {
		int j = createEventViewStartMinutes.getValue();
		List<Integer> someVariable = new ArrayList();
		for (int i = 0; i < 60; i += 5)
			someVariable.add(i);
		ObservableList<Integer> availableMinutes = FXCollections
				.observableArrayList(someVariable);
		createEventViewEndMinutes.setItems(availableMinutes);
		createEventViewEndMinutes.setValue(j);
	}

	@FXML
	public void setRoom() {
		try {
			ObservableList<String> roomlist = FXCollections
					.observableArrayList(databaseServer.getRoomName());
			roomlist.remove("PersonalRoom");
			createEventViewRoom.setItems(roomlist);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	public void setDate() {
		personalAppointment.setDato(java.sql.Date
				.valueOf(createEventViewDatePicker.getValue()));
	}

	private void setDato() {
		personalAppointment.setDato(java.sql.Date
				.valueOf(createEventViewDatePicker.getValue()));
	}

	@FXML
	public void setGroups(){
		try {
			ArrayList<String>grup = databaseServer.getGroupNames(databaseServer.Username);
			grup.add("Ny gruppe");
			Collections.reverse(grup);
			ObservableList<String> groupList = FXCollections.observableArrayList(grup);
			createEventViewGroup.setItems(groupList);
			createEventViewGroup.setValue(groupList.get(0));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	@FXML
	public boolean validateTime() {
		if (createEventViewEndHours.getValue() == createEventViewStartHours
				.getValue()
				&& createEventViewEndMinutes.getValue() < createEventViewStartMinutes
				.getValue()) {
			createEventViewEndMinutes.setStyle("-fx-background-color: red");
			endError.setStyle("-fx-text-fill: red");
			endError.setText("End time cannot be set before start time.");
			endError.setVisible(true);
			return false;
		} else if (createEventViewStartHours.getValue() == createEventViewEndHours
				.getValue()
				&& createEventViewStartMinutes.getValue() == createEventViewEndMinutes
				.getValue()) {
			createEventViewEndMinutes.setStyle("-fx-background-color: red");
			createEventViewEndHours.setStyle("-fx-background-color:  red");
			endError.setStyle("-fx-text-fill: red");
			endError.setText("End time and start time cannot be equal.");
			endError.setVisible(true);
			return false;
		} else {
			createEventViewEndMinutes.setStyle(" ");
			createEventViewEndHours.setStyle(" ");
			endError.setVisible(false);
			Time start = new Time(createEventViewStartHours.getValue(),
					createEventViewStartMinutes.getValue(), 00);
			Time end = new Time(createEventViewEndHours.getValue(),
					createEventViewEndMinutes.getValue(), 00);
			personalAppointment.setStartTid(start);
			personalAppointment.setSluttTid(end);
			return true;
		}
	}

	@FXML
	public void lastsAllDay() {
		if (allDayCheck.isSelected()) {
			createEventViewStartHours.setValue(0);
			createEventViewStartMinutes.setValue(0);
			createEventViewEndHours.setValue(23);
			createEventViewEndMinutes.setValue(59);
			createEventViewEndMinutes.setDisable(true);
			createEventViewEndHours.setDisable(true);
			createEventViewStartMinutes.setDisable(true);
			createEventViewStartHours.setDisable(true);
		} else {
			createEventViewEndMinutes.setDisable(false);
			createEventViewEndHours.setDisable(false);
			createEventViewStartMinutes.setDisable(false);
			createEventViewStartHours.setDisable(false);
		}
	}

	public boolean validateNotification() {
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTime().getTime());
		try {
			int not = Integer.parseInt(notifyInt.getText());
			if(not == 0){
				alarm.setTidspunkt(null);
				return true;
			}
			if(((String)notifyCombo.getValue()).equalsIgnoreCase("minutes")){
				ts.setMinutes(ts.getMinutes()+not);
				alarm.setTidspunkt(ts);
				return true;
			}
			if(((String)notifyCombo.getValue()).equalsIgnoreCase("hours")){
				ts.setHours(ts.getHours()+not);
				alarm.setTidspunkt(ts);
				return true;
			}
			if(((String)notifyCombo.getValue()).equalsIgnoreCase("days")){
				ts.setDate(ts.getDay()+not);
				alarm.setTidspunkt(ts);
				return true;
			}
		} catch (Exception e) {
			alarm.setTidspunkt(null);
			return true;
		}
		return false;
	}

	@FXML
	public boolean validateDescription() {
		if (createEventViewTextArea.getText().trim().length() == 0) {
			createEventViewTextArea
			.setText("There has to be a description of the event.");
			return false;
		} else if (createEventViewTextArea.getText().equals(
				"There has to be a description of the event.")) {
			return false;
		} else {
			personalAppointment.setBeskrivelse(createEventViewTextArea
					.getText());
			return true;
		}
	}

	@FXML
	public boolean validateRoom() throws Exception {
		if(createEventViewRoom.getValue().equalsIgnoreCase("PersonalRoom")){
			return true;
		}
		if(!isChangedDNT()){
			return true;
		}
		if(createEventViewRoom.getValue() == null) {
			roomError.setStyle("-fx-text-fill: red");
			roomError.setText("There has to be a room to the event.");
			roomError.setVisible(true);
			return false;
		}
		personalAppointment.setRomnavn(createEventViewRoom.getValue());
		if(!validateTime()){
			roomError.setStyle("-fx-text-fill: red");
			roomError.setText("Room can not be set before time.");
			roomError.setVisible(true);
			return false;
		}
		if(databaseServer.roomIsTaken(personalAppointment)){
			roomError.setStyle("-fx-text-fill: red");
			roomError.setText("The room is taken on the given time.");
			roomError.setVisible(true);
			return false;
		}else {
			roomError.setVisible(false);
			return true;
		}
	}

	@FXML
	public void createEvent(ActionEvent event) throws Exception {
		setDato();
		if (validateTime() && validateRoom() && validateDescription() && validateNotification()){
			if(isChanged()){
				for(PersonalAppointment pa : databaseServer.appointmentChilds(opprinneligPa)){
					databaseServer.removeAppointment(pa);
				}
				databaseServer.removeInvite(opprinneligPa);
				databaseServer.editAppointment(personalAppointment, databaseServer.getGroupId(createEventViewGroup.getValue()));
				parent.monthB();
				parent.monthF();
				((Node) (event.getSource())).getScene().getWindow().hide();
			}
			else{
				((Node) (event.getSource())).getScene().getWindow().hide();
			}
		}
	}


	private boolean isChanged() throws Exception{
		if(!personalAppointment.getBeskrivelse().equalsIgnoreCase(opprinneligPa.getBeskrivelse())){
			return true;
		}
		if(!(personalAppointment.getDato().equals(opprinneligPa.getDato()))){
			return true;
		}
		if(!(personalAppointment.getStartTid().equals(opprinneligPa.getStartTid()))){
			return true;
		}
		if(!personalAppointment.getSluttTid().equals(opprinneligPa.getSluttTid())){
			return true;
		}
		if(!databaseServer.invitesSent(opprinneligPa).equals(selectedUsers)){
			return true;
		}
		if(!personalAppointment.getRomnavn().equals(opprinneligPa.getRomnavn())){
			return true;
		}
		return false;
	}

	private boolean isChangedDNT() throws Exception{
		if(!(personalAppointment.getDato().equals(opprinneligPa.getDato()))){
			return true;
		}
		if(!(personalAppointment.getStartTid().equals(opprinneligPa.getStartTid()))){
			return true;
		}
		if(!personalAppointment.getSluttTid().equals(opprinneligPa.getSluttTid())){
			return true;
		}
		if(!personalAppointment.getRomnavn().equals(opprinneligPa.getRomnavn())){
			return true;
		}
		return false;
	}

	public void sendInvitationEmail() {
		try {
			for (int i = 0; i < selectedUsers.size(); i++) {
				// Sender's email ID needs to be mentioned
				String from = "awesome@calendar.com";

				// Assuming you are sending email from localhost
				String host = "smtp.stud.ntnu.no";

				// Get system properties
				Properties properties = System.getProperties();

				// Setup mail server
				properties.setProperty("mail.smtp.host", host);

				// Get the default Session object.
				Session session = Session.getDefaultInstance(properties);

				try {
					// Create a default MimeMessage object.
					MimeMessage message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.addRecipient(
							Message.RecipientType.TO,
							new InternetAddress(selectedUsers.get(i).getEmail()));

					// Set Subject: header field
					message.setSubject("Invitation to meeting");

					// Now set the actual message
					try {
						message.setText("Your have been invited to join "
								+ databaseServer.getUser()
								+ "s meeting, which will take place at "
								+ createEventViewRoom.getValue()
								+ ", "
								+ createEventViewStartHours.getValue()
								+ ":"
								+ createEventViewStartMinutes.getValue()
								+ ". Please check your calendar to accept or decline this invitation."
								+ "\nMeeting description: \n"
								+ createEventViewTextArea.getText());

					} catch (Exception e) {
						System.out.println(e);
					}

					// Send message
					Transport.send(message);
					System.out.println("Sent message successfully to "
							+ selectedUsers.get(i).getEmail());

				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	public void cancelEvent(ActionEvent event) throws Exception {
		parent.notifications();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
}

