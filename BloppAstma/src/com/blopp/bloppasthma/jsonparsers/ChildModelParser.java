package com.blopp.bloppasthma.jsonparsers;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.blopp.bloppasthma.jsonmodels.ChildResultModel;
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.BLOPParser;

public class ChildModelParser extends GenericJSONParser implements BLOPParser
{
	public static final String PHPPAGE = MyURL + "get_child.php";
	private ChildResultModel child;

	public ChildModelParser(int childId)
	{
		super(PHPPAGE + "?child_id=" + childId);
		TAG = this.getClass().getSimpleName();
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			JSONObject information = json_data.getJSONObject("information");
			this.child = new ChildResultModel()
					.setId(information.getInt("id"))
					.setName(information.getString("name"))
					.setPersNum(information.getInt("pers_num"))
					.setCredits(information.getInt("credits"))
					.setMedicalPlanId(information.getInt("medical_plan_id"));
			System.out.println("Child is null" + (child==null));
		} catch (JSONException e)
		{
			Log.e(TAG, e.getMessage());
		}

	}

	public ChildResultModel getLogResult()
	{
		return this.child;
	}
}
