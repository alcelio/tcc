package com.tc.util;

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
	
	/**
	 * Retorna a diferen�a do n�mero de dias entre as datas
	 */
	public static Integer diffInDays(Date date1, Date date2) {
		Long dias = 0l;
		if (date1 != null && date2 != null) {
			long diff = 0l;
			if (date2.compareTo(date1) > 0)
				diff = date2.getTime() - date1.getTime();
			else
				diff = date1.getTime() - date2.getTime();
			dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}
		return dias.intValue();
	}
	
	/**
	 * Retorna a escrita do dia da semana, conforme n�mero do dia da semana (Ex: 'Domingo' para 1, 'Segunda' p/2, etc.)
	 */
	public static String weekNameByNumber(Integer weekDay) {
		try {
			return new DateFormatSymbols().getWeekdays()[weekDay];
		} catch (Exception e) {
			return String.valueOf(weekDay);
		}
	}
	
}