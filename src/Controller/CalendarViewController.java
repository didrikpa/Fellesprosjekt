package Controller;

import Model.Group;
import Model.PersonalAppointment;
import Server.DatabaseServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class CalendarViewController implements Initializable {

	@FXML
	ToggleButton toggleButtonWeek;
	@FXML
	ToggleButton toggleButtonMonth;
	@FXML
	TextField searchBar;
	@FXML
	ListView<PersonalAppointment> searchList;
	@FXML
	Label labelMonth;
	@FXML
	Pane mainMonthViewPane;
	@FXML
	StackPane mainViewMid;
	@FXML
    Circle notificationCircle;
    @FXML
    Label notificationLabel;
	
	MonthViewController midViewEn;
	int maned = 0;
	int aar = 0;
	Group groupCal;
	WeekViewController midViewTo;
	DatabaseServer server;
	Stage stage;
	UpdateNotifications up;
	public CalendarViewController(DatabaseServer loginServer) throws Exception {
		server = loginServer;
		notificationLabel = new Label();
		notificationCircle = new Circle();
		init();
		initialize(null, null);
		up = new UpdateNotifications(this);
		up.setDaemon(true);
		up.start();
	}
	
	
	public void openAppointment(PersonalAppointment pa) {
		try {
			notifications();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/appointmentView.fxml"));
			fxmlLoader
			.setController(new AppointmentController(server, pa, this));
			stage = new Stage();
			stage.setTitle("Appointment - ID." + pa.getAvtaleID());
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	public void openNotification() {
		try{
            		notifications();
        	}catch(Exception e){
            		System.out.println(e);
        	}
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/notificationsPopUpView.fxml"));
			fxmlLoader.setController(new NotificationController(server,this));
			stage = new Stage();
			stage.setTitle("Notifications");
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// TopPane code
	@FXML
	public void manedBak(ActionEvent event) throws Exception {
		notifications();
		if (midViewEn != null) {
			if (groupCal == null) {
				maned -= 1;
				midViewEn.setMonth(aar, maned, null);
				updatelMonth();
			} else {
				maned -= 1;
				midViewEn.setMonth(aar, maned, groupCal);
				updatelMonth();
			}
		} else {
			midViewTo.setCalendarViewController(this);
			midViewTo.weekBackward();
			labelMonth.setText(midViewTo.getMonth() + " " +  midViewTo.getYear());
		}
	}

	@FXML
	public void manedFrem(ActionEvent event) throws Exception {
		notifications();
		if (midViewEn != null) {
			if (groupCal == null) {

				maned += 1;
				midViewEn.setMonth(aar, maned, null);
				updatelMonth();
			} else {
				maned += 1;
				midViewEn.setMonth(aar, maned, groupCal);
				updatelMonth();
			}
		} else {
			midViewTo.setCalendarViewController(this);
			midViewTo.weekForward();
			labelMonth.setText(midViewTo.getMonth() + " " +  midViewTo.getYear());
		}
	}

	public void monthB() throws Exception {
		notifications();
		if (!midViewEn.equals(null)) {
			if (groupCal == null) {
				maned -= 1;
				midViewEn.setMonth(aar, maned, null);
				updatelMonth();
			} else {
				maned -= 1;
				midViewEn.setMonth(aar, maned, groupCal);
				updatelMonth();
			}
		}
	}

	public void monthF() throws Exception {
		notifications();
		if (!midViewEn.equals(null)) {
			if (groupCal == null) {
				maned += 1;
				midViewEn.setMonth(aar, maned, null);
				updatelMonth();
			} else {
				maned += 1;
				midViewEn.setMonth(aar, maned, groupCal);
				updatelMonth();
			}
		}
	}

	private void updatelMonth() throws Exception {
		notifications();
		int mid = maned;
		int yid = aar;
		while (mid > 11) {
			mid -= 12;
			yid += 1;
		}
		while (mid < 0) {
			mid += 12;
			yid -= 1;
		}
		switch (mid) {
		case 0:
			labelMonth.setText("January " + yid);
			break;
		case 1:
			labelMonth.setText("February " + yid);
			break;
		case 2:
			labelMonth.setText("March " + yid);
			break;
		case 3:
			labelMonth.setText("April " + yid);
			break;
		case 4:
			labelMonth.setText("May " + yid);
			break;
		case 5:
			labelMonth.setText("June " + yid);
			break;
		case 6:
			labelMonth.setText("July " + yid);
			break;
		case 7:
			labelMonth.setText("August " + yid);
			break;
		case 8:
			labelMonth.setText("September " + yid);
			break;
		case 9:
			labelMonth.setText("October " + yid);
			break;
		case 10:
			labelMonth.setText("November " + yid);
			break;
		case 11:
			labelMonth.setText("December " + yid);
			break;
		default:
			labelMonth.setText("Ukjent");
			break;
		}
	}

	@FXML
	public void switchToWeek(ActionEvent event) throws Exception {
		notifications();
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/weekView.fxml"));
		midViewTo = new WeekViewController(server);
		loader.setController(midViewTo);
		midViewEn = null;
		groupCal = null;
		mainViewMid.getChildren().add((Parent) loader.load());
		toggleButtonMonth.setSelected(false);
		labelMonth.setText(midViewTo.getMonth() + " " + midViewTo.getYear());
	}

	@FXML
	public void switchToMonth(ActionEvent event) throws Exception {
		notifications();
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
		midViewEn = new MonthViewController(server, this);
		loader.setController(midViewEn);
		midViewTo = null;
		mainViewMid.getChildren().add((Parent) loader.load());
		midViewEn.setMonth(aar, maned, null);
		updatelMonth();
	}
	
	@FXML
	public void searchEvent(ActionEvent event) throws Exception {
		notifications();
		if (!searchList.isVisible()) {
			EventSearchController eventSearch = new EventSearchController(server);
			ArrayList<PersonalAppointment> personalappointments = new ArrayList<PersonalAppointment>();
			personalappointments = eventSearch.eventSearch(searchBar.getText(), true, server.comingUp(1000));
			searchList.setVisible(true);
			searchList.setItems(FXCollections.observableArrayList(personalappointments));
			searchList.getSelectionModel().selectedItemProperty()
			.addListener(new ChangeListener<PersonalAppointment>() {
				@Override
				public void changed(
						ObservableValue<? extends PersonalAppointment> observable,
						PersonalAppointment oldValue,
						PersonalAppointment newValue) {
					searchList.setVisible(false);
					openAppointment(newValue);
				}
			});
		} else {
			searchList.setVisible(false);
		}
	}

	@FXML
	public void logOut(ActionEvent event) throws Exception {
		notifications();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/loginView.fxml"));
		stage = (Stage) mainMonthViewPane.getScene().getWindow();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Login");
		stage.show();
	}

	@FXML
	public void editUser(ActionEvent event) throws Exception {
		notifications();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/editUserView.fxml"));
		loader.setController(new EditUserController(server));
        stage = new Stage();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Edit user");
		stage.show();
	}

	@FXML
	public void createEvent(ActionEvent event) throws Exception {
		notifications();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/createEventView.fxml"));
		loader.setController(new CreateEventController(server, this));
		stage = new Stage();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Create event");
		stage.show();
	}

	@FXML
	private void accessMyCalendar() throws Exception {
		notifications();
		groupCal = null;
		if (!midViewEn.equals(null)) {
			midViewEn.setMonth(aar, maned, null);
		}
	}

	@FXML
	public void accessMyGroups(ActionEvent event) throws Exception {
		notifications();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/myGroupsPopUpView.fxml"));
		loader.setController(new MyGroupController(server, this));
		stage = new Stage();
		Parent root = loader.load();
		stage.setTitle("My Groups");
		stage.setScene(new Scene(root));
		stage.show();
	}
	    @FXML
	 public void notifications() throws Exception{
        	int counter = 0;
        	for (int i = 0; i < server.getMyInvites().size() ; i++) {
            		counter+=1;
        	}
        	for (int i = 0; i < server.getAlarm().size() ; i++) {
            		counter+=1;
        	}
        	if (counter >0) {
            		notificationLabel.setText(counter + "");
            		notificationLabel.setVisible(true);
            		notificationCircle.setVisible(true);
        	} else{
            		notificationCircle.setVisible(false);
            		notificationLabel.setVisible(false);
        	}	
    	}

	@SuppressWarnings("static-access")
	void init() {
		mainViewMid = new StackPane();
		labelMonth = new Label();
		mainMonthViewPane = new Pane();
		GregorianCalendar cg = new GregorianCalendar();
		aar = cg.get(cg.YEAR);
		maned = cg.get(cg.MONTH);
		groupCal = null;
	}

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
		midViewEn = new MonthViewController(server, this);
		loader.setController(midViewEn);
		try{
            		notifications();
        	}catch(Exception e){
            		System.out.println(e);
        	}
		try {
			mainViewMid.getChildren().add((Parent) loader.load());
		} catch (IOException e) {
			System.out.println(e);
		}
		labelMonth.setText("Month");
		GregorianCalendar en = new GregorianCalendar();
		try {
			midViewEn.setMonth(en.get(en.YEAR), en.get(en.MONTH), null);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			updatelMonth();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
