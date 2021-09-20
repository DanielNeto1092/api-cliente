package br.com.builders.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class DataUtil {
	public static final String FORMATO_DD_MM_AAAA = "dd/MM/yyyy";

	public static Integer cacularIdade(Date dataNascimento) {
		if (dataNascimento != null) {
			return Period.between(converterDateParaLocalDate(dataNascimento), converterDateParaLocalDate(new Date())).getYears();
		} else {
			return null;
		}
	}
	
	private static LocalDate converterDateParaLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static Date toDate(Integer ano, Integer mes, Integer dia){
		try{
			Calendar c = Calendar.getInstance(); 
			c.set(Calendar.YEAR, ano); 
			c.set(Calendar.MONTH, mes); 
			c.set(Calendar.DAY_OF_MONTH, dia != 0 ? dia : 1);

			return c.getTime();
		}catch(Exception e){
			return new Date();
		}
	}
	
	public static Date converterStringToDate(String data)  {
		try {
			SimpleDateFormat formato = new SimpleDateFormat(FORMATO_DD_MM_AAAA); 
			return formato.parse(data);
		}catch(Exception e){
			return new Date();
		}
	}
}
