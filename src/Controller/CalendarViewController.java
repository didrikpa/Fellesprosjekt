package Controller;

import Model.PersonalAppointment;
import Server.DatabaseServer;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class CalendarViewController implements Initializable{

	@FXML ToggleButton toggleButtonWeek;
	@FXML ToggleButton toggleButtonMonth;
	@FXML TextField searchBar;
	@FXML ListView<PersonalAppointment> searchList;
	@FXML Label labelMonth;
	@FXML Pane mainMonthViewPane;
	@FXML Pane mainViewMid;
	MonthViewController midViewEn;
	WeekViewController midViewTo;
	DatabaseServer server;
	Stage stage;
	int maned = 0;
	int aar = 0;

	public CalendarViewController(DatabaseServer loginServer) throws Exception{
		server = loginServer;
		init();
		initialize(null, null);
	}
	
	public void openAppointment(PersonalAppointment pa){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/appointmentView.fxml"));
			fxmlLoader.setController(new AppointmentController(server,pa, this));
			stage = new Stage();
			stage.setTitle("Appointment - ID." + pa.getAvtaleID());
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		}
		catch (Exception e) { System.out.println(e);}
	}
	
	@FXML
	public void openNotification(){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/notificationsPopUpView.fxml"));
			fxmlLoader.setController(new NotificationController());
			stage = new Stage();
			stage.setTitle("Notifications");
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		}
		catch (Exception e) { System.out.println(e);}
	}
	
	// TopPane code
	@FXML
	public void manedBak(ActionEvent event) throws Exception {
		if(!midViewEn.equals(null)){
			maned -= 1;
			midViewEn.setMonth(aar,maned);
			updatelMonth();
		}
	}
	@FXML
	public void manedFrem(ActionEvent event) throws Exception {
		if(!midViewEn.equals(null)){
			maned += 1;
			midViewEn.setMonth(aar,maned);
			updatelMonth();
		}
	}
	
	public void monthB() throws Exception {
		if(!midViewEn.equals(null)){
			maned -= 1;
			midViewEn.setMonth(aar,maned);
			updatelMonth();
		}
	}
	public void monthF() throws Exception {
		if(!midViewEn.equals(null)){
			maned += 1;
			midViewEn.setMonth(aar,maned);
			updatelMonth();
		}
	}
	
	private void updatelMonth(){
		int mid = maned;
		int yid = aar;
		while(mid > 11){
			mid -= 12;
			yid += 1;
		}
		while(mid < 0){
			mid+=12;
			yid -= 1;
		}
		switch (mid) {
		case 0:  labelMonth.setText("January "+yid);
		break;
		case 1:  labelMonth.setText("February "+yid);
		break;
		case 2:  labelMonth.setText("March "+yid);
		break;
		case 3:  labelMonth.setText("April "+yid);
		break;
		case 4:  labelMonth.setText("May "+yid);
		break;
		case 5:  labelMonth.setText("June "+yid);
		break;
		case 6:  labelMonth.setText("July "+yid);
		break;
		case 7:  labelMonth.setText("August "+yid);
		break;
		case 8:  labelMonth.setText("September "+yid);
		break;
		case 9:  labelMonth.setText("October "+yid);
		break;
		case 10: labelMonth.setText("November "+yid);
		break;
		case 11: labelMonth.setText("December "+yid);
		break;
		default: labelMonth.setText("Ukjent");
		break;
		}
	}

	@FXML
	public void switchToWeek(ActionEvent event) throws Exception {
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/weekView.fxml"));
		midViewTo = new WeekViewController();
		loader.setController(midViewTo);
		midViewEn = null;
		mainViewMid.getChildren().add((Parent) loader.load());
		toggleButtonMonth.setSelected(false);
		labelMonth.setText("Week");
	}
	
	@FXML
	public void switchToMonth(ActionEvent event) throws Exception {
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
		midViewEn = new MonthViewController(server);
		loader.setController(midViewEn);
		midViewTo = null;
		mainViewMid.getChildren().add((Parent) loader.load());
		midViewEn.setMonth(aar, maned);
		updatelMonth();
	}

	// LeftBar code 
	@FXML
	private void chooseAppointment(ActionEvent event){
	}

	@FXML
	public void searchEvent(ActionEvent event) throws Exception {
		if(!searchList.isVisible()){
			EventSearchController evs = new EventSearchController(server);
			ArrayList<PersonalAppointment> pas = new ArrayList<PersonalAppointment>();
			pas = evs.eventSearch(searchBar.getText(), true, server.comingUp(10));
			ArrayList<PersonalAppointment> nas = new ArrayList<PersonalAppointment>();
			for(PersonalAppointment pa :  pas){
				nas.add(pa);
			}
			searchList.setVisible(true);
			searchList.setItems(FXCollections.observableArrayList(nas));
			searchList.getSelectionModel().selectedItemProperty()
	        .addListener(new ChangeListener<PersonalAppointment>() {
			@Override
			public void changed(
					ObservableValue<? extends PersonalAppointment> observable,
					PersonalAppointment oldValue, PersonalAppointment newValue) {
					searchList.setVisible(false);
					openAppointment(newValue);
			}
	        });
		}
		else{
			searchList.setVisible(false);
		}
	}

	@FXML
	public void logOut(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/loginView.fxml"));
		stage = (Stage) mainMonthViewPane.getScene().getWindow();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Login");
		stage.show();
	}

	@FXML
	public void editUser(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/editUserView.fxml"));
		loader.setController(new EditUserController(server));
		stage = (Stage) mainMonthViewPane.getScene().getWindow();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Edit user");
		stage.show();
	}

	@FXML
	public void createEvent(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/createEventView.fxml"));
		loader.setController(new CreateEventController(server, this ));
        stage = new Stage();
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Create event");
		stage.show();
	}

    @FXML
    public void accessMyGroups(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/myGroupsPopUpView.fxml"));
        loader.setController(new MyGroupController(server));
        stage = new Stage();
        Parent root = loader.load();
        stage.setTitle("My Groups");
        stage.setScene(new Scene(root));
        stage.show();
    }
	
	@SuppressWarnings("static-access")
	void init(){
		mainViewMid = new Pane();
		labelMonth = new Label();
		mainMonthViewPane = new Pane();
		GregorianCalendar cg = new GregorianCalendar();
		aar = cg.get(cg.YEAR);
		maned = cg.get(cg.MONTH);
	}

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
		midViewEn = new MonthViewController(server);
		loader.setController(midViewEn);
		try {
			mainViewMid.getChildren().add((Parent) loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelMonth.setText("Month");
		GregorianCalendar en = new GregorianCalendar();
        try {
            midViewEn.setMonth(en.get(en.YEAR), en.get(en.MONTH));
        }
        catch (Exception e) {
            System.out.println(e);
        }
            updatelMonth();

	}
}
