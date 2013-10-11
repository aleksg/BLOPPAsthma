package com.blopp.bloppasthma.jsonparsers;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.BLOPParser;

public class HealthStateParser extends GenericJSONParser implements BLOPParser
{
	public static final String TAG = HealthStateParser.class.getSimpleName(); 
	private static String PHP_PAGE = MyURL + "get_child_state?";
	private int healthStateId = 0;
	private boolean sqlsuccess = false;
	

	public HealthStateParser(String urltail)
	{
		super(PHP_PAGE + urltail);
		Log.d(TAG, urltail);
	}
	public HealthStateParser(int childId)
	{
		super(PHP_PAGE + "child_id=" + childId);
		Log.d(TAG, "child_id=" + childId);
	}
	public void initializeDataFromJSON(String result)
	{
		JSONObject jsonData;
		try
		{
			Log.d(TAG, "Received result");
			Log.d(TAG, result);
			jsonData = new JSONObject(result);
			sqlsuccess = jsonData.getBoolean("sqlsuccess");
			JSONObject state = jsonData.getJSONObject("state");
			this.healthStateId = Integer.parseInt(state.getString("id"));

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public int getHealthStateId()
	{
		return this.healthStateId;
	}

	public boolean isSqlSuccess()
	{
		return this.sqlsuccess;
	}
}
