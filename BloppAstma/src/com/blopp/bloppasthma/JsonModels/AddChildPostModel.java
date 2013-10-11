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
	private String SSN;
	
	public int getHealthState()
	{
		return healthState;
	}

	public String getName()
	{
		return name;
	}

	public String getSSN()
	{
		return SSN;
	}
	
	public AddChildPostModel()
	{
		this.healthState = 3;
		this.name = "jonas melqvist";
		this.SSN = "27272727272";
	}
	
	@Override
	public String toString()
	{
		String _states, _name, _ssn;
		try{
			_states = URLEncoder.encode(Integer.toString(getHealthState()), ENCODING_TYPE);
			_name = URLEncoder.encode(getName(), ENCODING_TYPE);
			_ssn = URLEncoder.encode(getSSN(), ENCODING_TYPE);
			return String.format("name=%s&persnum=%s&states=%s", _name, _ssn, _states);
		}catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			Log.d(TAG, "Did not manage to encode properties");
		}
		return null;
	}
	
	
	
}
