package com.society.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public String dateToString(Date data) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormat.format(data); 
	}

	public Date addHora(Date data, int quantidade) {
		
		GregorianCalendar gc = new GregorianCalendar();
	    gc.setTime(data);
	    gc.add(Calendar.HOUR,quantidade);
	    
		return gc.getTime();
	}
	
}
