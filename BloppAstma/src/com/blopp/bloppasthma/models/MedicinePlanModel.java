package com.blopp.bloppasthma.models;

import java.io.Serializable;
import java.util.HashMap;

public class MedicinePlanModel implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5169335014428191642L;
	private int id;
	private String label;
	private int medicalPlanId;
	private int healthStateId;
	private int medicineId;
	private String medicineKarotzColor;
	private HashMap<String, String> medicinePlanMap; //<Time, Medicine>
	private String time;
	public MedicinePlanModel()
	{
		medicinePlanMap = new HashMap<String, String>();
	}
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public int getMedicalPlanId()
	{
		return medicalPlanId;
	}
	public void setMedicalPlanId(int medicalPlanId)
	{
		this.medicalPlanId = medicalPlanId;
	}
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public void setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
	}

	public int getMedicineId()
	{
		return medicineId;
	}
	public void setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
	}
	public String getMedicineKarotzColor()
	{
		return medicineKarotzColor;
	}
	public void setMedicineKarotzColor(String medicineKarotzColor)
	{
		this.medicineKarotzColor = medicineKarotzColor;
	}
	/**
	 * Adds an entry to the plan map with time and name
	 * @param medicationTime
	 * @param medicineName
	 */
	public void addEntryToMap(String medicationTime, String medicineName)
	{
		this.medicinePlanMap.put(medicationTime, medicineName);
	}
	/**
	 * 
	 * @return the hashmap with key: Time and value: Name
	 */
	public HashMap<String, String> getMedicinePlanMap()
	{
		return this.medicinePlanMap;
	}
	
	
}
