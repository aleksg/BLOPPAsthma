package com.blopp.bloppasthma.models;

import java.util.ArrayList;

public class AirQualityAtDay {
	
	private ArrayList<AirQualityState> airQualityAtDay;
	
	public AirQualityAtDay()
	{
		this.airQualityAtDay = new ArrayList<AirQualityState>();
	}
	
	public ArrayList<AirQualityState> getAirQualityAtDay()
	{
		return airQualityAtDay;
	}
	
	public void setAirQualityAtDay(ArrayList<AirQualityState> airQualityAtDay)
	{
		this.airQualityAtDay = airQualityAtDay;
	}

}
