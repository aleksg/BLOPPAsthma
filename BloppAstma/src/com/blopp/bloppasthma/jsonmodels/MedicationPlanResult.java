package com.blopp.bloppasthma.jsonmodels;

import java.util.ArrayList;

import com.blopp.bloppasthma.models.MedicinePlanModel;


public class MedicationPlanResult
{

	private boolean sqlSuccess;
	private int childId;
	private String query;
	private ArrayList<MedicinePlanModel> plans;
	public MedicationPlanResult()
	{
	}
	public boolean isSqlSuccess()
	{
		return sqlSuccess;
	}
	public MedicationPlanResult setSqlSuccess(boolean sqlSuccess)
	{
		this.sqlSuccess = sqlSuccess;
		return this;
	}
	public int getChildId()
	{
		return childId;
	}
	public MedicationPlanResult setChildId(int childId)
	{
		this.childId = childId;
		return this;
	}
	public String getQuery()
	{
		return query;
	}
	public MedicationPlanResult setQuery(String query)
	{
		this.query = query;
		return this;
	}
	public ArrayList<MedicinePlanModel> getPlans()
	{
		return plans;
	}
	public MedicationPlanResult setPlans(ArrayList<MedicinePlanModel> plans)
	{
		this.plans = plans;
		return this;
	}

}
