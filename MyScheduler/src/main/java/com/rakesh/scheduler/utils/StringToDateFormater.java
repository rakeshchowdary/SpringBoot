package com.rakesh.scheduler.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.apache.log4j.Logger;


public class StringToDateFormater {
	private static final Logger logger = Logger.getLogger(StringToDateFormater.class.getName());

	static Date result;
	int hoursOfDay;
	int minutesOfDay;
	int monthOfYear;
	int dayOfWeek;
	int dayOfMonth;

	public StringToDateFormater() {

	}

	public Date returnDate(String input) {
		logger.info("Test111111111");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			logger.info("Test2222222");
			result = dateFormat.parse(input);
			logger.info("Test4444"+result);

		} catch (ParseException p) {
			logger.error(p.getMessage());
		}
		return result;
	}

	public int returnHoursOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		hoursOfDay = c.get(Calendar.HOUR_OF_DAY);
		// System.out.println("hoursOfDay----> " + hoursOfDay);
		return hoursOfDay;
	}

	public int returnMinutesOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		minutesOfDay = c.get(Calendar.MINUTE);
		// System.out.println("minutesOfDay----> " + minutesOfDay);
		return minutesOfDay;
	}

	public int returnMonthOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		monthOfYear = c.get(Calendar.MONTH) + 1;
		// System.out.println(" monthOfYear----> " + monthOfYear);
		return monthOfYear;

	}

	public int returnDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		// System.out.println(" dayOfWeek----> " + dayOfWeek);
		return dayOfWeek;
	}

	public int returnDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		// System.out.println(" dayOfMonth----> " + dayOfMonth);
		return dayOfMonth;
	}

	// public static void main(String... strings) {
	// StringToDateFormater obj = new StringToDateFormater();
	// obj.returnDate("2018-07-10 10:30");
	// obj.returnDayOfMonth(result);
	// obj.returnDayOfWeek(result);
	// obj.returnHoursOfDay(result);
	// obj.returnMinutesOfDay(result);
	// obj.returnMonthOfYear(result);
	//
	// }

}
