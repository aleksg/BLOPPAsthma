package com.blopp.bloppasthma.models;

public class AirQuality {
	
	//AQI: Air Quality Index
	private int AQI;
	private String location;
	
	
	public AirQuality(int AQI, String location){
		this.AQI = AQI;
		this.location = location;
	}
	
	public int getAQI()
	{
		return AQI;
	}

	public void setAQI(int AQI)
	{
		this.AQI = AQI;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
}
