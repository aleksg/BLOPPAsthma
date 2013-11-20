package com.blopp.bloppasthma.JsonModels;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.util.Log;

public class AddChildPostModel
{
	private static final String ENCODING_TYPE = "UTF-8";
	private static final String TAG = AddChildPostModel.class.getSimpleName();
	private int healthState;	
	private String name;
	
	
	public int getHealthState()
	{
		return healthState;
	}

	public String getName()
	{
		return name;
	}

	
	
	public AddChildPostModel()
	{
		this.healthState = 1;
		this.name = "jonas melqvist";
	}
	
	@Override
	public String toString()
	{
		String _states, _name, _ssn;
		try{
			_states = URLEncoder.encode(Integer.toString(getHealthState()), ENCODING_TYPE);
			_name = URLEncoder.encode(getName(), ENCODING_TYPE);
			
			return String.format("name=%s&states[]=%s", _name, _states);
		}catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			Log.d(TAG, "Did not manage to encode properties");
		}
		return null;
	}
	
	
	
}
