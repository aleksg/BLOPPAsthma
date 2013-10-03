package com.blopp.bloppasthma.jsonposters;


import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.JsonModels.HealthStatePostResult;

import android.util.Log;

public class HealthStatePoster extends DatabasePoster
{
	private static final String TAG = HealthStatePoster.class.getSimpleName();
	private static final String phpPage = "set_child_state.php/";
	private HealthStatePostResult healthStatePostResult;
	
	public HealthStatePoster(String params)
	{
		super(params, phpPage);
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject result_data = null;
		healthStatePostResult = new HealthStatePostResult();
		Log.d(TAG, result);
		try
		{
			Log.d(TAG, "Trying to get json-result");
			
			result_data = new JSONObject(result);
			healthStatePostResult.setSqlSuccess(result_data.getBoolean("sqlsuccess"));
			
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	public HealthStatePostResult getHealthStatePostResult()
	{
		return this.healthStatePostResult;
	}

}
