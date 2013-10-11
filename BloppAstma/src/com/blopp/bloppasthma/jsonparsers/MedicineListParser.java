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
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.BLOPParser;
public class MedicineListParser extends GenericJSONParser implements BLOPParser
{
	public static String phpPage = MyURL + "get_medicine_list.php";
	private ArrayList<Medicine> medicines;
	private Context context;
	public MedicineListParser(Context context)
	{
		super(phpPage);
		this.context = context;
	}

	public void initializeDataFromJSON(String result)
	{
		
		JSONObject json_data;
		try
		{
			json_data = new JSONObject(result);
			JSONArray array = json_data.getJSONArray("medicines");
			medicines = new ArrayList<Medicine>();
			for(int i=0; i<array.length(); i++)
			{
				JSONObject med = array.getJSONObject(i);

				Medicine m = new Medicine()
					.setBitmap(medColorToBitmap(med.getString("color")))
					.setColor(med.getString("color"))
					.setName(med.getString("name"))
					.setId(med.getInt("id"));
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
	
	// TOO LITTLE TIME TO MAKE IMAGE DOWNLOADER. WHAT THE FUCK????
	public Bitmap medColorToBitmap(String color)
	{
		return BitmapFactory.decodeResource(context.getResources(), ColorMeds.medicineImage(color));
	}
}
