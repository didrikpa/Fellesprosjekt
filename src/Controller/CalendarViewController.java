package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import Model.PersonalAppointment;
import Server.*;

public class CalendarViewController implements Initializable{

	@FXML ToggleButton toggleButtonWeek;
	@FXML ToggleButton toggleButtonMonth;
	@FXML TextField searchBar;
	@FXML ListView<String> searchList;
    @FXML Label labelMonth;

	//Hovedviewet hvor stage hentes fra
	@FXML
	Pane mainMonthViewPane;
	//Underview - mainViewMid inneholder enten month- eller week-kalender
	@FXML 
	Pane mainViewMid;
	DatabaseServer server;
	Stage stage;

	public CalendarViewController(DatabaseServer loginServer) throws Exception{
		server = loginServer;
		mainViewMid = new Pane();
		labelMonth = new Label();
		mainMonthViewPane = new Pane();
		initialize(null, null);
	}
	
	// TopPane code
	@FXML
	public void switchToWeek(ActionEvent event) throws Exception {
		if(!toggleButtonWeek.isPressed()){
			mainViewMid.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/weekView.fxml"));
			loader.setController(new WeekViewController());
			mainViewMid.getChildren().add((Parent) loader.load());
			toggleButtonMonth.setSelected(false);
            labelMonth.setText("Week");
		}

	}

	public void switchToMonth(ActionEvent event) throws Exception {
		if(!toggleButtonMonth.isPressed()){
			mainViewMid.getChildren().clear();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
			loader.setController(new MonthViewController(server));
			mainViewMid.getChildren().add((Parent) loader.load());
            labelMonth.setText("Month");
		}
	}

	// LeftBar code 
	@FXML
	private void chooseAppointment(ActionEvent event){
		System.out.println("LOL");
		//    	searchList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		//    	    @Override
		//			public void changed(ObservableValue<? extends String> observable,
		//					String oldValue, String newValue) {
		//    	    	System.out.println("LOL");
		//			}
		//    	});
	}

	@FXML
	public void searchEvent(ActionEvent event) throws Exception {
		if(!searchList.isVisible()){
			EventSearchController evs = new EventSearchController(server);
			ArrayList<PersonalAppointment> pas = new ArrayList<PersonalAppointment>();
			pas = evs.eventSearch(searchBar.getText(), true, server.comingUp(10));
			ArrayList<String> nas = new ArrayList<String>();
			for(PersonalAppointment pa :  pas){
				nas.add(pa.getBeskrivelse());
			}
			searchList.setVisible(true);
			searchList.setItems(FXCollections.observableArrayList(nas));
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
	
//	@FXML
//	public void createEvent(ActionEvent event) throws Exception {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/createEventView.fxml"));
//		loader.setController(new CreateEventController(server));
//		stage = (Stage) mainMonthViewPane.getScene().getWindow();
//		Parent root = loader.load();
//		stage.setScene(new Scene(root));
//		stage.setTitle("Create event");
//		stage.show();
//	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainViewMid.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/monthView.fxml"));
		loader.setController(new MonthViewController(server));
		try {
			mainViewMid.getChildren().add((Parent) loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        labelMonth.setText("Month");
	}
}
