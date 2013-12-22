package com.blopp.bloppasthma.utils;

import org.joda.time.DateTime;


/**
 * 
 * Used to get sql formatted date from CalendarView. 
 * @author aarseth_90
 * 
 */
public class DateAdapter
{
	private int day, month, year;
	public static final String[] MONTHS = {"Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"};
	public DateAdapter()
	{	
	}
	public DateAdapter(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/**
	 * @return sql-formatted date.
	 */
	public String getSqlFormattedDate()
	{
		return "" + this.year + "-" + (this.month <= 9 ? "0"+this.month : this.month) + "-" + (this.day<=9 ? "0"+this.day : this.day);   
	}
	
	public static String getMonth(DateTime dateTime)
	{
		int prettyMonth = dateTime.getMonthOfYear();
		return MONTHS[prettyMonth-1];
	}
	
}
