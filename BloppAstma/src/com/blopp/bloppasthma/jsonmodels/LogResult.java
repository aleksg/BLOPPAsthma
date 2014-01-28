package com.blopp.bloppasthma.jsonmodels;

import java.util.ArrayList;

public class LogResult
{
	private boolean sqlSuccess;
	private int childId;
	private int month, year;
	private String query;
	private ArrayList<LogDayResult> logDayResults;

	public LogResult()
	{
		logDayResults = new ArrayList<LogDayResult>();
	}

	public ArrayList<LogDayResult> getLogDayResults()
	{
		return logDayResults;
	}

	public LogResult setLogDayResults(ArrayList<LogDayResult> logDayResults)
	{
		this.logDayResults = logDayResults;
		return this;
	}

	public boolean isSqlSuccess()
	{
		return sqlSuccess;
	}

	public LogResult setSqlSuccess(boolean sqlSuccess)
	{
		this.sqlSuccess = sqlSuccess;
		return this;
	}

	public int getChildId()
	{
		return childId;
	}

	public LogResult setChildId(int childId)
	{
		this.childId = childId;
		return this;
	}

	public int getMonth()
	{
		return month;
	}

	public LogResult setMonth(int month)
	{
		this.month = month;
		return this;
	}

	public int getYear()
	{
		return year;
	}

	public LogResult setYear(int year)
	{
		this.year = year;
		return this;
	}

	public String getQuery()
	{
		return query;
	}

	public LogResult setQuery(String query)
	{
		this.query = query;
		return this;
	}

	// TODO: Fix dates as SimpleDateFormat or something??
	public int getHealthStateIdByDate(String d)
	{
		for (LogDayResult result : logDayResults)
		{
			String date = result.getDate();
			if (date.equalsIgnoreCase(d))
			{
				return result.getHealthStateId();
			}
		}
		return -1;
	}

}
