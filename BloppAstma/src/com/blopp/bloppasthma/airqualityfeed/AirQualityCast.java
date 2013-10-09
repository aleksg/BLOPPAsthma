package com.blopp.bloppasthma.airqualityfeed;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.blopp.bloppasthma.models.AirQuality;
import com.blopp.bloppasthma.models.AirQualityAtDay;

public class AirQualityCast extends GenericAirQualityJSONParser
{

	private AirQualityAtDay airQualityAtDayModel;

	public AirQualityCast()
	{
		super();
	}

	public AirQualityAtDay getPollenStateAtDayModel()
	{
		return this.airQualityAtDayModel;
	}

	@Override
	public void initializeDataFromJSON(String result)
	{
		airQualityAtDayModel = new AirQualityAtDay();
		ArrayList<AirQuality> airQualityList = new ArrayList<AirQuality>();
		
		JSONArray json_array;
		try
		{
			json_array = new JSONArray(result);	
			for (int i=0; i<json_array.length(); i++)
			{
				JSONObject place = (JSONObject) json_array.get(i);
				JSONObject highestAqiIndex = (JSONObject)place.get("HighestAqiIndex");
				JSONObject station = (JSONObject)place.get("Station");
				AirQuality quality = new AirQuality()
										.setAQI(highestAqiIndex.getInt("Index"))
										.setColor(highestAqiIndex.getString("Color"))
										.setDescription(highestAqiIndex.getString("ShortDescription"))
										.setLocation(station.getString("Name"));
				airQualityList.add(quality);
			}
			
		} catch (JSONException e)
		{
			Log.d(TAG, "Could not parse json");
			e.printStackTrace();
		}
	}

}
