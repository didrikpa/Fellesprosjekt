package Controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import Model.PersonalAppointment;
import Server.DatabaseServer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

	DatabaseServer server;
	public MonthViewController(DatabaseServer loginServer){
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
		showEvents(sane00,innehald[0]);
		showEvents(sane10,innehald[1]);
		showEvents(sane20,innehald[2]);
		showEvents(sane30,innehald[3]);
		showEvents(sane40,innehald[4]);
		showEvents(sane50,innehald[5]);
		showEvents(sane60,innehald[6]);
		showEvents(sane01,innehald[7]);
		showEvents(sane11,innehald[8]);
		showEvents(sane21,innehald[9]);
		showEvents(sane31,innehald[10]);
		showEvents(sane41,innehald[11]);
		showEvents(sane51,innehald[12]);
		showEvents(sane61,innehald[13]);
		showEvents(sane02,innehald[14]);
		showEvents(sane12,innehald[15]);
		showEvents(sane22,innehald[16]);
		showEvents(sane32,innehald[17]);
		showEvents(sane42,innehald[18]);
		showEvents(sane52,innehald[19]);
		showEvents(sane62,innehald[20]);
		showEvents(sane03,innehald[21]);
		showEvents(sane13,innehald[22]);
		showEvents(sane23,innehald[23]);
		showEvents(sane33,innehald[24]);
		showEvents(sane43,innehald[25]);
		showEvents(sane53,innehald[26]);
		showEvents(sane63,innehald[27]);
		showEvents(sane04,innehald[28]);
		showEvents(sane14,innehald[29]);
		showEvents(sane24,innehald[30]);
		showEvents(sane34,innehald[31]);
		showEvents(sane44,innehald[32]);
		showEvents(sane54,innehald[33]);
		showEvents(sane64,innehald[34]);
		showEvents(sane05,innehald[35]);
		showEvents(sane15,innehald[36]);
		showEvents(sane25,innehald[37]);
		showEvents(sane35,innehald[38]);
		showEvents(sane45,innehald[39]);
		showEvents(sane55,innehald[40]);
		showEvents(sane65,innehald[41]);
		
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

	@SuppressWarnings({ "deprecation", "static-access" })
	public int[] settMonth(int ayear, int amonth){
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
				try{
					ArrayList<PersonalAppointment> ena = server.getAppointment(innehald[0]);
					GridPane gridPane = new GridPane();
					gridPane.setHgap(10);
					gridPane.setVgap(10);
					if(ena.size() <= 3){
						int i = 0;
						for(PersonalAppointment enu:ena){
							Label eni = new Label();
							String desc = enu.getBeskrivelse();
							if(enu.getBeskrivelse().length() > 6){
								desc = desc.substring(0, 6);
								desc += "..";
							}
							eni.setText(enu.getStartTid().getHours() + ":" + enu.getStartTid().getMinutes() + "-" + enu.getSluttTid().getHours() + ":" + enu.getSluttTid().getMinutes() + " " + desc);
							gridPane.add(eni, 0,i);
							i+=1;
						}
						pane00.getChildren().add(gridPane);
					}
				}
				catch(Exception e1){
					System.out.println(e1);
				}
			}
		});

		pane10.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 10 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[1])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane20.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 20 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[2])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane30.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 30 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[3])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane40.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 40 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[4])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane50.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 50 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[5])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane60.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 60 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[6])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane01.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 01 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[7])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 11 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[8])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane21.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 21 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[9])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane31.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 31");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[10])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane41.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 41 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[11])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane51.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 51 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[12])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane61.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 61 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[13])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane02.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 02 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[14])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 12 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[15])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane22.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 22 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[16])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane32.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 32 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[17])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane42.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 42 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[18])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane52.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 52 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[19])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane62.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 62 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[20])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane03.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 03 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[21])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane13.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 13 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[22])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane23.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 23 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[23])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane33.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 33 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[24])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane43.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 43 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[25])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane53.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 53 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[26])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane63.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 63 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[27])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane04.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 04 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[28])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane14.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 14 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[29])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane24.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 24 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[30])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane34.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 34 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[31])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane44.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 44 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[32])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane54.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 54 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[33])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane64.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 64 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[34])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane05.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 05 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[35])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane15.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 15 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[36])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane25.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 25 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[37])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane35.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 35 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[38])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane45.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(e.getClickCount() == 2)
					System.out.print("Pane 45 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[39])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane55.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 55 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[40])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pane65.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getClickCount() == 2)
					System.out.print("Pane 65 ");
				try {
					for(PersonalAppointment en : server.getAppointment(innehald[41])){
						System.out.println(en.getBeskrivelse());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	private void showEvents(Pane pane, Date date) throws Exception {
		pane.getChildren().clear();
		ArrayList<PersonalAppointment> ena = server.getAppointment(date);
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
