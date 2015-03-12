package Model;

import java.sql.Time;
import java.sql.Date;

public abstract class SuperEvent {
	private int AvtaleID;
	private Date dato;
	private Time startTid;
	private Time sluttTid;
	private String beskrivelse;
	private String romnavn;
	private String opprettetAv;

	public SuperEvent(){
		AvtaleID = 0;
		dato = null;
		startTid = null;
		sluttTid = null;
		beskrivelse = null;
		romnavn = null;
		opprettetAv = null;
	}

	public String getOpprettetAv() {
		return opprettetAv;
	}

	public void setOpprettetAv(String opprettetAv) {
		this.opprettetAv = opprettetAv;
	}

	public int getAvtaleID() {
		return this.AvtaleID;
	}

	public void setAvtaleID(int aid) {
		this.AvtaleID = aid;
	}

	public Date getDato() {
		return dato;
	}

	public void setDato(Date dato) {
		this.dato = dato;
	}

	public Time getStartTid() {
		return startTid;
	}

	public void setStartTid(Time startTid) {
		this.startTid = startTid;
	}

	public Time getSluttTid() {
		return sluttTid;
	}

	public void setSluttTid(Time sluttTid) {
		this.sluttTid = sluttTid;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getRomnavn() {
		return romnavn;
	}

	public void setRomnavn(String romnavn) {
		this.romnavn = romnavn;
	}
}
