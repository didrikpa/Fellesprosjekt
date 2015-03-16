package Controller;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.javafx.font.directwrite.RECT;

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
	private int currentMonth;
	private int currentYear;
	private int forwardOrBackwardByNumberOfDays;
	private DatabaseServer tempDatabaseServer;
	private Pane pne[][];
	private int weekDatesInView[];
	private ArrayList<Rectangle> eventsInView;
	private ArrayList<Label> eventLabelsInView;
	
	public WeekViewController(DatabaseServer loginServer){
		
		tempDatabaseServer = loginServer;
		
	}
	
	private void showEvent(PersonalAppointment appointment){
		
		Date dateObject = appointment.getDato();
		int date = dateObject.getDate();
		int month = dateObject.getMonth();
		int year = dateObject.getYear();
		Time startTime = appointment.getStartTid();
		Time endTime = appointment.getSluttTid();
		
		
		
		
		
		
	}
	
	private void initWeekdayLabels(){
		
		weekDatesInView = new int[7];
		
		System.out.println("Current weekday is: " + currentWeekDay);
		
		switch (currentWeekDay){
			case 2:
				System.out.println("init case 2");
				mondayDateLabel.setText("Monday."+ currentDate + ".");
				weekDatesInView[0] = currentDate;
				tuesdayDateLabel.setText("Tuesday." + (currentDate+1) + ".");
				weekDatesInView[1] = currentDate+1;
				wedensdayDateLabel.setText("Wedensday." + (currentDate+2) + ".");
				weekDatesInView[2] = currentDate+2;
				thursdayDateLabel.setText("Thursday." + (currentDate+3) + ".");
				weekDatesInView[3] = currentDate+3;
				fridayDateLabel.setText("Friday." + (currentDate+4) + ".");
				weekDatesInView[4] = currentDate+4;
				saturdayDateLabel.setText("Saturday." + (currentDate+5) + ".");
				weekDatesInView[5] = currentDate+5;
				sundayDateLabel.setText("Sunday." + (currentDate+6) + ".");
				weekDatesInView[6] = currentDate+6;
				
				break;
			case 3:
				System.out.println("init case 3");
				mondayDateLabel.setText("Monday."+ (currentDate-1) + ".");
				weekDatesInView[0] = currentDate-1;
				tuesdayDateLabel.setText("Tuesday." + currentDate + ".");
				weekDatesInView[1] = currentDate;
				//tuesdayDateLabel.setStyle("-fx-background-color: red");
				wedensdayDateLabel.setText("Wedensday." + (currentDate+1) + ".");
				weekDatesInView[2] = currentDate+1;
				thursdayDateLabel.setText("Thursday." + (currentDate+2) + ".");
				weekDatesInView[3] = currentDate+2;
				fridayDateLabel.setText("Friday." + (currentDate+3) + ".");
				weekDatesInView[3] = currentDate+3;
				saturdayDateLabel.setText("Saturday." + (currentDate+4) + ".");
				weekDatesInView[3] = currentDate+4;
				sundayDateLabel.setText("Sunday." + (currentDate+5) + ".");
				weekDatesInView[3] = currentDate+5;
				break;
			case 4:
				System.out.println("init case 4");

				mondayDateLabel.setText("Monday."+ (currentDate-2) + ".");
				weekDatesInView[0] = currentDate-2;
				tuesdayDateLabel.setText("Tuesday." + (currentDate-1) + ".");
				weekDatesInView[1] = currentDate-1;
				wedensdayDateLabel.setText("Wedensday." + currentDate + ".");
				weekDatesInView[2] = currentDate;
				thursdayDateLabel.setText("Thursday." + (currentDate+1) + ".");
				weekDatesInView[3] = currentDate+1;
				fridayDateLabel.setText("Friday." + (currentDate+2) + ".");
				weekDatesInView[4] = currentDate+2;
				saturdayDateLabel.setText("Saturday." + (currentDate+3) + ".");
				weekDatesInView[5] = currentDate+3;
				sundayDateLabel.setText("Sunday." + (currentDate+4) + ".");
				weekDatesInView[6] = currentDate+4;
				break;
			case 5:
				System.out.println("init case 5");

				mondayDateLabel.setText("Monday."+ (currentDate-3) + ".");
				weekDatesInView[0] = currentDate-3;
				tuesdayDateLabel.setText("Tuesday." + (currentDate-2) + ".");
				weekDatesInView[1] = currentDate-2;
				wedensdayDateLabel.setText("Wedensday." + (currentDate-1) + ".");
				weekDatesInView[2] = currentDate-1;
				thursdayDateLabel.setText("Thursday." + currentDate + ".");
				weekDatesInView[3] = currentDate;
				fridayDateLabel.setText("Friday." + (currentDate+1) + ".");
				weekDatesInView[4] = currentDate+1;
				saturdayDateLabel.setText("Saturday." + (currentDate+2) + ".");
				weekDatesInView[5] = currentDate+2;
				sundayDateLabel.setText("Sunday." + (currentDate+3) + ".");
				weekDatesInView[6] = currentDate+3;
				break;
			case 6:
				System.out.println("init case 6");

				mondayDateLabel.setText("Monday."+ (currentDate-4) + ".");
				weekDatesInView[0] = currentDate-4;
				tuesdayDateLabel.setText("Tuesday." + (currentDate-3) + ".");
				weekDatesInView[1] = currentDate-3;
				wedensdayDateLabel.setText("Wedensday." + (currentDate-2) + ".");
				weekDatesInView[2] = currentDate-2;
				thursdayDateLabel.setText("Thursday." + (currentDate-1) + ".");
				weekDatesInView[3] = currentDate-1;
				fridayDateLabel.setText("Friday." + currentDate + ".");
				weekDatesInView[4] = currentDate;
				saturdayDateLabel.setText("Saturday." + (currentDate+1) + ".");
				weekDatesInView[5] = currentDate+1;
				sundayDateLabel.setText("Sunday." + (currentDate+2) + ".");
				weekDatesInView[6] = currentDate+2;
				break;
			case 7:
				System.out.println("init case 7");

				mondayDateLabel.setText("Monday."+ (currentDate-5) + ".");
				weekDatesInView[0] = currentDate-5;
				tuesdayDateLabel.setText("Tuesday." + (currentDate-4) + ".");
				weekDatesInView[1] = currentDate-4;
				wedensdayDateLabel.setText("Wedensday." + (currentDate-3) + ".");
				weekDatesInView[2] = currentDate-3;
				thursdayDateLabel.setText("Thursday." + (currentDate-2) + ".");
				weekDatesInView[3] = currentDate-2;
				fridayDateLabel.setText("Friday." + (currentDate-1) + ".");
				weekDatesInView[4] = currentDate-1;
				saturdayDateLabel.setText("Saturday." + currentDate + ".");
				weekDatesInView[5] = currentDate;
				sundayDateLabel.setText("Sunday." + (currentDate+1) + ".");
				weekDatesInView[6] = currentDate+1;
				break;
			case 1:
				System.out.println("init case 1");

				mondayDateLabel.setText("Monday."+ (currentDate-6) + ".");
				weekDatesInView[0] = currentDate-6;
				tuesdayDateLabel.setText("Tuesday." + (currentDate-5) + ".");
				weekDatesInView[1] = currentDate-5;
				wedensdayDateLabel.setText("Wedensday." + (currentDate-4) + ".");
				weekDatesInView[2] = currentDate-4;
				thursdayDateLabel.setText("Thursday." + (currentDate-3) + ".");
				weekDatesInView[3] = currentDate-3;
				fridayDateLabel.setText("Friday." + (currentDate-2) + ".");
				weekDatesInView[4] = currentDate-2;
				saturdayDateLabel.setText("Saturday." + (currentDate-1) + ".");
				weekDatesInView[5] = currentDate-1;
				sundayDateLabel.setText("Sunday." + currentDate + ".");
				weekDatesInView[6] = currentDate;
				break;
				
		}
	}
	
	private int getDateInSomeDays(int dateFrom, int monthFrom, int yearFrom, int differenceDays){
		
		if (dateFrom+differenceDays >= 1 && dateFrom+differenceDays <= getDaysInMonth(monthFrom, yearFrom)){
			
			return dateFrom + differenceDays;
			
		}
		else if (dateFrom + differenceDays < 1){
			
			int tempDate = dateFrom + differenceDays;
			int tempMonth = monthFrom;
			int tempYear = yearFrom;
			
			while(tempDate <= 0){
				
				if(tempMonth > 0){
					tempMonth-=1;
					tempDate += getDaysInMonth(tempMonth, tempYear);
				} else {
					tempMonth = 11;
					tempYear-=1;
					tempDate += getDaysInMonth(tempMonth, tempYear);
				}
			}
			return tempDate;
		}else {
			
			int tempDate = dateFrom + differenceDays;
			int tempMonth = monthFrom;
			int tempYear = yearFrom;
			
			while(tempDate > getDaysInMonth(tempMonth,tempYear)){
				
				if(tempMonth <= 11){
					tempDate -= getDaysInMonth(tempMonth, tempYear);
					tempMonth+=1;
				} else {
					tempDate -= getDaysInMonth(tempMonth, tempYear);
					tempMonth = 0;
					tempYear+=1;
				}
				
			}
			return tempDate;
			
			
		}
	}
	
	private int getDaysInMonth(int month, int year){
		
		switch(month){
		
		case 0:
			return 31;
		case 1:
			if (cal.isLeapYear(year)==true){
				return 29;
			}else{
			return 28;
			}
		case 2:
			return 31;
		case 3:
			return 30;
		case 4:
			return 31;
		case 5:
			return 30;
		case 6:
			return 31;
		case 7:
			return 31;
		case 8:
			return 30;
		case 9:
			return 31;
		case 10:
			return 30;
		case 11:
			return 31;
		default:
			return 0;
		
		}
		
	}

	public String displayMonthName(){
		
		switch(currentMonth){
		
		case 0:
			return "January";
		case 1:
			return "February";
		case 2:
			return "March";
		case 3:
			return "April";
		case 4:
			return "May";
		case 5:
			return "June";
		case 6:
			return "July";
		case 7:
			return "August";
		case 8:
			return "September";
		case 9:
			return "October";
		case 10:
			return "November";
		case 11:
			return "December";
		default:
			return "Month";
		}
	}
	
	public int getCurrentMonth(){
		
		return currentMonth;
		
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
		
		this.forwardOrBackwardByNumberOfDays+=7;
		
		switch (currentWeekDay) {

			
			case 2:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+5);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+6)) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+6);
				break;
			case 3:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");	
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+5);
				break;
			case 4:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
				break;
			case 5:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
				break;
			case 6:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
				break;
			case 7:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
				break;
			case 1:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6)) + ".");
				weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6);
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-5)) + ".");
				weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5);
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
				weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
				break;
			}
		
		for (int tmpDate : weekDatesInView){
			
			System.out.println("Dato som vises: " + tmpDate);
			
		}
		
		for (int tmpDate : weekDatesInView){
			
			if (tmpDate==getDaysInMonth(currentMonth, currentYear)){
				if(currentMonth==11){
					currentMonth=0;
					System.out.println("Month updated: " + currentMonth);
					currentYear++;
				}
				else{
					currentMonth++;
					System.out.println("Month updated: " + currentMonth);
				}
			}
			
		}
		

		try {
			
			ArrayList<PersonalAppointment> appointmentsForToday;
			
			for (int weekDaysShowing : weekDatesInView){
				
			appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(currentYear, currentMonth+1, weekDaysShowing)));
			
			for ( PersonalAppointment event : appointmentsForToday){
				
				System.out.println("Start tid er: " + event.getStartTid());
				System.out.println("Slutt tid er: " + event.getSluttTid());
				System.out.println("Beskrivelse: " + event.getBeskrivelse());
				System.out.println("Dato er: " + event.getDato());
				
				
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

	
	
	for (Rectangle rect : eventsInView){
		
		rect.setVisible(false);
		
	}
	
	for (Label lbl : eventLabelsInView){
		
		lbl.setVisible(false);
		
	}
	
	eventsInView.clear();
	eventLabelsInView.clear();
	
		
	this.forwardOrBackwardByNumberOfDays-=7;
		
	switch (currentWeekDay) {

	
	case 2:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+5);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+6)) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+6);
		break;
	case 3:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");	
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+5);
		break;
	case 4:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+4);
		break;
	case 5:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+3);
		break;
	case 6:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+2);
		break;
	case 7:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays+1);
		break;
	case 1:
		mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6)) + ".");
		weekDatesInView[0]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6);
		tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-5)) + ".");
		weekDatesInView[1]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5);
		wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
		weekDatesInView[2]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4);
		thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
		weekDatesInView[3]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3);
		fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
		weekDatesInView[4]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2);
		saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
		weekDatesInView[5]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1);
		sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
		weekDatesInView[6]=getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays);
		break;
	}
	
	for (int tmpDate : weekDatesInView){
		
		System.out.println("Dato som vises: " + tmpDate);
		
	}
	
	for (int tmpDate : weekDatesInView){
		
		if (tmpDate==1){
			if(currentMonth==0){
				currentMonth=11;
				currentYear--;
			}
			else{
				currentMonth--;
			}
		}
		
	}

	
	try {
		
		ArrayList<PersonalAppointment> appointmentsForToday;
		
		for (int weekDaysShowing : weekDatesInView){
			
		appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(currentYear, currentMonth+1, weekDaysShowing)));
		
		for ( PersonalAppointment event : appointmentsForToday){
			
			System.out.println("Start tid er: " + event.getStartTid());
			System.out.println("Slutt tid er: " + event.getSluttTid());
			System.out.println("Beskrivelse: " + event.getBeskrivelse());
			System.out.println("Dato er: " + event.getDato());
			
			
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
		
		eventsInView = new ArrayList<Rectangle>();
		eventLabelsInView = new ArrayList<Label>();
		cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		currentWeekDay = cal.get(Calendar.DAY_OF_WEEK);
		currentDate = cal.get(Calendar.DAY_OF_MONTH); 
		System.out.println(currentDate);
		currentMonth = cal.get(Calendar.MONTH);
		currentYear = cal.get(Calendar.YEAR);

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
		for (int i : weekDatesInView){
			
			System.out.println(i);
			
		}
		try {
			
			for (int weekDaysShowing : weekDatesInView){
				
				
			appointmentsForToday = tempDatabaseServer.getAppointment(java.sql.Date.valueOf(LocalDate.of(currentYear, currentMonth+1, weekDaysShowing)));
			
			for ( PersonalAppointment event : appointmentsForToday){

				
				System.out.println("Start tid er: " + event.getStartTid());
				System.out.println("Slutt tid er: " + event.getSluttTid());
				System.out.println("Beskrivelse: " + event.getBeskrivelse());
				System.out.println("Dato er: " + event.getDato());
				
				
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
            	System.out.println("Is this empty");
            	
//            	if (event.getGestureTarget() instanceof Rectangle){
//            		
//            		
//            		
//            	}
           
	            int startRowIndexForEvent = startEventRowCreation;
	            int numberOfRows = finalRow - startRowIndexForEvent;
	            int heightForRect = (numberOfRows) * 15;

                event.consume();
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
