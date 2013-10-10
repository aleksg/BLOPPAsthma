package com.blopp.bloppasthma.models;

import android.graphics.Color;

public class AirQualityState {
	
	//AQI: Air Quality Index
	private int AQI;
	private String location;
	private int color;
	private String description;
	public AirQualityState(){
		
	}
	
	public int getAQI()
	{
		return AQI;
	}

	public AirQualityState setAQI(int AQI)
	{
		this.AQI = AQI;
		return this;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public AirQualityState setLocation(String location)
	{
		this.location = location;
		return this;
	}
	
	public AirQualityState setColor(String color)
	{
		this.color = Color.parseColor(String.format("#%s", color));
		
		return this;
	}
	public int getColor()
	{
		return this.color;
	}
	public AirQualityState setDescription(String description)
	{
		this.description = description;
		return this;
	}
	public String getDescription()
	{
		return this.description;
	}
	
}
