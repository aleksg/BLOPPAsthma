package com.blopp.bloppasthma.airqualityfeed;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.blopp.bloppasthma.models.AirQualityAtDay;
import com.blopp.bloppasthma.models.AirQualityState;

public class AirQualityCast extends GenericAirQualityJSONParser
{

	private AirQualityAtDay airQualityAtDayModel;
	private String TAG = AirQualityCast.class.getSimpleName();

	public AirQualityCast()
	{
		super();
	}

	public AirQualityAtDay getAirQualityStateAtDayModel()
	{
		return this.airQualityAtDayModel;
	}

	@Override
	public void initializeDataFromJSON(String result)
	{
		airQualityAtDayModel = new AirQualityAtDay();
		ArrayList<AirQualityState> airQualityList = new ArrayList<AirQualityState>();
		JSONArray json_array;
		try
		{
			json_array = new JSONArray(result);	
			int size = json_array.length();
			if(size >= 1)
			{
				JSONObject place = (JSONObject) json_array.get(0);
				JSONObject highestAqiIndex = (JSONObject)place.get("HighestAqiIndex");
				JSONObject station = (JSONObject)place.get("Station");
				AirQualityState quality = new AirQualityState()
				.setAQI(highestAqiIndex.getInt("Index"))
				.setColor(highestAqiIndex.getString("Color"))
				.setShortDescription(highestAqiIndex.getString("ShortDescription"))
				.setLongDescription(highestAqiIndex.getString("Description"))
				.setLocation(station.getString("Name"));
				airQualityList.add(quality);
				
			}
			
			airQualityAtDayModel.setAirQualityAtDay(airQualityList);
			
		} catch (JSONException e)
		{
			Log.d(TAG, "Could not parse json");
			e.printStackTrace();
		}
	}

}
