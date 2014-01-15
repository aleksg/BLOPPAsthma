package com.blopp.bloppasthma.jsonposters;

import org.json.JSONException;
import org.json.JSONObject;

import com.blopp.bloppasthma.jsonmodels.AddChildPostModel;

public class AddChildPoster extends DatabasePoster
{
	private static final String TAG = AddChildPoster.class.getSimpleName();
	private static final String phpPage = "add_child.php/";
	private int receivedChildId;
	
	public AddChildPoster(String params)
	{
		super(params, phpPage);
	}

	@Override
	public void initializeDataFromJSON(String result)
	{
		JSONObject result_data = null;
		try
		{
			result_data = new JSONObject(result);
			this.receivedChildId = result_data.getInt("child_id");
		}catch(JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getReceivedChildId()
	{
		return this.receivedChildId;
	}

}
