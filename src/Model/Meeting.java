package Model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by henrikmm on 3/2/15.
 */
public class Meeting extends SuperEvent{
    private Integer gruppeID;

    public Meeting(){}

    public Date getDato() {return super.getDato();}

    public void setDato(Date dato) {super.setDato(dato);}

    public Time getStartTid() {return super.getStartTid();}

    public void setStartTid(Time startTid) {super.setStartTid(startTid);}

    public Time getSluttTid() {return super.getSluttTid();}

    public void setSluttTid(Time sluttTid) {super.setSluttTid(sluttTid);}

    public String getBeskrivelse() {
        return super.getBeskrivelse();
    }

    public void setBeskrivelse(String beskrivelse) {super.setBeskrivelse(beskrivelse);}

    public String getRomnavn() {return super.getRomnavn();}

    public void setRomnavn(String romnavn) {super.setRomnavn(romnavn);}

    public Integer getGruppeID() {return gruppeID;}

    public void setGruppeID(Integer gruppeID) {this.gruppeID = gruppeID;}
}
