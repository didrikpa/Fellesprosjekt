package Controller;

import java.util.ArrayList;
import java.awt.Checkbox;
import java.util.Calendar;
import java.util.Date;
import Model.PersonalAppointment;
import Server.DatabaseServer;


@SuppressWarnings("unused")
public class EventSearchController {
	private DatabaseServer server = new DatabaseServer();
	public EventSearchController(DatabaseServer db){
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
