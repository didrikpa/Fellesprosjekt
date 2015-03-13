package Model;

import java.sql.Timestamp;

import Server.DatabaseServer;

public class Alarm implements Påminnelse {
	DatabaseServer server;
	int AvtaleID;
	Timestamp Tidspunkt;
	String Brukernavn;

	public Alarm(DatabaseServer dbserver) {
		server = dbserver;
	}

	public String getBrukernavn() {
		return Brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		Brukernavn = brukernavn;
	}

	public int getAvtaleID() {
		return AvtaleID;
	}

	public void setAvtaleID(int avtaleID) {
		AvtaleID = avtaleID;
	}

	public Timestamp getTidspunkt() {
		return Tidspunkt;
	}

	public void setTidspunkt(Timestamp tidspunkt) {
		Tidspunkt = tidspunkt;
	}

	@Override
	public String toString() {
		PersonalAppointment pa = null;
		try {
			pa = server.getSpecificAppointment(getAvtaleID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Påminnelse: " + pa.getStartTid() + "-" + pa.getSluttTid() + " "
		+ pa.getBeskrivelse();
	}
}
