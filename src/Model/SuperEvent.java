package Model;

import java.sql.Time;
import java.sql.Date;

public abstract class SuperEvent {
    private Date dato;
    private Time startTid;
    private Time sluttTid;
    private String beskrivelse;
    private String romnavn;
    private Integer gruppeid;

    public SuperEvent(){
        dato = null;
        startTid = null;
        sluttTid = null;
        beskrivelse = null;
        romnavn = null;
        gruppeid = null;
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
    public Integer getGruppeid() {
        return gruppeid;
    }
    public void setGruppeid(Integer gruppeid) {
        this.gruppeid = gruppeid;
    }
}
