package Controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

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
public class MonthViewController{
	@FXML Pane pane00;
	@FXML Pane pane10;
	@FXML Pane pane20;
	@FXML Pane pane30;
	@FXML Pane pane40;
	@FXML Pane pane50;
	@FXML Pane pane60;
	@FXML Pane pane01;
	@FXML Pane pane11;
	@FXML Pane pane21;
	@FXML Pane pane31;
	@FXML Pane pane41;
	@FXML Pane pane51;
	@FXML Pane pane61;
	@FXML Pane pane02;
	@FXML Pane pane12;
	@FXML Pane pane22;
	@FXML Pane pane32;
	@FXML Pane pane42;
	@FXML Pane pane52;
	@FXML Pane pane62;
	@FXML Pane pane03;
	@FXML Pane pane13;
	@FXML Pane pane23;
	@FXML Pane pane33;
	@FXML Pane pane43;
	@FXML Pane pane53;
	@FXML Pane pane63;
	@FXML Pane pane04;
	@FXML Pane pane14;
	@FXML Pane pane24;
	@FXML Pane pane34;
	@FXML Pane pane44;
	@FXML Pane pane54;
	@FXML Pane pane64;
	@FXML Pane pane05;
	@FXML Pane pane15;
	@FXML Pane pane25;
	@FXML Pane pane35;
	@FXML Pane pane45;
	@FXML Pane pane55;
	@FXML Pane pane65;
	
	@FXML Pane sane00;
	@FXML Pane sane10;
	@FXML Pane sane20;
	@FXML Pane sane30;
	@FXML Pane sane40;
	@FXML Pane sane50;
	@FXML Pane sane60;
	@FXML Pane sane01;
	@FXML Pane sane11;
	@FXML Pane sane21;
	@FXML Pane sane31;
	@FXML Pane sane41;
	@FXML Pane sane51;
	@FXML Pane sane61;
	@FXML Pane sane02;
	@FXML Pane sane12;
	@FXML Pane sane22;
	@FXML Pane sane32;
	@FXML Pane sane42;
	@FXML Pane sane52;
	@FXML Pane sane62;
	@FXML Pane sane03;
	@FXML Pane sane13;
	@FXML Pane sane23;
	@FXML Pane sane33;
	@FXML Pane sane43;
	@FXML Pane sane53;
	@FXML Pane sane63;
	@FXML Pane sane04;
	@FXML Pane sane14;
	@FXML Pane sane24;
	@FXML Pane sane34;
	@FXML Pane sane44;
	@FXML Pane sane54;
	@FXML Pane sane64;
	@FXML Pane sane05;
	@FXML Pane sane15;
	@FXML Pane sane25;
	@FXML Pane sane35;
	@FXML Pane sane45;
	@FXML Pane sane55;
	@FXML Pane sane65;

	@FXML Label date00;
	@FXML Label date10;
	@FXML Label date20;
	@FXML Label date30;
	@FXML Label date40;
	@FXML Label date50;
	@FXML Label date60;
	@FXML Label date01;
	@FXML Label date11;
	@FXML Label date21;
	@FXML Label date31;
	@FXML Label date41;
	@FXML Label date51;
	@FXML Label date61;
	@FXML Label date02;
	@FXML Label date12;
	@FXML Label date22;
	@FXML Label date32;
	@FXML Label date42;
	@FXML Label date52;
	@FXML Label date62;
	@FXML Label date03;
	@FXML Label date13;
	@FXML Label date23;
	@FXML Label date33;
	@FXML Label date43;
	@FXML Label date53;
	@FXML Label date63;
	@FXML Label date04;
	@FXML Label date14;
	@FXML Label date24;
	@FXML Label date34;
	@FXML Label date44;
	@FXML Label date54;
	@FXML Label date64;
	@FXML Label date05;
	@FXML Label date15;
	@FXML Label date25;
	@FXML Label date35;
	@FXML Label date45;
	@FXML Label date55;
	@FXML Label date65;
	@FXML AnchorPane monthViewPaneMain;
	@FXML GridPane gridPane;

	//Holder dag for hver label
	int [] dagar;
	//Holder dato for hver pane
	Date [] innehald;
	CalendarViewController parent;
	DatabaseServer server;
	public MonthViewController(DatabaseServer loginServer, CalendarViewController par){
		parent = par;
		server = loginServer;
		innehald = new Date[42];
		init();
	}
	
	void dateInit(){
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
	}
	
	//Instansierer alle panes
	public void init(){	
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

	//får settMonth til å fylle array, og legger deretter hver verdi til hver dag
	public void setMonth(int ayear,int amonth) throws Exception{
		dagar = settMonth(ayear, amonth);
		showEvents(sane00,innehald[0],null);
		showEvents(sane10,innehald[1],null);
		showEvents(sane20,innehald[2],null);
		showEvents(sane30,innehald[3],null);
		showEvents(sane40,innehald[4],null);
		showEvents(sane50,innehald[5],null);
		showEvents(sane60,innehald[6],null);
		showEvents(sane01,innehald[7],null);
		showEvents(sane11,innehald[8],null);
		showEvents(sane21,innehald[9],null);
		showEvents(sane31,innehald[10],null);
		showEvents(sane41,innehald[11],null);
		showEvents(sane51,innehald[12],null);
		showEvents(sane61,innehald[13],null);
		showEvents(sane02,innehald[14],null);
		showEvents(sane12,innehald[15],null);
		showEvents(sane22,innehald[16],null);
		showEvents(sane32,innehald[17],null);
		showEvents(sane42,innehald[18],null);
		showEvents(sane52,innehald[19],null);
		showEvents(sane62,innehald[20],null);
		showEvents(sane03,innehald[21],null);
		showEvents(sane13,innehald[22],null);
		showEvents(sane23,innehald[23],null);
		showEvents(sane33,innehald[24],null);
		showEvents(sane43,innehald[25],null);
		showEvents(sane53,innehald[26],null);
		showEvents(sane63,innehald[27],null);
		showEvents(sane04,innehald[28],null);
		showEvents(sane14,innehald[29],null);
		showEvents(sane24,innehald[30],null);
		showEvents(sane34,innehald[31],null);
		showEvents(sane44,innehald[32],null);
		showEvents(sane54,innehald[33],null);
		showEvents(sane64,innehald[34],null);
		showEvents(sane05,innehald[35],null);
		showEvents(sane15,innehald[36],null);
		showEvents(sane25,innehald[37],null);
		showEvents(sane35,innehald[38],null);
		showEvents(sane45,innehald[39],null);
		showEvents(sane55,innehald[40],null);
		showEvents(sane65,innehald[41],null);
		
		date00.setText(dagar[0]+"");
		date10.setText(dagar[1]+"");
		date20.setText(dagar[2]+"");
		date30.setText(dagar[3]+"");
		date40.setText(dagar[4]+"");
		date50.setText(dagar[5]+"");
		date60.setText(dagar[6]+"");

		date01.setText(dagar[7]+"");
		date11.setText(dagar[8]+"");
		date21.setText(dagar[9]+"");
		date31.setText(dagar[10]+"");
		date41.setText(dagar[11]+"");
		date51.setText(dagar[12]+"");
		date61.setText(dagar[13]+"");

		date02.setText(dagar[14]+"");
		date12.setText(dagar[15]+"");
		date22.setText(dagar[16]+"");
		date32.setText(dagar[17]+"");
		date42.setText(dagar[18]+"");
		date52.setText(dagar[19]+"");
		date62.setText(dagar[20]+"");

		date03.setText(dagar[21]+"");
		date13.setText(dagar[22]+"");
		date23.setText(dagar[23]+"");
		date33.setText(dagar[24]+"");
		date43.setText(dagar[25]+"");
		date53.setText(dagar[26]+"");
		date63.setText(dagar[27]+"");

		date04.setText(dagar[28]+"");
		date14.setText(dagar[29]+"");
		date24.setText(dagar[30]+"");
		date34.setText(dagar[31]+"");
		date44.setText(dagar[32]+"");
		date54.setText(dagar[33]+"");
		date64.setText(dagar[34]+"");

		date05.setText(dagar[35]+"");
		date15.setText(dagar[36]+"");
		date25.setText(dagar[37]+"");
		date35.setText(dagar[38]+"");
		date45.setText(dagar[39]+"");
		date55.setText(dagar[40]+"");
		date65.setText(dagar[41]+"");
		
	}
	
	public void setMonthGroup(int ayear,int amonth, Group group) throws Exception{
		dagar = settMonth(ayear, amonth);
		showEvents(sane00,innehald[0],group);
		showEvents(sane10,innehald[1],group);
		showEvents(sane20,innehald[2],group);
		showEvents(sane30,innehald[3],group);
		showEvents(sane40,innehald[4],group);
		showEvents(sane50,innehald[5],group);
		showEvents(sane60,innehald[6],group);
		showEvents(sane01,innehald[7],group);
		showEvents(sane11,innehald[8],group);
		showEvents(sane21,innehald[9],group);
		showEvents(sane31,innehald[10],group);
		showEvents(sane41,innehald[11],group);
		showEvents(sane51,innehald[12],group);
		showEvents(sane61,innehald[13],group);
		showEvents(sane02,innehald[14],group);
		showEvents(sane12,innehald[15],group);
		showEvents(sane22,innehald[16],group);
		showEvents(sane32,innehald[17],group);
		showEvents(sane42,innehald[18],group);
		showEvents(sane52,innehald[19],group);
		showEvents(sane62,innehald[20],group);
		showEvents(sane03,innehald[21],group);
		showEvents(sane13,innehald[22],group);
		showEvents(sane23,innehald[23],group);
		showEvents(sane33,innehald[24],group);
		showEvents(sane43,innehald[25],group);
		showEvents(sane53,innehald[26],group);
		showEvents(sane63,innehald[27],group);
		showEvents(sane04,innehald[28],group);
		showEvents(sane14,innehald[29],group);
		showEvents(sane24,innehald[30],group);
		showEvents(sane34,innehald[31],group);
		showEvents(sane44,innehald[32],group);
		showEvents(sane54,innehald[33],group);
		showEvents(sane64,innehald[34],group);
		showEvents(sane05,innehald[35],group);
		showEvents(sane15,innehald[36],group);
		showEvents(sane25,innehald[37],group);
		showEvents(sane35,innehald[38],group);
		showEvents(sane45,innehald[39],group);
		showEvents(sane55,innehald[40],group);
		showEvents(sane65,innehald[41],group);
		
		date00.setText(dagar[0]+"");
		date10.setText(dagar[1]+"");
		date20.setText(dagar[2]+"");
		date30.setText(dagar[3]+"");
		date40.setText(dagar[4]+"");
		date50.setText(dagar[5]+"");
		date60.setText(dagar[6]+"");

		date01.setText(dagar[7]+"");
		date11.setText(dagar[8]+"");
		date21.setText(dagar[9]+"");
		date31.setText(dagar[10]+"");
		date41.setText(dagar[11]+"");
		date51.setText(dagar[12]+"");
		date61.setText(dagar[13]+"");

		date02.setText(dagar[14]+"");
		date12.setText(dagar[15]+"");
		date22.setText(dagar[16]+"");
		date32.setText(dagar[17]+"");
		date42.setText(dagar[18]+"");
		date52.setText(dagar[19]+"");
		date62.setText(dagar[20]+"");

		date03.setText(dagar[21]+"");
		date13.setText(dagar[22]+"");
		date23.setText(dagar[23]+"");
		date33.setText(dagar[24]+"");
		date43.setText(dagar[25]+"");
		date53.setText(dagar[26]+"");
		date63.setText(dagar[27]+"");

		date04.setText(dagar[28]+"");
		date14.setText(dagar[29]+"");
		date24.setText(dagar[30]+"");
		date34.setText(dagar[31]+"");
		date44.setText(dagar[32]+"");
		date54.setText(dagar[33]+"");
		date64.setText(dagar[34]+"");

		date05.setText(dagar[35]+"");
		date15.setText(dagar[36]+"");
		date25.setText(dagar[37]+"");
		date35.setText(dagar[38]+"");
		date45.setText(dagar[39]+"");
		date55.setText(dagar[40]+"");
		date65.setText(dagar[41]+"");
		
	}
	
	private void eventToday(Date date){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/todaysEventsView.fxml"));
			fxmlLoader.setController(new TodaysEventsController(server,date, parent));
			Stage stage = new Stage();
			stage.setTitle("Todays events");
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
			stage.show();
		}
		catch (Exception e) { System.out.println(e);}

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private int[] settMonth(int ayear, int amonth){
		int year = ayear;
		int month = amonth;
		int gang = 0;
		int [] dager = new int [42];
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month,1);
		int dmo = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		int dweek = calendar.get(calendar.DAY_OF_WEEK);
		Calendar cal = Calendar.getInstance();
		cal.set(year, month,0);
		int fmo = cal.getActualMaximum(cal.DAY_OF_MONTH);
		if(dweek == 2){
			for(int i = fmo-6; i <= fmo; i++){
				dager[gang] = i;
				innehald[gang] = new Date(ayear-1900,amonth-1,i);
				gang +=1;
			}
			for(int i = 1; i <= dmo; i++){
				dager[gang] = i;
				innehald[gang] = new Date(ayear-1900,amonth,i);
				gang +=1;
			}
			int a = 1;
			while(gang < 42){
				dager[gang] = a;
				innehald[gang] = new Date(ayear-1900,amonth+1,a);
				gang +=1;
				a+=1;
			}
		}
		else{
			if(dweek == 1){
				for(int i = fmo-5; i <= fmo; i++){
					dager[gang] = i;
					innehald[gang] = new Date(ayear-1900,amonth-1,i);
					gang +=1;
				}
				for(int i = 1; i <= dmo; i++){
					dager[gang] = i;
					innehald[gang] = new Date(ayear-1900,amonth,i);
					gang +=1;
				}
				int a = 1;
				while(gang < 42){
					dager[gang] = a;
					innehald[gang] = new Date(ayear-1900,amonth+1,a);
					gang +=1;
					a+=1;
				}
			}
			else{
				if(month == 0){
					for(int j = 31-(7-dweek);j <= 31;j++){
						dager[gang] = j;
						innehald[gang] = new Date(ayear-1900,amonth-1,j);
						gang +=1;
					}
					for(int m = 1;m <= dmo;m++){
						dager[gang] = m;
						innehald[gang] = new Date(ayear-1900,amonth,m);
						gang +=1;
					}
					int a = 1;
					while(gang < 42){
						dager[gang] = a;
						innehald[gang] = new Date(ayear-1900,amonth+1,a);
						gang +=1;
						a+=1;
					}
				}
				else{
					for(int j = fmo-(dweek-3);j<=fmo;j++){
						dager[gang] = j;
						innehald[gang] = new Date(ayear-1900,amonth-1,j);
						gang +=1;
					}
					for(int m = 1;m <= dmo;m++){
						dager[gang] = m;
						innehald[gang] = new Date(ayear-1900,amonth,m);
						gang +=1;
					}
					int a = 1;
					while(gang < 42){
						dager[gang] = a;
						innehald[gang] = new Date(ayear-1900,amonth+1,a);
						gang+=1;
						a +=1;
					}
				}
			}
		}
		return dager;
	}

	@FXML
	public void clickGrid(MouseEvent e) {
		pane00.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[0]);
			}
		});
		
		pane10.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[1]);
			}
		});
		pane20.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[2]);
			}
		});
		pane30.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[3]);
			}
		});
		pane40.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[4]);
			}
		});
		pane50.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[5]);
			}
		});
		pane60.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[6]);
			}
		});
		pane01.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[7]);
			}
		});

		pane11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[8]);
			}
		});
		pane21.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[9]);
			}
		});
		pane31.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[10]);
			}
		});
		pane41.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[11]);
			}
		});
		pane51.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[12]);
			}
		});
		pane61.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[13]);
			}
		});
		pane02.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[14]);
			}
		});

		pane12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[15]);
			}
		});
		pane22.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[16]);
			}
		});
		pane32.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[17]);
			}
		});
		pane42.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[18]);
			}
		});
		pane52.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[19]);
			}
		});
		pane62.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[20]);
			}
		});

		pane03.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[21]);
			}
		});
		pane13.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[22]);
			}
		});
		pane23.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[23]);
			}
		});
		pane33.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[24]);
			}
		});
		pane43.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[25]);
			}
		});
		pane53.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[26]);
			}
		});
		
		pane63.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[27]);
			}
		});
		
		pane04.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[28]);
			}
		});

		pane14.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[29]);
			}
		});
		pane24.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[30]);
			}
		});
		pane34.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[31]);
			}
		});
		pane44.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[32]);
			}
		});
		pane54.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[33]);
			}
		});
		pane64.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[34]);
			}
		});
		pane05.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[35]);
			}
		});

		pane15.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[36]);
			}
		});
		pane25.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[37]);
			}
		});
		pane35.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[38]);
			}
		});
		pane45.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[39]);
			}
		});
		pane55.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[40]);
			}
		});
		pane65.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2) eventToday(innehald[41]);
			}
		});
	}
	private void showEvents(Pane pane, Date date, Group group) throws Exception {
		pane.getChildren().clear();
		ArrayList<PersonalAppointment> ena;
		if(group == null){
			ena = server.getAppointment(date);
		}
		else{
		 ena = server.getAppointment(date,group);
		}
		if(!ena.isEmpty() || !ena.equals(null)){
			GridPane gridPane = new GridPane();
			gridPane.setHgap(10);
			gridPane.setVgap(10);
			int i = 1;
			for(PersonalAppointment enu:ena){
				Label eni = new Label();
				if(i <= 3){
					String desc = enu.getBeskrivelse();
					if(enu.getBeskrivelse().length() > 6){
						desc = desc.substring(0, 6);
						desc += "..";
					}
					eni.setText(enu.getStartTid().getHours() + ":" + enu.getStartTid().getMinutes() + "-" + enu.getSluttTid().getHours() + ":" + enu.getSluttTid().getMinutes() + " " + desc);
					gridPane.add(eni, 0,i);
					i+=1;
				}
			}
			pane.getChildren().add(gridPane);
		}
	}
}
