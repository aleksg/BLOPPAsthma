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
		this.medicinePlanMap = new HashMap<String, String>();
	}
	public String getTime()
	{
		return time;
	}
	public MedicinePlanModel setTime(String time)
	{
		this.time = time;
		return this;
	}
	public int getId()
	{
		return id;
	}
	public MedicinePlanModel setId(int id)
	{
		this.id = id;
		return this;
	}
	public String getLabel()
	{
		return label;
	}
	public MedicinePlanModel setLabel(String label)
	{
		this.label = label;
		return this;
	}
	public int getMedicalPlanId()
	{
		return medicalPlanId;
	}
	public MedicinePlanModel setMedicalPlanId(int medicalPlanId)
	{
		this.medicalPlanId = medicalPlanId;
		return this;
	}
	public int getHealthStateId()
	{
		return healthStateId;
	}
	public MedicinePlanModel setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
		return this;
	}

	public int getMedicineId()
	{
		return medicineId;
	}
	public MedicinePlanModel setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
		return this;
	}
	public String getMedicineKarotzColor()
	{
		return medicineKarotzColor;
	}
	public MedicinePlanModel setMedicineKarotzColor(String medicineKarotzColor)
	{
		this.medicineKarotzColor = medicineKarotzColor;
		return this;
	}
	/**
	 * Adds an entry to the plan map with time and name
	 * @param medicationTime
	 * @param medicineName
	 */
	public MedicinePlanModel addEntryToMap(String medicationTime, String medicineName)
	{
		this.medicinePlanMap.put(medicationTime, medicineName);
		return this;
	}
	/**
	 * 
	 * @return the hashmap with key: Time and value: Medicine
	 */
	public HashMap<String, String> getMedicinePlanMap()
	{
		return this.medicinePlanMap;
	}
	
	
}
