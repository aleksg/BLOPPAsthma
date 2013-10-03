package com.blopp.bloppasthma.JsonModels;

import java.util.ArrayList;

/**
 * The log for a given day.
 * Consists of a date and a list of losDosesModel, which in turn handles which medicines that is taken, the health state of the child, etc.
 * @author aarseth_90
 *
 */
public class LogDayResult 
{
	private int healthStateId;
	
	private String date;
	
	private ArrayList<LogDosesModel> logDosesList;
	
	public LogDayResult()
	{
		logDosesList = new ArrayList<LogDosesModel>();
	}
	
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public LogDayResult setHealthStateId(int health_state_id)
	{
		this.healthStateId = health_state_id;
		return this;
	}
	
	public String getDate()
	{
		return date;
	}
	public LogDayResult setDate(String date)
	{
		this.date = date;
		return this;
	}
	
	public ArrayList<LogDosesModel> getLogDosesList()
	{
		return logDosesList;
	}
	public LogDayResult setLogDosesList(ArrayList<LogDosesModel> logDosesList)
	{
		this.logDosesList = logDosesList;
		return this;
	}

	
}
