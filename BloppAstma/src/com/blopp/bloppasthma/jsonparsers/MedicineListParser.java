package com.blopp.bloppasthma.jsonparsers;

import java.util.ArrayList;


import com.blopp.bloppasthma.models.Medicine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.div.ColorMeds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class MedicineListParser extends GenericJSONParser
{
	public static String phpPage = "get_medicine_list.php";
	private ArrayList<Medicine> medicines;
	private Context context;
	public MedicineListParser(Context context)
	{
		super(phpPage);
		this.context = context;
	}

	public void initializeDataFromJSON(String result)
	{
		Log.d("mlp", "started init data");
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			JSONArray array = json_data.getJSONArray("medicines");
			medicines = new ArrayList<Medicine>();
			for(int i=0; i<array.length(); i++)
			{
				JSONObject med = array.getJSONObject(i);
				Medicine m = new Medicine(
						med.getInt("id"),
						med.getString("name"),
						med.getString("color"),
						medColorToBitmap(med.getString("color"))
						);
				medicines.add(m);
			}
			
		} catch (JSONException e)
		{
			Log.d("mlp", e.getMessage());
			e.printStackTrace();
		}

	}
	public ArrayList<Medicine> getMedicines()
	{
		return medicines;
	}
	
	// TOO LITTLE TIME TO MAKE IMAGE DOWNLOADER
	public Bitmap medColorToBitmap(String color)
	{
		return BitmapFactory.decodeResource(context.getResources(), ColorMeds.medicineImage(color));
	}
}
