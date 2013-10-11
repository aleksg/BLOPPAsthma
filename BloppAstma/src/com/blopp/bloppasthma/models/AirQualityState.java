package com.blopp.bloppasthma.models;

import android.graphics.Color;

public class AirQualityState {
	
	//AQI: Air Quality Index
	private int AQI;
	private String location;
	private int color;
	private String shortDescription;
	private String longDescription;
	
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
	
	//Refers to the short description
	public AirQualityState setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
		return this;
	}
	public String getShortDescription()
	{
		return this.shortDescription;
	}
	
	//Refers to the long description
	public AirQualityState setLongDescription(String longDescription)
	{
		this.longDescription = longDescription;
		return this;
	}
	public String getLongDescription()
	{
		return this.longDescription;
	}
	@Override
	public String toString() {
		
		return super.toString();
	}
}
