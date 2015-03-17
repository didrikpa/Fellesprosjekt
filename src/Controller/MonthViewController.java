package Controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Util;

import Model.Group;
import Model.PersonalAppointment;
import Server.DatabaseServer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MonthViewController {
	//Deklarering av FXML-panes og labels skjer i bunnen av klassen
	@FXML
	AnchorPane monthViewPaneMain;
	@FXML
	GridPane gridPane;
	
	// Holder dag for hver label
	int[] daysLabel;
	// Holder dato for hver pane
	Date[] paneDates;
	
	CalendarViewController parent;
	DatabaseServer server;
	public MonthViewController(DatabaseServer dbServer, CalendarViewController par) {
		parent = par;
		server = dbServer;
		paneDates = new Date[42];
		init();
	}
	
	
	
	@SuppressWarnings({ "deprecation", "static-access" })
	private int[] settMonth(int ayear, int amonth) {
		int year = ayear;
		int month = amonth;
		int panesTaken = 0;
		int[] days = new int[42];
		Calendar thisMonth = Calendar.getInstance();
		thisMonth.set(year, month, 1);
		int daysThismonth = thisMonth.getActualMaximum(thisMonth.DAY_OF_MONTH);
		int dayOfWeek = thisMonth.get(thisMonth.DAY_OF_WEEK);
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.set(year, month, 0);
		int daysLastmonth = lastMonth.getActualMaximum(lastMonth.DAY_OF_MONTH);
		if (dayOfWeek == 2) {
			for (int i = daysLastmonth - 6; i <= daysLastmonth; i++) {
				days[panesTaken] = i;
				paneDates[panesTaken] = new Date(ayear - 1900, amonth - 1, i);
				panesTaken += 1;
			}
			for (int i = 1; i <= daysThismonth; i++) {
				days[panesTaken] = i;
				paneDates[panesTaken] = new Date(ayear - 1900, amonth, i);
				panesTaken += 1;
			}
			int a = 1;
			while (panesTaken < 42) {
				days[panesTaken] = a;
				paneDates[panesTaken] = new Date(ayear - 1900, amonth + 1, a);
				panesTaken += 1;
				a += 1;
			}
		} else {
			if (dayOfWeek == 1) {
				for (int i = daysLastmonth - 5; i <= daysLastmonth; i++) {
					days[panesTaken] = i;
					paneDates[panesTaken] = new Date(ayear - 1900, amonth - 1, i);
					panesTaken += 1;
				}
				for (int i = 1; i <= daysThismonth; i++) {
					days[panesTaken] = i;
					paneDates[panesTaken] = new Date(ayear - 1900, amonth, i);
					panesTaken += 1;
				}
				int a = 1;
				while (panesTaken < 42) {
					days[panesTaken] = a;
					paneDates[panesTaken] = new Date(ayear - 1900, amonth + 1, a);
					panesTaken += 1;
					a += 1;
				}
			} else {
				if (month == 0) {
					for (int j = 31 - (7 - dayOfWeek); j <= 31; j++) {
						days[panesTaken] = j;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth - 1, j);
						panesTaken += 1;
					}
					for (int m = 1; m <= daysThismonth; m++) {
						days[panesTaken] = m;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth, m);
						panesTaken += 1;
					}
					int a = 1;
					while (panesTaken < 42) {
						days[panesTaken] = a;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth + 1, a);
						panesTaken += 1;
						a += 1;
					}
				} else {
					for (int j = daysLastmonth - (dayOfWeek - 3); j <= daysLastmonth; j++) {
						days[panesTaken] = j;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth - 1, j);
						panesTaken += 1;
					}
					for (int m = 1; m <= daysThismonth; m++) {
						days[panesTaken] = m;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth, m);
						panesTaken += 1;
					}
					int a = 1;
					while (panesTaken < 42) {
						days[panesTaken] = a;
						paneDates[panesTaken] = new Date(ayear - 1900, amonth + 1, a);
						panesTaken += 1;
						a += 1;
					}
				}
			}
		}
		return days;
	}

	@SuppressWarnings("deprecation")
	private void showEventsInPane(Pane pane, Date date, Group group)throws Exception {
		pane.getChildren().clear();
		ArrayList<PersonalAppointment> appointments;
		if (group == null) {
			appointments = server.getAppointment(date);
		} else {
			appointments = server.getAppointment(date, group);
		}
		if (!appointments.isEmpty() || !appointments.equals(null)) {
			GridPane gridPane = new GridPane();
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			int i = 1;
			for (PersonalAppointment appointment : appointments) {
				Label appointmentInPane = new Label();
				if (i <= 3) {
					String description = appointment.getBeskrivelse();
					if (appointment.getBeskrivelse().length() > 6) {
						description = description.substring(0, 6);
						description += "..";
					}
					appointmentInPane.setText(appointment.getStartTid().getHours() + ":"
							+ appointment.getStartTid().getMinutes() + "-"
							+ appointment.getSluttTid().getHours() + ":"
							+ appointment.getSluttTid().getMinutes() + " " + description);
					gridPane.add(appointmentInPane, 0, i);
					i += 1;
				}
			}
			pane.getChildren().add(gridPane);
		}
	}

	public void setMonth(int ayear, int amonth, Group group)throws Exception {
		daysLabel = settMonth(ayear, amonth);
		grayLabel();
		showEventsInPane(sane00, paneDates[0], group);
		showEventsInPane(sane10, paneDates[1], group);
		showEventsInPane(sane20, paneDates[2], group);
		showEventsInPane(sane30, paneDates[3], group);
		showEventsInPane(sane40, paneDates[4], group);
		showEventsInPane(sane50, paneDates[5], group);
		showEventsInPane(sane60, paneDates[6], group);
		
		showEventsInPane(sane01, paneDates[7], group);
		showEventsInPane(sane11, paneDates[8], group);
		showEventsInPane(sane21, paneDates[9], group);
		showEventsInPane(sane31, paneDates[10], group);
		showEventsInPane(sane41, paneDates[11], group);
		showEventsInPane(sane51, paneDates[12], group);
		showEventsInPane(sane61, paneDates[13], group);
		
		showEventsInPane(sane02, paneDates[14], group);
		showEventsInPane(sane12, paneDates[15], group);
		showEventsInPane(sane22, paneDates[16], group);
		showEventsInPane(sane32, paneDates[17], group);
		showEventsInPane(sane42, paneDates[18], group);
		showEventsInPane(sane52, paneDates[19], group);
		showEventsInPane(sane62, paneDates[20], group);
		
		showEventsInPane(sane03, paneDates[21], group);
		showEventsInPane(sane13, paneDates[22], group);
		showEventsInPane(sane23, paneDates[23], group);
		showEventsInPane(sane33, paneDates[24], group);
		showEventsInPane(sane43, paneDates[25], group);
		showEventsInPane(sane53, paneDates[26], group);
		showEventsInPane(sane63, paneDates[27], group);
		
		showEventsInPane(sane04, paneDates[28], group);
		showEventsInPane(sane14, paneDates[29], group);
		showEventsInPane(sane24, paneDates[30], group);
		showEventsInPane(sane34, paneDates[31], group);
		showEventsInPane(sane44, paneDates[32], group);
		showEventsInPane(sane54, paneDates[33], group);
		showEventsInPane(sane64, paneDates[34], group);
		
		showEventsInPane(sane05, paneDates[35], group);
		showEventsInPane(sane15, paneDates[36], group);
		showEventsInPane(sane25, paneDates[37], group);
		showEventsInPane(sane35, paneDates[38], group);
		showEventsInPane(sane45, paneDates[39], group);
		showEventsInPane(sane55, paneDates[40], group);
		showEventsInPane(sane65, paneDates[41], group);

		date00.setText(daysLabel[0] + "");
		date10.setText(daysLabel[1] + "");
		date20.setText(daysLabel[2] + "");
		date30.setText(daysLabel[3] + "");
		date40.setText(daysLabel[4] + "");
		date50.setText(daysLabel[5] + "");
		date60.setText(daysLabel[6] + "");

		date01.setText(daysLabel[7] + "");
		date11.setText(daysLabel[8] + "");
		date21.setText(daysLabel[9] + "");
		date31.setText(daysLabel[10] + "");
		date41.setText(daysLabel[11] + "");
		date51.setText(daysLabel[12] + "");
		date61.setText(daysLabel[13] + "");

		date02.setText(daysLabel[14] + "");
		date12.setText(daysLabel[15] + "");
		date22.setText(daysLabel[16] + "");
		date32.setText(daysLabel[17] + "");
		date42.setText(daysLabel[18] + "");
		date52.setText(daysLabel[19] + "");
		date62.setText(daysLabel[20] + "");

		date03.setText(daysLabel[21] + "");
		date13.setText(daysLabel[22] + "");
		date23.setText(daysLabel[23] + "");
		date33.setText(daysLabel[24] + "");
		date43.setText(daysLabel[25] + "");
		date53.setText(daysLabel[26] + "");
		date63.setText(daysLabel[27] + "");

		date04.setText(daysLabel[28] + "");
		date14.setText(daysLabel[29] + "");
		date24.setText(daysLabel[30] + "");
		date34.setText(daysLabel[31] + "");
		date44.setText(daysLabel[32] + "");
		date54.setText(daysLabel[33] + "");
		date64.setText(daysLabel[34] + "");

		date05.setText(daysLabel[35] + "");
		date15.setText(daysLabel[36] + "");
		date25.setText(daysLabel[37] + "");
		date35.setText(daysLabel[38] + "");
		date45.setText(daysLabel[39] + "");
		date55.setText(daysLabel[40] + "");
		date65.setText(daysLabel[41] + "");

	}
	
	private void grayLabel(){
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date date = new java.sql.Date(utilDate.getTime());
		if(paneDates[0].before(date)){
			date00.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date00.setStyle("-fx-background-color: white");
		}
		if(paneDates[1].before(date)){
			date10.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date10.setStyle("-fx-background-color: white");
		}
		if(paneDates[2].before(date)){
			date20.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date20.setStyle("-fx-background-color: white");
		}
		if(paneDates[3].before(date)){
			date30.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date30.setStyle("-fx-background-color: white");
		}
		if(paneDates[4].before(date)){
			date40.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date40.setStyle("-fx-background-color: white");
		}
		if(paneDates[5].before(date)){
			date50.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date50.setStyle("-fx-background-color: white");
		}
		if(paneDates[6].before(date)){
			date60.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date60.setStyle("-fx-background-color: white");
		}
		if(paneDates[7].before(date)){
			date01.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date01.setStyle("-fx-background-color: white");
		}
		if(paneDates[8].before(date)){
			date11.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date11.setStyle("-fx-background-color: white");
		}
		if(paneDates[9].before(date)){
			date21.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date21.setStyle("-fx-background-color: white");
		}
		if(paneDates[10].before(date)){
			date31.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date31.setStyle("-fx-background-color: white");
		}
		if(paneDates[11].before(date)){
			date41.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date41.setStyle("-fx-background-color: white");
		}
		if(paneDates[12].before(date)){
			date51.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date51.setStyle("-fx-background-color: white");
		}
		if(paneDates[13].before(date)){
			date61.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date61.setStyle("-fx-background-color: white");
		}
		if(paneDates[14].before(date)){
			date02.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date02.setStyle("-fx-background-color: white");
		}
		if(paneDates[15].before(date)){
			date12.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date12.setStyle("-fx-background-color: white");
		}
		if(paneDates[16].before(date)){
			date22.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date22.setStyle("-fx-background-color: white");
		}
		if(paneDates[17].before(date)){
			date32.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date32.setStyle("-fx-background-color: white");
		}
		if(paneDates[18].before(date)){
			date42.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date42.setStyle("-fx-background-color: white");
		}
		if(paneDates[19].before(date)){
			date52.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date52.setStyle("-fx-background-color: white");
		}
		if(paneDates[20].before(date)){
			date62.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date62.setStyle("-fx-background-color: white");
		}
		if(paneDates[21].before(date)){
			date03.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date03.setStyle("-fx-background-color: white");
		}
		if(paneDates[22].before(date)){
			date13.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date13.setStyle("-fx-background-color: white");
		}
		if(paneDates[23].before(date)){
			date23.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date23.setStyle("-fx-background-color: white");
		}
		if(paneDates[24].before(date)){
			date33.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date33.setStyle("-fx-background-color: white");
		}
		if(paneDates[25].before(date)){
			date43.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date43.setStyle("-fx-background-color: white");
		}
		if(paneDates[26].before(date)){
			date53.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date53.setStyle("-fx-background-color: white");
		}
		if(paneDates[27].before(date)){
			date63.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date63.setStyle("-fx-background-color: white");
		}
		if(paneDates[28].before(date)){
			date04.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date04.setStyle("-fx-background-color: white");
		}
		if(paneDates[29].before(date)){
			date14.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date14.setStyle("-fx-background-color: white");
		}
		if(paneDates[30].before(date)){
			date24.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date24.setStyle("-fx-background-color: white");
		}
		if(paneDates[31].before(date)){
			date34.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date34.setStyle("-fx-background-color: white");
		}
		if(paneDates[32].before(date)){
			date44.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date44.setStyle("-fx-background-color: white");
		}
		if(paneDates[33].before(date)){
			date54.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date54.setStyle("-fx-background-color: white");
		}
		if(paneDates[34].before(date)){
			date64.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date64.setStyle("-fx-background-color: white");
		}
		if(paneDates[35].before(date)){
			date05.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date05.setStyle("-fx-background-color: white");
		}
		if(paneDates[36].before(date)){
			date15.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date15.setStyle("-fx-background-color: white");
		}
		if(paneDates[37].before(date)){
			date25.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date25.setStyle("-fx-background-color: white");
		}
		if(paneDates[38].before(date)){
			date35.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date35.setStyle("-fx-background-color: white");
		}
		if(paneDates[39].before(date)){
			date45.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date45.setStyle("-fx-background-color: white");
		}
		if(paneDates[40].before(date)){
			date55.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date55.setStyle("-fx-background-color: white");
		}
		if(paneDates[41].before(date)){
			date65.setStyle("-fx-background-color: #d3d3d3");
		}
		else{
			date65.setStyle("-fx-background-color: white");
		}
	}

	private void eventToday(Date date) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/todaysEventsView.fxml"));
			fxmlLoader.setController(new TodaysEventsController(server, date,parent));
			Stage stage = new Stage();
			stage.setTitle("Todays events");
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//Lister opp dagens hendelser når en dobbeltklikker på en rute
	@FXML
	public void clickGrid(MouseEvent e) {
		pane00.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[0]);
			}
		});

		pane10.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[1]);
			}
		});
		pane20.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[2]);
			}
		});
		pane30.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[3]);
			}
		});
		pane40.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[4]);
			}
		});
		pane50.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[5]);
			}
		});
		pane60.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[6]);
			}
		});
		pane01.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[7]);
			}
		});

		pane11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[8]);
			}
		});
		pane21.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[9]);
			}
		});
		pane31.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[10]);
			}
		});
		pane41.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[11]);
			}
		});
		pane51.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[12]);
			}
		});
		pane61.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[13]);
			}
		});
		pane02.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[14]);
			}
		});

		pane12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[15]);
			}
		});
		pane22.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[16]);
			}
		});
		pane32.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[17]);
			}
		});
		pane42.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[18]);
			}
		});
		pane52.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[19]);
			}
		});
		pane62.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[20]);
			}
		});

		pane03.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[21]);
			}
		});
		pane13.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[22]);
			}
		});
		pane23.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[23]);
			}
		});
		pane33.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[24]);
			}
		});
		pane43.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[25]);
			}
		});
		pane53.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[26]);
			}
		});

		pane63.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[27]);
			}
		});

		pane04.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[28]);
			}
		});

		pane14.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[29]);
			}
		});
		pane24.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[30]);
			}
		});
		pane34.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[31]);
			}
		});
		pane44.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[32]);
			}
		});
		pane54.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[33]);
			}
		});
		pane64.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[34]);
			}
		});
		pane05.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[35]);
			}
		});

		pane15.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[36]);
			}
		});
		pane25.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[37]);
			}
		});
		pane35.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[38]);
			}
		});
		pane45.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[39]);
			}
		});
		pane55.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[40]);
			}
		});
		pane65.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					eventToday(paneDates[41]);
			}
		});
	}

	public void init() {
		monthViewPaneMain = new AnchorPane();
		gridPane = new GridPane();
		date00 = new Label();
		date10 = new Label();
		date20 = new Label();
		date30 = new Label();
		date40 = new Label();
		date50 = new Label();
		date60 = new Label();

		date01 = new Label();
		date11 = new Label();
		date21 = new Label();
		date31 = new Label();
		date41 = new Label();
		date51 = new Label();
		date61 = new Label();

		date02 = new Label();
		date12 = new Label();
		date22 = new Label();
		date32 = new Label();
		date42 = new Label();
		date52 = new Label();
		date62 = new Label();

		date03 = new Label();
		date13 = new Label();
		date23 = new Label();
		date33 = new Label();
		date43 = new Label();
		date53 = new Label();
		date63 = new Label();

		date04 = new Label();
		date14 = new Label();
		date24 = new Label();
		date34 = new Label();
		date44 = new Label();
		date54 = new Label();
		date64 = new Label();

		date05 = new Label();
		date15 = new Label();
		date25 = new Label();
		date35 = new Label();
		date45 = new Label();
		date55 = new Label();
		date65 = new Label();

		pane00 = new Pane();
		pane10 = new Pane();
		pane20 = new Pane();
		pane30 = new Pane();
		pane40 = new Pane();
		pane50 = new Pane();
		pane60 = new Pane();

		pane01 = new Pane();
		pane11 = new Pane();
		pane21 = new Pane();
		pane31 = new Pane();
		pane41 = new Pane();
		pane51 = new Pane();
		pane61 = new Pane();

		pane02 = new Pane();
		pane12 = new Pane();
		pane22 = new Pane();
		pane32 = new Pane();
		pane42 = new Pane();
		pane52 = new Pane();
		pane62 = new Pane();

		pane03 = new Pane();
		pane13 = new Pane();
		pane23 = new Pane();
		pane33 = new Pane();
		pane43 = new Pane();
		pane53 = new Pane();
		pane63 = new Pane();

		pane04 = new Pane();
		pane14 = new Pane();
		pane24 = new Pane();
		pane34 = new Pane();
		pane44 = new Pane();
		pane54 = new Pane();
		pane64 = new Pane();

		pane05 = new Pane();
		pane15 = new Pane();
		pane25 = new Pane();
		pane35 = new Pane();
		pane45 = new Pane();
		pane55 = new Pane();
		pane65 = new Pane();

		sane00 = new Pane();
		sane10 = new Pane();
		sane20 = new Pane();
		sane30 = new Pane();
		sane40 = new Pane();
		sane50 = new Pane();
		sane60 = new Pane();

		sane01 = new Pane();
		sane11 = new Pane();
		sane21 = new Pane();
		sane31 = new Pane();
		sane41 = new Pane();
		sane51 = new Pane();
		sane61 = new Pane();

		sane02 = new Pane();
		sane12 = new Pane();
		sane22 = new Pane();
		sane32 = new Pane();
		sane42 = new Pane();
		sane52 = new Pane();
		sane62 = new Pane();

		sane03 = new Pane();
		sane13 = new Pane();
		sane23 = new Pane();
		sane33 = new Pane();
		sane43 = new Pane();
		sane53 = new Pane();
		sane63 = new Pane();

		sane04 = new Pane();
		sane14 = new Pane();
		sane24 = new Pane();
		sane34 = new Pane();
		sane44 = new Pane();
		sane54 = new Pane();
		sane64 = new Pane();

		sane05 = new Pane();
		sane15 = new Pane();
		sane25 = new Pane();
		sane35 = new Pane();
		sane45 = new Pane();
		sane55 = new Pane();
		sane65 = new Pane();
	}
	@FXML
	Pane pane00;
	@FXML
	Pane pane10;
	@FXML
	Pane pane20;
	@FXML
	Pane pane30;
	@FXML
	Pane pane40;
	@FXML
	Pane pane50;
	@FXML
	Pane pane60;
	@FXML
	Pane pane01;
	@FXML
	Pane pane11;
	@FXML
	Pane pane21;
	@FXML
	Pane pane31;
	@FXML
	Pane pane41;
	@FXML
	Pane pane51;
	@FXML
	Pane pane61;
	@FXML
	Pane pane02;
	@FXML
	Pane pane12;
	@FXML
	Pane pane22;
	@FXML
	Pane pane32;
	@FXML
	Pane pane42;
	@FXML
	Pane pane52;
	@FXML
	Pane pane62;
	@FXML
	Pane pane03;
	@FXML
	Pane pane13;
	@FXML
	Pane pane23;
	@FXML
	Pane pane33;
	@FXML
	Pane pane43;
	@FXML
	Pane pane53;
	@FXML
	Pane pane63;
	@FXML
	Pane pane04;
	@FXML
	Pane pane14;
	@FXML
	Pane pane24;
	@FXML
	Pane pane34;
	@FXML
	Pane pane44;
	@FXML
	Pane pane54;
	@FXML
	Pane pane64;
	@FXML
	Pane pane05;
	@FXML
	Pane pane15;
	@FXML
	Pane pane25;
	@FXML
	Pane pane35;
	@FXML
	Pane pane45;
	@FXML
	Pane pane55;
	@FXML
	Pane pane65;

	@FXML
	Pane sane00;
	@FXML
	Pane sane10;
	@FXML
	Pane sane20;
	@FXML
	Pane sane30;
	@FXML
	Pane sane40;
	@FXML
	Pane sane50;
	@FXML
	Pane sane60;
	@FXML
	Pane sane01;
	@FXML
	Pane sane11;
	@FXML
	Pane sane21;
	@FXML
	Pane sane31;
	@FXML
	Pane sane41;
	@FXML
	Pane sane51;
	@FXML
	Pane sane61;
	@FXML
	Pane sane02;
	@FXML
	Pane sane12;
	@FXML
	Pane sane22;
	@FXML
	Pane sane32;
	@FXML
	Pane sane42;
	@FXML
	Pane sane52;
	@FXML
	Pane sane62;
	@FXML
	Pane sane03;
	@FXML
	Pane sane13;
	@FXML
	Pane sane23;
	@FXML
	Pane sane33;
	@FXML
	Pane sane43;
	@FXML
	Pane sane53;
	@FXML
	Pane sane63;
	@FXML
	Pane sane04;
	@FXML
	Pane sane14;
	@FXML
	Pane sane24;
	@FXML
	Pane sane34;
	@FXML
	Pane sane44;
	@FXML
	Pane sane54;
	@FXML
	Pane sane64;
	@FXML
	Pane sane05;
	@FXML
	Pane sane15;
	@FXML
	Pane sane25;
	@FXML
	Pane sane35;
	@FXML
	Pane sane45;
	@FXML
	Pane sane55;
	@FXML
	Pane sane65;

	@FXML
	Label date00;
	@FXML
	Label date10;
	@FXML
	Label date20;
	@FXML
	Label date30;
	@FXML
	Label date40;
	@FXML
	Label date50;
	@FXML
	Label date60;
	@FXML
	Label date01;
	@FXML
	Label date11;
	@FXML
	Label date21;
	@FXML
	Label date31;
	@FXML
	Label date41;
	@FXML
	Label date51;
	@FXML
	Label date61;
	@FXML
	Label date02;
	@FXML
	Label date12;
	@FXML
	Label date22;
	@FXML
	Label date32;
	@FXML
	Label date42;
	@FXML
	Label date52;
	@FXML
	Label date62;
	@FXML
	Label date03;
	@FXML
	Label date13;
	@FXML
	Label date23;
	@FXML
	Label date33;
	@FXML
	Label date43;
	@FXML
	Label date53;
	@FXML
	Label date63;
	@FXML
	Label date04;
	@FXML
	Label date14;
	@FXML
	Label date24;
	@FXML
	Label date34;
	@FXML
	Label date44;
	@FXML
	Label date54;
	@FXML
	Label date64;
	@FXML
	Label date05;
	@FXML
	Label date15;
	@FXML
	Label date25;
	@FXML
	Label date35;
	@FXML
	Label date45;
	@FXML
	Label date55;
	@FXML
	Label date65;
}
