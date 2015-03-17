package Controller;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.font.directwrite.RECT;
import com.sun.javafx.geom.Point2D;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Server.DatabaseServer;
import Model.Overlap;
import Model.PersonalAppointment;



public class WeekViewController implements Initializable {

	private int startEventRowCreation;
	private int finalRow;
	private int currentRow;
	private Rectangle eventRect;
	private Color eventColor;
	private Color eventLabelColor;
	private GregorianCalendar cal;
	private int currentWeekDay;
	private int currentDate;
	private int forwardOrBackwardByNumberOfDays;
	private DatabaseServer tempDatabaseServer;
	private Pane pne[][];
	private GregorianCalendar weekDatesInView[];
	private ArrayList<Rectangle> eventsInView;
	private ArrayList<Label> eventLabelsInView;
	private ArrayList<PersonalAppointment> appointmentList;
	
	public WeekViewController(DatabaseServer loginServer){
		
		tempDatabaseServer = loginServer;
		
	}
	
	private void initWeekdayLabels(){

		
		for(int i=0; i<7; i++){
			
			weekDatesInView[i]=new GregorianCalendar();
			
		}
		
		switch (currentWeekDay){
			case 2:
				System.out.println("init case 2");
				weekDatesInView[0].add(GregorianCalendar.DATE, 0);
				weekDatesInView[1].add(GregorianCalendar.DATE, 1);
				weekDatesInView[2].add(GregorianCalendar.DATE, 2);
				weekDatesInView[3].add(GregorianCalendar.DATE, 3);
				weekDatesInView[4].add(GregorianCalendar.DATE, 4);
				weekDatesInView[5].add(GregorianCalendar.DATE, 5);
				weekDatesInView[6].add(GregorianCalendar.DATE, 6);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				
				break;
			case 3:
				System.out.println("init case 3");
				
				weekDatesInView[0].add(GregorianCalendar.DATE, -1);
				weekDatesInView[1].add(GregorianCalendar.DATE, 0);
				weekDatesInView[2].add(GregorianCalendar.DATE, 1);
				weekDatesInView[3].add(GregorianCalendar.DATE, 2);
				weekDatesInView[4].add(GregorianCalendar.DATE, 3);
				weekDatesInView[5].add(GregorianCalendar.DATE, 4);
				weekDatesInView[6].add(GregorianCalendar.DATE, 5);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
			case 4:
				System.out.println("init case 4");

				weekDatesInView[0].add(GregorianCalendar.DATE, -2);
				weekDatesInView[1].add(GregorianCalendar.DATE, -1);
				weekDatesInView[2].add(GregorianCalendar.DATE, 0);
				weekDatesInView[3].add(GregorianCalendar.DATE, 1);
				weekDatesInView[4].add(GregorianCalendar.DATE, 2);
				weekDatesInView[5].add(GregorianCalendar.DATE, 3);
				weekDatesInView[6].add(GregorianCalendar.DATE, 4);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
			case 5:
				System.out.println("init case 5");
				
				weekDatesInView[0].add(GregorianCalendar.DATE, -3);
				weekDatesInView[1].add(GregorianCalendar.DATE, -2);
				weekDatesInView[2].add(GregorianCalendar.DATE, -1);
				weekDatesInView[3].add(GregorianCalendar.DATE, 0);
				weekDatesInView[4].add(GregorianCalendar.DATE, 1);
				weekDatesInView[5].add(GregorianCalendar.DATE, 2);
				weekDatesInView[6].add(GregorianCalendar.DATE, 3);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
			case 6:
				System.out.println("init case 6");

				weekDatesInView[0].add(GregorianCalendar.DATE, -4);
				weekDatesInView[1].add(GregorianCalendar.DATE, -3);
				weekDatesInView[2].add(GregorianCalendar.DATE, -2);
				weekDatesInView[3].add(GregorianCalendar.DATE, -1);
				weekDatesInView[4].add(GregorianCalendar.DATE, 0);
				weekDatesInView[5].add(GregorianCalendar.DATE, 1);
				weekDatesInView[6].add(GregorianCalendar.DATE, 2);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
			case 7:
				System.out.println("init case 7");

				weekDatesInView[0].add(GregorianCalendar.DATE, -5);
				weekDatesInView[1].add(GregorianCalendar.DATE, -4);
				weekDatesInView[2].add(GregorianCalendar.DATE, -3);
				weekDatesInView[3].add(GregorianCalendar.DATE, -2);
				weekDatesInView[4].add(GregorianCalendar.DATE, -1);
				weekDatesInView[5].add(GregorianCalendar.DATE, 0);
				weekDatesInView[6].add(GregorianCalendar.DATE, 1);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
			case 1:
				System.out.println("init case 1");

				weekDatesInView[0].add(GregorianCalendar.DATE, -6);
				weekDatesInView[1].add(GregorianCalendar.DATE, -5);
				weekDatesInView[2].add(GregorianCalendar.DATE, -4);
				weekDatesInView[3].add(GregorianCalendar.DATE, -3);
				weekDatesInView[4].add(GregorianCalendar.DATE, -2);
				weekDatesInView[5].add(GregorianCalendar.DATE, -1);
				weekDatesInView[6].add(GregorianCalendar.DATE, 0);
				
				mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
				tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
				wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
				thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
				fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
				saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
				sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");
				break;
				
		}
	}
	
	public String getMonth(){
		
		return weekDatesInView[0].getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		
	}
	
	public int getYear(){
		
		return weekDatesInView[0].get(GregorianCalendar.YEAR);
		
	}
	
	public void weekForward(){
		
		for (Rectangle rect : eventsInView){
			
			rect.setVisible(false);
			
		}
		
		for (Label lbl : eventLabelsInView){
			
			lbl.setVisible(false);
			
		}
		
		eventsInView.clear();
		eventLabelsInView.clear();
		
			
		this.forwardOrBackwardByNumberOfDays=7;
		
		weekDatesInView[0].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[1].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[2].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[3].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[4].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[5].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		weekDatesInView[6].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
		
		mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
		tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
		wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
		thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
		fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
		saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
		sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");

		
		try {
			
			ArrayList<PersonalAppointment> appointmentsForToday;
			
			for (GregorianCalendar weekDaysShowing : weekDatesInView){
				
			int appointmentDate = weekDaysShowing.get(GregorianCalendar.DATE);
			int appointmentMonth = weekDaysShowing.get(GregorianCalendar.MONTH);
			int appointmentYear = weekDaysShowing.get(GregorianCalendar.YEAR);
				
			appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(appointmentYear, appointmentMonth+1, appointmentDate)));
			
			for ( PersonalAppointment event : appointmentsForToday){
				
				
				int startEventRowFifteenMinutes = (event.getStartTid().getHours()*4 + (event.getStartTid().getMinutes()/15));
				int endEventRowFifteenMinutes = (event.getSluttTid().getHours()*4 + (event.getSluttTid().getMinutes()/15));
				
				int eventLength = endEventRowFifteenMinutes - startEventRowFifteenMinutes;
				
				Label newEventLabel = new Label();
	            newEventLabel.setText(event.getBeskrivelse());
	            newEventLabel.setTextFill(eventLabelColor);
	            newEventLabel.setAlignment(Pos.TOP_CENTER);  
	            
	            eventRect = new Rectangle();
				eventRect.setWidth(142);
				eventRect.setHeight(15*eventLength);
				eventRect.setArcWidth(20);
				eventRect.setArcHeight(20);
				eventRect.setStrokeType(StrokeType.OUTSIDE);
				eventRect.setFill(eventColor);
				eventRect.setOpacity(0.5);
				
				eventsInView.add(eventRect);
				eventLabelsInView.add(newEventLabel);
				pne[event.getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventsInView.get(eventsInView.size()-1));
				pne[event.getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventLabelsInView.get(eventLabelsInView.size()-1));
				
				
			}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void weekBackward(){

	System.out.println("WeekBackward");
	
	
	for (Rectangle rect : eventsInView){
		
		rect.setVisible(false);
		
	}
	
	for (Label lbl : eventLabelsInView){
		
		lbl.setVisible(false);
		
	}
	
	eventsInView.clear();
	eventLabelsInView.clear();
	
		
	this.forwardOrBackwardByNumberOfDays=-7;
	
	weekDatesInView[0].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[1].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[2].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[3].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[4].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[5].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	weekDatesInView[6].add(GregorianCalendar.DATE, forwardOrBackwardByNumberOfDays);
	
	mondayDateLabel.setText("Monday."+ weekDatesInView[0].get(GregorianCalendar.DATE) + ".");
	tuesdayDateLabel.setText("Tuesday." + weekDatesInView[1].get(GregorianCalendar.DATE) + ".");
	wedensdayDateLabel.setText("Wedensday." + weekDatesInView[2].get(GregorianCalendar.DATE) + ".");
	thursdayDateLabel.setText("Thursday." + weekDatesInView[3].get(GregorianCalendar.DATE) + ".");
	fridayDateLabel.setText("Friday." + weekDatesInView[4].get(GregorianCalendar.DATE) + ".");
	saturdayDateLabel.setText("Saturday." + weekDatesInView[5].get(GregorianCalendar.DATE) + ".");
	sundayDateLabel.setText("Sunday." + weekDatesInView[6].get(GregorianCalendar.DATE) + ".");

	
	try {
		
		ArrayList<PersonalAppointment> appointmentsForToday;
		
		for (GregorianCalendar weekDaysShowing : weekDatesInView){
			
		int appointmentDate = weekDaysShowing.get(GregorianCalendar.DATE);
		int appointmentMonth = weekDaysShowing.get(GregorianCalendar.MONTH);
		int appointmentYear = weekDaysShowing.get(GregorianCalendar.YEAR);
			
		appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(appointmentYear, appointmentMonth+1, appointmentDate)));
		
		for ( PersonalAppointment event : appointmentsForToday){
			
			
			int startEventRowFifteenMinutes = (event.getStartTid().getHours()*4 + (event.getStartTid().getMinutes()/15));
			int endEventRowFifteenMinutes = (event.getSluttTid().getHours()*4 + (event.getSluttTid().getMinutes()/15));
			
			int eventLength = endEventRowFifteenMinutes - startEventRowFifteenMinutes;
			
			Label newEventLabel = new Label();
            newEventLabel.setText(event.getBeskrivelse());
            newEventLabel.setTextFill(eventLabelColor);
            newEventLabel.setAlignment(Pos.TOP_CENTER);  
            
            eventRect = new Rectangle();
			eventRect.setWidth(142);
			eventRect.setHeight(15*eventLength);
			eventRect.setArcWidth(20);
			eventRect.setArcHeight(20);
			eventRect.setStrokeType(StrokeType.OUTSIDE);
			eventRect.setFill(eventColor);
			eventRect.setOpacity(0.5);
			
			eventsInView.add(eventRect);
			eventLabelsInView.add(newEventLabel);
			pne[event.getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventsInView.get(eventsInView.size()-1));
			pne[event.getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventLabelsInView.get(eventLabelsInView.size()-1));
			
			
		}
		}
	}
	catch(Exception e){
		System.out.println(e);
	}
		
	}
	
	@FXML
	private Circle greenCircle;
	
	@FXML
	private Circle lightOrangeCircle;
	
	@FXML
	private Circle blueCircle;
	
	@FXML
	private Circle lightBlueCircle;
	
	@FXML
	private Circle redCircle;
	
	@FXML
	private Circle grayCircle;
	
	@FXML
	private GridPane weekGrid;

	@FXML 
	private void changeColorGray(){
		
		this.eventColor = Color.LIGHTGRAY;
		this.eventLabelColor = Color.BLACK;
		
	}
	
	@FXML
	void changeColorOrange(){
		
		this.eventColor = Color.ORANGE;
		this.eventLabelColor = Color.BLACK;
		
	}
	
	@FXML
	void changeColorLightBlue(){
		
		this.eventColor = Color.LIGHTBLUE;
		
	}
	
	@FXML
	void changeColorFuchsia(){
		
		this.eventColor = Color.FUCHSIA;
		this.eventLabelColor = Color.BLACK;
		
		
	}
	
	@FXML
	void changeColorLime(){
		
		this.eventColor = Color.LIME;
		this.eventLabelColor = Color.GRAY;
		
	}
	
	@FXML
	void changeColorBlue(){
		
		this.eventColor = Color.CADETBLUE;
		this.eventLabelColor = Color.WHITESMOKE;
		
	}
	
	private ImageView dragImageView = new ImageView();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		weekDatesInView = new GregorianCalendar[7];
		eventsInView = new ArrayList<Rectangle>();
		eventLabelsInView = new ArrayList<Label>();
		appointmentList = new ArrayList<PersonalAppointment>(); 
		cal = new GregorianCalendar();
		currentWeekDay = cal.get(Calendar.DAY_OF_WEEK);
		currentDate = cal.get(Calendar.DAY_OF_MONTH); 
		ArrayList<PersonalAppointment> appointmentsForToday;
		
		finalRow = 0;
		startEventRowCreation=0;
		this.eventColor = Color.LIME;
		this.eventLabelColor = Color.GRAY;

		pne = new Pane[8][96];	
		for(int i=1; i<8; i++){
            for(int j=0; j<96;j++){            
                    pne[i][j] = new Pane();
                    createThisPane(pne[i][j]);
                    pne[i][j].setPrefSize(120, 15);
                    weekGrid.add(pne[i][j], i, j);  
                    }
		}	

		initWeekdayLabels();
		
		try {
			
			for (GregorianCalendar weekDaysShowing : weekDatesInView){
				
				int appointmentDate = weekDaysShowing.get(GregorianCalendar.DATE);
				int appointmentMonth = weekDaysShowing.get(GregorianCalendar.MONTH);
				int appointmentYear = weekDaysShowing.get(GregorianCalendar.YEAR);
					
				//appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(appointmentYear, appointmentMonth+1, appointmentDate)));
				
				ArrayList<Overlap> tempOverlapEvents = tempDatabaseServer.appointmentOverlap(java.sql.Date.valueOf(LocalDate.of(appointmentYear, appointmentMonth+1, appointmentDate)));
				//tempOverlapEvents.get(0).g
				
				
			for ( Overlap event : tempOverlapEvents){
				
				System.out.println("Start tid er: " + event.getEvent().getStartTid());
				System.out.println("Slutt tid er: " + event.getEvent().getSluttTid());
				System.out.println("Beskrivelse: " + event.getEvent().getBeskrivelse());
				System.out.println("Dato er: " + event.getEvent().getDato());
				
				
				int startEventRowFifteenMinutes = (event.getEvent().getStartTid().getHours()*4 + (event.getEvent().getStartTid().getMinutes()/15));
				int endEventRowFifteenMinutes = (event.getEvent().getSluttTid().getHours()*4 + (event.getEvent().getSluttTid().getMinutes()/15));
				
				int eventLength = endEventRowFifteenMinutes - startEventRowFifteenMinutes;
				
				Label newEventLabel = new Label();
	            newEventLabel.setText(event.getEvent().getBeskrivelse());
	            newEventLabel.setTextFill(eventLabelColor);
	            newEventLabel.setAlignment(Pos.TOP_CENTER); 
	            int overlap = event.getAntallOverlapp()+1;
	            Pane temp = pne[event.getEvent().getDato().getDay()][startEventRowFifteenMinutes];
				
				float overlapFloat;
				
				if (overlap > 1){
					overlapFloat  = 142/(overlap);
				}
				else{
					overlapFloat = 0.0f;
				}
				
	            
	            eventRect = new Rectangle();
				eventRect.setWidth(142/overlap);
				eventRect.setHeight(15*eventLength);
				eventRect.setArcWidth(20);
				eventRect.setArcHeight(20);
				eventRect.setStrokeType(StrokeType.OUTSIDE);
				eventRect.setFill(eventColor);
				eventRect.setOpacity(0.5);
				eventRect.setX(overlapFloat);
				eventRect.setY(0);
				
				eventRect.setOnMouseClicked(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent event) {
						
						System.out.println("clicked on rect!!!!!!!!!!!!!");
						
					}
					
					
					
				});

				eventsInView.add(eventRect);
				eventLabelsInView.add(newEventLabel);

				
				pne[event.getEvent().getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventsInView.get(eventsInView.size()-1));
				pne[event.getEvent().getDato().getDay()][startEventRowFifteenMinutes].getChildren().add(eventLabelsInView.get(eventLabelsInView.size()-1));
			}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		this.forwardOrBackwardByNumberOfDays = 0;
	}

	private void createThisPane(Pane newPane){
		
		newPane.setOnDragDetected(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event) {
				/* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                Label newEventLabel = new Label();
                newEventLabel.setText("My New Event");
                newEventLabel.setTextFill(eventLabelColor);
                newEventLabel.setAlignment(Pos.TOP_CENTER);         

                Pane temp = (Pane) event.getSource();
                
				eventRect = new Rectangle();
				eventRect.setWidth(temp.getWidth());
				eventRect.setHeight(temp.getHeight());
				eventRect.setArcWidth(20);
				eventRect.setArcHeight(20);
				eventRect.setStrokeType(StrokeType.OUTSIDE);
				eventRect.setFill(eventColor);
				eventRect.setOpacity(0.5);
				
//				createThisEvent(eventRect);

            	startEventRowCreation = GridPane.getRowIndex(temp);
            	finalRow = startEventRowCreation;
            	System.out.println(startEventRowCreation);
            	
            	
            	
            	/* allow any transfer mode */
            	Dragboard db = temp.startDragAndDrop(TransferMode.ANY);
            	
            	/* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("");
                db.setContent(content);
                
            	temp.getChildren().add(eventRect);
            	temp.getChildren().add(newEventLabel);      	
            	event.consume();
            	
			}
        });
       
		newPane.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
            	 /* data is dragged over the target */
            	
            	Pane temp = (Pane) event.getSource();
            	Pane source = (Pane) event.getGestureSource();

            	currentRow = GridPane.getRowIndex(temp);
            	
            	System.out.println("This is the first row: " + startEventRowCreation);
            	System.out.println("This is the final row:" + finalRow);
            	
            	if (eventRect!=null && event.getGestureSource() != temp && GridPane.getColumnIndex(source)==GridPane.getColumnIndex(temp) && finalRow<currentRow){
            	
                System.out.println("onDragOverPlus");
                 
                eventRect.setHeight(eventRect.getHeight()+temp.getHeight());
                finalRow = currentRow;

                event.consume();
            }
            	else if (eventRect!=null && event.getGestureSource() != temp && GridPane.getColumnIndex(source)==GridPane.getColumnIndex(temp) && finalRow>currentRow){
            		            		System.out.println("onDragOverMinus");
                    
                    eventRect.setHeight(eventRect.getHeight()-temp.getHeight());
                    finalRow = currentRow;

                    event.consume();
            		
            		
            	}
            	
            	
            }
        });

		newPane.setOnDragDone(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	
		    	event.acceptTransferModes(TransferMode.ANY);
            	System.out.println("Drop occurred!");
            	Pane temp = (Pane) event.getGestureTarget();
            	System.out.println("Drag motherfucking done!!!!!!!!!!!!!!!!!!!!!");
            	
            	PersonalAppointment newEvent = new PersonalAppointment();
            	newEvent.setBeskrivelse("New event");
            	
            	int column = GridPane.getColumnIndex(temp);
//            	Date date;
//            	
//            	switch (column){
//            	
//            	case 0:
//            		date.setYear(weekDatesInView[0].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[0].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[0].get(GregorianCalendar.DATE));
//            		break;
//            	case 1:
//            		date.setYear(weekDatesInView[1].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[1].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[1].get(GregorianCalendar.DATE));
//            		break;
//            	case 2:
//            		date.setYear(weekDatesInView[2].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[2].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[2].get(GregorianCalendar.DATE));
//            		break;
//            	case 3:
//            		date.setYear(weekDatesInView[3].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[3].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[3].get(GregorianCalendar.DATE));
//            		break;
//            	case 4:
//            		date.setYear(weekDatesInView[4].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[4].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[4].get(GregorianCalendar.DATE));
//            		break;
//            	case 5:
//            		date.setYear(weekDatesInView[5].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[5].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[5].get(GregorianCalendar.DATE));
//            		break;
//            	case 6:
//            		date.setYear(weekDatesInView[6].get(GregorianCalendar.YEAR));
//            		date.setMonth(weekDatesInView[6].get(GregorianCalendar.MONTH));
//            		date.setDate(weekDatesInView[6].get(GregorianCalendar.DATE));
//            		break;
//            	default:
//            		break;
//            			
//            	
//            	}
            	
//            	System.out.println("column is: " + column);
//            	System.out.println("date is " );
//            	
//            	LocalDate
//            	
//            	newEvent.setDato(java.sql.Date.valueOf(LocalDate.of(date.getYear(), date.getMonth()+1, date.getDate())));
//            	java.sql.Date.valueOf(LocalDate)
//            	
//           
//	            int startRowIndexForEvent = startEventRowCreation;
//	            int numberOfRows = finalRow - startRowIndexForEvent;
//	            int heightForRect = (numberOfRows) * 15;
//
//                event.consume();
		    }
		});
		
	}
	
	@FXML
	private Label mondayDateLabel;
	
	@FXML
	private Label tuesdayDateLabel;
	
	@FXML
	private Label wedensdayDateLabel;
	
	@FXML
	private Label thursdayDateLabel;
	
	@FXML
	private Label fridayDateLabel;
	
	@FXML
	private Label saturdayDateLabel;
	
	@FXML 
	private Label sundayDateLabel;
	

}
