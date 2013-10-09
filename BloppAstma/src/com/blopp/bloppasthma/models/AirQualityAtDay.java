package com.blopp.bloppasthma.models;

import java.util.ArrayList;

public class AirQualityAtDay {
	
	private ArrayList<AirQuality> airQualityAtDay;
	
	public AirQualityAtDay()
	{
		this.airQualityAtDay = new ArrayList<AirQuality>();
	}
	
	public ArrayList<AirQuality> getAirQualityAtDay()
	{
		return airQualityAtDay;
	}
	
	public void setAirQualityAtDay(ArrayList<AirQuality> airQualityAtDay)
	{
		this.airQualityAtDay = airQualityAtDay;
	}

}
