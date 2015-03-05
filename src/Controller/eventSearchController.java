package Controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.awt.Checkbox;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Model.PersonalAppointment;
import Server.DatabaseServer;
import snippet.Avtale;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

@SuppressWarnings("unused")
public class eventSearchController{
	private DatabaseServer server = new DatabaseServer();
	public eventSearchController(DatabaseServer db){
		server = db;
	}
	ArrayList<PersonalAppointment> eventSearch(String sokeord, boolean upcoming, ArrayList<PersonalAppointment> appointmentsIn){
		String søker = sokeord;
		søker = søker.toLowerCase();
		ArrayList<PersonalAppointment> filtered = new ArrayList<PersonalAppointment>();
		ArrayList<PersonalAppointment> appointmentsOut = new ArrayList<PersonalAppointment>();
		if(upcoming){
			for(PersonalAppointment pa : appointmentsIn){
				if(!before(pa))filtered.add(pa);
			}
		}
		else{
			for(PersonalAppointment pa : appointmentsIn){
				if(before(pa))filtered.add(pa);
			}
		}
		while(!søker.equals("")){
			for(PersonalAppointment pa : filtered){
				if(pa.getBeskrivelse().toLowerCase().contains(søker)){
					if(!appointmentsOut.contains(pa)){
						appointmentsOut.add(pa);
					}
				}
			}
			String [] swSplit = søker.split("");
			søker = "";
			int ant = swSplit.length - 1;
			for (int i = 0; i < ant; i++){
				if(!(søker.length() == ant) || søker.length() < 3)søker += swSplit[i];
			}
		}
		return appointmentsOut;
	}

	private boolean before(PersonalAppointment avtale){
		Date now = Calendar.getInstance().getTime();
		Date avtalen = avtale.getDato();
		if(now.after(avtalen)){
			return true;
		}
		return false;
	}
}	
