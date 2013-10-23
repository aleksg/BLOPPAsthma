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
	private String medicineName;
	private String medicineColor;
	private String time;
	
	public MedicinePlanModel()
	{
		
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
	public String getMedicineColor()
	{
		return medicineColor;
	}
	public MedicinePlanModel setMedicineColor(String medicineColor)
	{
		this.medicineColor = medicineColor;
		return this;
	}
	public String getMedicineName()
	{
		return medicineName;
	}
	public MedicinePlanModel setMedicineName(String medicineName)
	{
		this.medicineName = medicineName;
		return this;
	}
	
	
	
}
