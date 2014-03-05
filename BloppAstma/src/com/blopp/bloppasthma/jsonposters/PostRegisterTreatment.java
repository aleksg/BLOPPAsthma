package com.blopp.bloppasthma.jsonposters;

import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.jsonmodels.RegisterTreatmentResult;


public class PostRegisterTreatment extends DatabasePoster
{
	public static final String phpPage = "register_medicine_taken.php/";
	private RegisterTreatmentResult treatmentResult;
	
	public PostRegisterTreatment(String params)
	{
		super(params, phpPage);
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		System.out.println(result);
		this.treatmentResult = new RegisterTreatmentResult();
		try
		{
			json_data = new JSONObject(result);
			treatmentResult.setReward(json_data.getInt("reward"));
			treatmentResult.setSqlSuccess(json_data.getBoolean("sqlsuccess"));
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	public RegisterTreatmentResult getTreatmentResult()
	{
		return this.treatmentResult;
	}

}

