package com.blopp.bloppapi.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Child
{
	private String name;
	private int uid;
	private int credits;
	private int healthState;
	private int medicalPlanId;
	public Child(){
		
	}
	public Child(int healthState){
		
	}
	public int getUid()
	{
		return uid;
	}
	public void setUid(int uid)
	{
		this.uid = uid;
	}
	public int getCredits()
	{
		return credits;
	}
	public void setCredits(int credits)
	{
		this.credits = credits;
	}
	public int getHealthState()
	{
		return healthState;
	}
	public void setHealthState(int healthState)
	{
		this.healthState = healthState;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String toPostParameters() throws UnsupportedEncodingException
	{
		String ENCODING_TYPE = "UTF-8";
		try
		{
			String _name = URLEncoder.encode(getName(), ENCODING_TYPE);
			String _healthState = URLEncoder.encode(Integer.toString(getHealthState()), ENCODING_TYPE);
			return String.format("name=%s&states[]=%s", _name, _healthState);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			throw e;
		}	
	}
	public int getMedicalPlanId()
	{
		return medicalPlanId;
	}
	public void setMedicalPlanId(int medicalPlanId)
	{
		this.medicalPlanId = medicalPlanId;
	}
	
}
