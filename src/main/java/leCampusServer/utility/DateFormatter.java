package leCampusServer.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
	
	private static final String SERVER_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	//private static final String SERVER_PATTERN_2 = "yyyy-MM-dd";
	private static final String THE_LIST = "yyyy-MM-dd'T'HH:mmZ";
	private static final String EVENT_BRITE = "yyyy-MM-dd";

	public String dateStringToServerDateString (String dateString, boolean randomTime) {
		DateFormat serverDateFormat = new SimpleDateFormat(SERVER_PATTERN);
		DateFormat dateFormatA = new SimpleDateFormat(THE_LIST);
		DateFormat dateFormatB = new SimpleDateFormat(EVENT_BRITE);
		
		Date result = null;
		try {
			result = dateFormatA.parse(dateString);
			
		} catch (ParseException e) { }
		
		try {
			result = dateFormatB.parse(dateString);
			
		} catch (ParseException e) { }
		
		if (randomTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(result);
			int startHour = 7;
			calendar.set(Calendar.HOUR_OF_DAY, (int) (startHour+(Math.random()*10)));
			result = calendar.getTime();
		}
		
		return serverDateFormat.format(result);
		
	}
	
	public String dateToServerDateString (Date date) {
		DateFormat serverDateFormat = new SimpleDateFormat(SERVER_PATTERN);
		return serverDateFormat.format(date);
	}
	
	public Date serverDateStringToDate (String string) {
		DateFormat serverDateFormat = new SimpleDateFormat(SERVER_PATTERN);
		Date result = null;
		try {
			result = serverDateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String randomEndTime (String startTime) {
		DateFormat serverDateFormat = new SimpleDateFormat(SERVER_PATTERN);
		Date result = null;
		try {
			result = serverDateFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(result);
			calendar.add(Calendar.HOUR_OF_DAY, (int) (1+Math.random()*3));
			result = calendar.getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return serverDateFormat.format(result);
	}
}
