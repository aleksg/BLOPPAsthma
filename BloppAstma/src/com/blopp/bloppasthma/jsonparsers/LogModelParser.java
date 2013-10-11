package com.blopp.bloppasthma.jsonparsers;

import java.util.ArrayList;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.JsonModels.LogDayResult;
import com.blopp.bloppasthma.JsonModels.LogDosesModel;
import com.blopp.bloppasthma.JsonModels.LogResult;
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.BLOPParser;
import android.util.Log;

public class LogModelParser extends GenericJSONParser implements BLOPParser
{
	private LogResult logResult;
	private static final String PHPPAGE = MyURL + "get_log_days_for_child.php?",
			TAG = LogModelParser.class.getSimpleName();

	public LogModelParser(String urltail)
	{
		super(urltail);
	}

	public LogModelParser(int childId)
	{
		super(PHPPAGE + "child_id=" + childId);
		Log.d(TAG, URL);
	}

	public LogModelParser(int childId, int month, int year)
	{
		super(PHPPAGE + "child_id=" + childId + "&month=" + month + "&year="
				+ year);
		Log.d(TAG, URL);
		
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			this.logResult = new LogResult()
			.setSqlSuccess(json_data.getBoolean("sqlsuccess"))
			.setMonth(Integer.parseInt(json_data.getString("month")))
			.setYear(Integer.parseInt(json_data.getString("year")))
			.setQuery(json_data.getString("query"))
			.setChildId(Integer.parseInt(json_data
					.getString("child_id")));
			JSONArray array = (json_data.getJSONArray("days"));
			ArrayList<LogDayResult> logDayResults = new ArrayList<LogDayResult>();

			for (int i = 0; i < array.length(); i++)
			{
				JSONObject day = array.getJSONObject(i);
				LogDayResult log_day = new LogDayResult()
				
				.setDate(day.getString("date"))
				.setHealthStateId(Integer.parseInt(day.getString("health_state_id")));
				
				JSONArray doses = day.getJSONArray("doses");
				
				log_day.setLogDosesList(initDosesModel(doses));
				logDayResults.add(log_day);
			}
			logResult.setLogDayResults(logDayResults);

		} catch (JSONException e)
		{
			//Log.e(TAG, e.getMessage());
		}

	}
	private ArrayList<LogDosesModel> initDosesModel(JSONArray doses)
	{
		ArrayList<LogDosesModel> modelArray = new ArrayList<LogDosesModel>();
		for (int i = 0; i<doses.length(); i++)
		{
			LogDosesModel model = new LogDosesModel();
			try
			{
				JSONObject dose = doses.getJSONObject(i);
				model.setId(Integer.parseInt(dose.getString("id")))
				.setHealthStateId(Integer.parseInt(dose.getString("health_state_id")))
				.setTime(dose.getString("time"))
				.setDate(dose.getString("day_date"))
				.setChildId(Integer.parseInt(dose.getString("child_id")))
				.setReward(Integer.parseInt(dose.getString("reward")))
				.setMedicineId(Integer.parseInt(dose.getString("medicine_id")))
				.setPlanId(Integer.parseInt(dose.getString("medical_plan_dose_id")))
				.setPollenStateId(Integer.parseInt(dose.getString("pollen_state_id")));
			} catch (JSONException e)
			{
				e.printStackTrace();
			}
			modelArray.add(model);
			
		}
		
		return modelArray;
	}
	public LogResult getLogResult()
	{
		return this.logResult;
	}

}
