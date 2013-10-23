package com.blopp.bloppasthma.jsonparsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.blopp.bloppasthma.JsonModels.MedicationPlanResult;
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.BLOPParser;
import com.blopp.bloppasthma.models.MedicinePlanModel;

public class MedicationPlanParser extends GenericJSONParser implements BLOPParser
{
	public static String phpPage = MyURL + "get_plan.php?";
	private MedicationPlanResult medicationPlanResult;
	private static String TAG = MedicationPlanParser.class.getSimpleName();
	public MedicationPlanParser(int child_id)
	{
		super(phpPage + "child_id=" + child_id);
		
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			medicationPlanResult = new MedicationPlanResult()
					.setChildId(Integer.parseInt(json_data.getString("child_id")))
					.setQuery(json_data.getString("query"))
					.setSqlSuccess(json_data.getBoolean("sqlsuccess"));
			
			JSONArray array = json_data.getJSONArray("rows");
			ArrayList<MedicinePlanModel> arrayList = new ArrayList<MedicinePlanModel>();

			for (int i = 0; i < array.length(); i++)
			{
				
				JSONObject plan = array.getJSONObject(i);
				MedicinePlanModel mpl = new MedicinePlanModel();				
				mpl = new MedicinePlanModel()
						.setHealthStateId(plan.getInt("health_state_id"))
						.setId(plan.getInt("id"))
						.setMedicalPlanId(plan.getInt("medical_plan_id"))
						.setMedicineId(plan.getInt("medicine_id"))
						.setMedicineName(plan.getString("medicine_name"))
						.setTime(plan.getString("time"))
						.setMedicineColor(plan.getString("medicine_color"));
				arrayList.add(mpl);
			}
			Log.d(TAG, "Found " + arrayList.size() + " planned treatments");
			medicationPlanResult.setPlans(arrayList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public MedicationPlanResult medicationPlanResult()
	{
		return this.medicationPlanResult;
	}
}
