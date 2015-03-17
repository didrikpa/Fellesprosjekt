package Model;

public class Overlap implements Comparable<Overlap>{
	PersonalAppointment event;
	int antallOverlapp;
	public Overlap(){
		antallOverlapp = 1;
		event = null;
	}
	public PersonalAppointment getEvent() {
		return event;
	}
	public void setEvent(PersonalAppointment event) {
		this.event = event;
	}
	public int getAntallOverlapp() {
		return antallOverlapp;
	}
	public void setAntallOverlapp() {
		this.antallOverlapp += 1;
	}
	@Override
	public String toString() {
		return event + " :- " + antallOverlapp;
	}
	@Override
	public int compareTo(Overlap o) {
		if(this.getEvent().getStartTid().before(o.getEvent().getStartTid())){
			return 1;
		}
		if(this.getEvent().getStartTid().equals(o.getEvent().getStartTid())){
			if(this.antallOverlapp > o.antallOverlapp){
				return 1;
			}
			else{
				return 0;
			}
		}
		else{
			return 0;
		}
	}
}
