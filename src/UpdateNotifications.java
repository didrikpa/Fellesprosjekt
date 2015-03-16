package Controller;

public class UpdateNotifications extends Thread{
	CalendarViewController cvc;
	public UpdateNotifications(	CalendarViewController cv){
		cvc = cv;
	}
	
    public void run() {
        while (true) {
            try {cvc.notifications();} 
            catch (Exception e1) {}
            try {Thread.sleep(1000);} 
            catch (InterruptedException e) {}
        }
    }
}
