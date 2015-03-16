package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

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
	
	
	private void initWeekdayLabels(){
		
		System.out.println("Current weekday is: " + currentWeekDay);
		
		switch (currentWeekDay){
			case 2:
				System.out.println("init case 2");

				mondayDateLabel.setText("Monday."+ currentDate + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate+1) + ".");
				wedensdayDateLabel.setText("Wedensday." + (currentDate+2) + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate+3) + ".");
				fridayDateLabel.setText("Friday." + (currentDate+4) + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate+5) + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+6) + ".");
				break;
			case 3:
				System.out.println("init case 3");
				mondayDateLabel.setText("Monday."+ (currentDate-1) + ".");
				tuesdayDateLabel.setText("Tuesday." + currentDate + ".");
				//tuesdayDateLabel.setStyle("-fx-background-color: red");
				wedensdayDateLabel.setText("Wedensday." + (currentDate+1) + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate+2) + ".");
				fridayDateLabel.setText("Friday." + (currentDate+3) + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate+4) + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+5) + ".");
				break;
			case 4:
				System.out.println("init case 4");

				mondayDateLabel.setText("Monday."+ (currentDate-2) + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate-1) + ".");
				wedensdayDateLabel.setText("Wedensday." + currentDate + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate+1) + ".");
				fridayDateLabel.setText("Friday." + (currentDate+2) + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate+3) + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+4) + ".");
				break;
			case 5:
				System.out.println("init case 5");

				mondayDateLabel.setText("Monday."+ (currentDate-3) + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate-2) + ".");
				wedensdayDateLabel.setText("Wedensday." + (currentDate-1) + ".");
				thursdayDateLabel.setText("Thursday." + currentDate + ".");
				fridayDateLabel.setText("Friday." + (currentDate+1) + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate+2) + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+3) + ".");
				break;
			case 6:
				System.out.println("init case 6");

				mondayDateLabel.setText("Monday."+ (currentDate-4) + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate-3) + ".");
				wedensdayDateLabel.setText("Wedensday." + (currentDate-2) + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate-1) + ".");
				fridayDateLabel.setText("Friday." + currentDate + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate+1) + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+2) + ".");
				break;
			case 7:
				System.out.println("init case 7");

				mondayDateLabel.setText("Monday."+ (currentDate-5) + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate-4) + ".");
				wedensdayDateLabel.setText("Wedensday." + (currentDate-3) + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate-2) + ".");
				fridayDateLabel.setText("Friday." + (currentDate-1) + ".");
				saturdayDateLabel.setText("Saturday." + currentDate + ".");
				sundayDateLabel.setText("Sunday." + (currentDate+1) + ".");
				break;
			case 1:
				System.out.println("init case 1");

				mondayDateLabel.setText("Monday."+ (currentDate-6) + ".");
				tuesdayDateLabel.setText("Tuesday." + (currentDate-5) + ".");
				wedensdayDateLabel.setText("Wedensday." + (currentDate-4) + ".");
				thursdayDateLabel.setText("Thursday." + (currentDate-3) + ".");
				fridayDateLabel.setText("Friday." + (currentDate-2) + ".");
				saturdayDateLabel.setText("Saturday." + (currentDate-1) + ".");
				sundayDateLabel.setText("Sunday." + currentDate + ".");
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
		
		this.forwardOrBackwardByNumberOfDays+=7;
		
		if (this.forwardOrBackwardByNumberOfDays+this.currentDate >= getDaysInMonth(this.currentMonth, this.currentYear)){
			this.currentMonth+=1;
			this.currentDate = 1;
			if (this.currentMonth > 11){
				this.currentYear+=1;
			}
		}
		
		switch (currentWeekDay) {

			
			case 2:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+6)) + ".");
				break;
			case 3:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");	
				break;
			case 4:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
				break;
			case 5:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
				break;
			case 6:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
				break;
			case 7:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
				break;
			case 1:
				mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6)) + ".");
				tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-5)) + ".");
				wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
				thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
				fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
				saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
				sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
				break;
			}
		
	}
	
	public void weekBackward(){
		
		this.forwardOrBackwardByNumberOfDays-=7;
		
		if (this.forwardOrBackwardByNumberOfDays+this.currentDate <= 1){
			this.currentMonth-=1;
			
			if (this.currentMonth < 0){
				this.currentYear-=1;
			}
			this.currentDate = getDaysInMonth(this.currentMonth, this.currentYear);
		}
		
	switch (currentWeekDay) {

		
		case 2:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+6)) + ".");
			break;
		case 3:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-1)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+5)) + ".");	
			break;
		case 4:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-2)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+4)) + ".");
			break;
		case 5:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-3)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+3)) + ".");
			break;
		case 6:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-4)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+2)) + ".");
			break;
		case 7:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-5)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)+1)) + ".");
			break;
		case 1:
			mondayDateLabel.setText("Monday."+ (getDateInSomeDays(currentDate, currentMonth, currentYear, forwardOrBackwardByNumberOfDays-6)) + ".");
			tuesdayDateLabel.setText("Tuesday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-5)) + ".");
			wedensdayDateLabel.setText("Wedensday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-4)) + ".");
			thursdayDateLabel.setText("Thursday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-3)) + ".");
			fridayDateLabel.setText("Friday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-2)) + ".");
			saturdayDateLabel.setText("Saturday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays)-1)) + ".");
			sundayDateLabel.setText("Sunday." + (getDateInSomeDays(currentDate, currentMonth, currentYear, (forwardOrBackwardByNumberOfDays))) + ".");
			break;
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
		cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		currentWeekDay = cal.get(Calendar.DAY_OF_WEEK);
		currentDate = cal.get(Calendar.DAY_OF_MONTH); 
		System.out.println(currentDate);
		currentMonth = cal.get(Calendar.MONTH);
		currentYear = cal.get(Calendar.YEAR);
		
//		initWeekdayLabels();
		
		this.forwardOrBackwardByNumberOfDays = 0;
		
		
		finalRow = 0;
		startEventRowCreation=0;
		this.eventColor = Color.LIME;
		this.eventLabelColor = Color.GRAY;

		Pane[][] pne = new Pane[8][96];	
		for(int i=1; i<8; i++){
            for(int j=0; j<96;j++){            
                    pne[i][j] = new Pane();
                    createThisPane(pne[i][j]);
                    weekGrid.add(pne[i][j], i, j);  
                    }
		}

		
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
