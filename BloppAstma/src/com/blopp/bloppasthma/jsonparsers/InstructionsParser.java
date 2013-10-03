package com.blopp.bloppasthma.jsonparsers;


import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.JsonModels.InstructionsResult;

import android.util.Log;

public class InstructionsParser extends GenericJSONParser
{
	private static final String PHPPage = "get_instructions.php?";
	private DownloadImageTask imageTask;
	private InstructionsResult instructionResult;


	public InstructionsParser(int medicineId)
	{
		super(PHPPage + "medicine_id=" + medicineId);
		Log.d(this.getClass().getSimpleName(), "URL = " + URL);
	}

	public void initializeDataFromJSON(String result)
	{
		JSONObject object;
		try
		{
			object = new JSONObject(result);
			JSONObject ir = object.getJSONObject("instructions");
			instructionResult = new InstructionsResult();
			instructionResult.setEffect(ir.getString("effect"));
			instructionResult.setId(ir.getInt("id"));
			instructionResult.setImageUrl(ir.getString("url"));
			instructionResult.setInstructions(ir.getString("information"));

		} catch (JSONException e)
		{
			e.printStackTrace();
		}
	
		imageTask = new DownloadImageTask(instructionResult.getImageUrl());
	}

	public InstructionsResult getResult()
	{
		return this.instructionResult;
	}

	public DownloadImageTask getImageTask()
	{
		return this.imageTask;
	}

}
