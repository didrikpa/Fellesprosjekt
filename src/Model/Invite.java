package Model;

import Server.DatabaseServer;

public class Invite {
	int InvitasjonsID;
	String Brukernavn;
	int AvtaleID;
	boolean Godtatt;
	DatabaseServer server;
	public Invite(DatabaseServer dbserver){
		server = dbserver;
	}
	
	public int getInvitasjonsID() {
		return InvitasjonsID;
	}
	public void setInvitasjonsID(int invitasjonsID) {
		InvitasjonsID = invitasjonsID;
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
	public boolean isGodtatt() {
		return Godtatt;
	}
	public void setGodtatt(boolean godtatt) {
		Godtatt = godtatt;
	}
	@Override
	public String toString() {
		PersonalAppointment pa = null;
		try {
			pa = server.specificAppointment(getAvtaleID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AvtaleID + " - \" " + pa.getBeskrivelse() + "\" invitert av " + pa.getOpprettetAv();
	}

}
