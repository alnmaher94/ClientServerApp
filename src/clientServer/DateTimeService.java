package clientServer;

import java.util.Calendar;
import java.util.Date;

public class DateTimeService {
	
	private Calendar calendar;
	
	DateTimeService(){
		this.calendar = Calendar.getInstance();
	}
	
	public String getDateAndTime(){
		Date d = calendar.getTime();
		
		return "The current time is " + d.toString();
	}
}
