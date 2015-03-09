package Model;

import java.sql.Date;
import java.sql.Time;

public class PersonalAppointment extends SuperEvent{

	public PersonalAppointment(){}

	public int getAvtaleID() {
		return super.getAvtaleID();
	}

	public void setAvtaleID(int aid) {
		super.setAvtaleID(aid);
	}

	public Date getDato() {
		return super.getDato();
	}

	public void setDato(Date dato) {
		super.setDato(dato);
	}

	public Time getStartTid() {
		return super.getStartTid();
	}

	public void setStartTid(Time startTid) {
		super.setStartTid(startTid);
	}

	public Time getSluttTid() {
		return super.getSluttTid();
	}

	public void setSluttTid(Time sluttTid) {
		super.setSluttTid(sluttTid);
	}

	public String getBeskrivelse() {
		return super.getBeskrivelse();
	}

	public void setBeskrivelse(String beskrivelse) {
		super.setBeskrivelse(beskrivelse);
	}

	public String getRomnavn() {
		return super.getRomnavn();
	}

	public void setRomnavn(String romnavn) {
		super.setRomnavn(romnavn);
	}

	@Override
	public String toString() {
		return getBeskrivelse();
	}
}
