package com.blopp.bloppapi.posters;

import com.blopp.bloppapi.gsondeserializers.ChildCreditsDeserializer;
import com.blopp.bloppapi.models.Child;
import com.blopp.bloppapi.models.ChildCredits;
import com.blopp.bloppapi.models.Treatment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TreatmenttPoster extends BLOPPPoster<ChildCredits>
{
	
	private static final String phpPage = "register_medicine_taken?";
	public TreatmenttPoster(String params)
	{
		super(phpPage, params);
	}

	@Override
	public ChildCredits parseData() 
	{
		System.out.println(getReply());
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Child.class, new ChildCreditsDeserializer());
		Gson gson = builder.create();
		ChildCredits childcredits = gson.fromJson(getReply(), ChildCredits.class);
		System.out.println("MedicineID = " + childcredits.getCredits());

		return childcredits;
	}
	
}
