package com.blopp.bloppasthma.models;

import android.graphics.Color;

public class AirQuality {
	
	//AQI: Air Quality Index
	private int AQI;
	private String location;
	private int color;
	private String description;
	public AirQuality(){
		
	}
	
	public int getAQI()
	{
		return AQI;
	}

	public AirQuality setAQI(int AQI)
	{
		this.AQI = AQI;
		return this;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public AirQuality setLocation(String location)
	{
		this.location = location;
		return this;
	}
	
	public AirQuality setColor(String color)
	{
		this.color = Color.parseColor(String.format("#%s", color));
		
		return this;
	}
	public int getColor()
	{
		return this.color;
	}
	public AirQuality setDescription(String description)
	{
		this.description = description;
		return this;
	}
	public String getDescription()
	{
		return this.description;
	}
	
}
