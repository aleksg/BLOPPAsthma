package com.blopp.bloppapi.parsers;

import com.blopp.bloppapi.gsondeserializers.ChildCreditsDeserializer;
import com.blopp.bloppapi.models.ChildCredits;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreditParser extends BLOPPParser
{
	private static final String relativeURL = "get_child?";
	
	public CreditParser(int forChildId)
	{
		super(relativeURL + "child_id=" + forChildId);
	}
	@Override
	public ChildCredits parse()
	{
		String result = fetchData();
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(ChildCredits.class, new ChildCreditsDeserializer());
		Gson gson = builder.create();
		ChildCredits childCredits = gson.fromJson(result, ChildCredits.class);
		
		return childCredits;
	}
	
	
}
