package com.blopp.bloppapi.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Treatment {
	
	private int medicineID;
	private int uid;
	private String time;
	private int HealthState;
	private String dayDate; //YYYY-mm-dd
	private int medicalPlanDoseID;
	
	
	public int getMedicineID() {
		return medicineID;
	}
	public void setMedicineID(int medicineID) {
		this.medicineID = medicineID;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getHealthState() {
		return HealthState;
	}
	public void setHealthStateID(int healthState) {
		HealthState = healthState;
	}
	public String getDayDate() {
		return dayDate;
	}
	public void setDayDate(String dayDate) {
		this.dayDate = dayDate;
	}
	public int getMedicalPlanDoseID() {
		return medicalPlanDoseID;
	}
	public void setMedicalPlanDoseID(int medicalPlanDoseID) {
		this.medicalPlanDoseID = medicalPlanDoseID;
	}
	
	public String toPostParameters() throws UnsupportedEncodingException
	{
		String ENCODING_TYPE = "UTF-8";
		try
		{
			//String _name = URLEncoder.encode(getName(), ENCODING_TYPE);
			String _healthState = URLEncoder.encode(Integer.toString(getHealthState()), ENCODING_TYPE);
			return String.format("states[]=%s", _healthState);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	

}
