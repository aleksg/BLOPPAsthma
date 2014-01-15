package com.blopp.bloppasthma.jsonmodels;

public class LogDosesModel
{
	private int id, childId, medicineId, healthStateId, planId, pollenStateId;
	private int reward;
	private String date, time;

	public LogDosesModel()
	{
	}

	public int getPollenStateId()
	{
		return pollenStateId;
	}

	public LogDosesModel setPollenStateId(int pollen_state_id)
	{
		this.pollenStateId = pollen_state_id;
		return this;
	}

	public int getId()
	{
		return id;
	}

	public LogDosesModel setId(int id)
	{
		this.id = id;
		return this;
	}

	public int getReward()
	{
		return reward;
	}

	public LogDosesModel setReward(int reward)
	{
		this.reward = reward;
		return this;
	}

	public int getChildId()
	{
		return childId;
	}

	public LogDosesModel setChildId(int childId)
	{
		this.childId = childId;
		return this;
	}

	public int getMedicineId()
	{
		return medicineId;
	}

	public LogDosesModel setMedicineId(int medicineId)
	{
		this.medicineId = medicineId;
		return this;
	}

	public int getHealthStateId()
	{
		return healthStateId;
	}

	public LogDosesModel setHealthStateId(int healthStateId)
	{
		this.healthStateId = healthStateId;
		return this;
	}

	public int getPlanId()
	{
		return planId;
	}

	public LogDosesModel setPlanId(int planId)
	{
		this.planId = planId;
		return this;
	}

	public String getDate()
	{
		return date;
	}

	public LogDosesModel setDate(String date)
	{
		this.date = date;
		return this;
	}

	public String getTime()
	{
		return time;
	}

	public LogDosesModel setTime(String time)
	{
		this.time = time;
		return this;
	}

}
