package timesheet;


import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class temp {

	public static void main(String[] args) {
		
		
	
		
		 

		//  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		 // String date = "27-01-2024";
		  
		  Date d = new Date();
		  
		  SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		  
		  String date = s.format(d);

		  //convert String to LocalDate
//		  LocalDate localDate = LocalDate.parse(date, formatter);
//		  
//		  DayOfWeek day = localDate.getDayOfWeek();
		  System.out.println(date);

		

	}

}
