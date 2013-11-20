package com.blopp.bloppapi.posters;

import com.blopp.bloppapi.models.Child;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreditPoster extends BLOPPPoster<Child>
{
	
	private static final String phpPage = "";
	public CreditPoster(String params)
	{
		super(phpPage, params);
	}

	@Override
	public Child parseData() 
	{
		System.out.println(getReply());
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Child.class, new ChildCreditsDeserializer());
		Gson gson = builder.create();
		Child child = gson.fromJson(getReply(), Child.class);
		System.out.println("Child id = " + child.getUid());
		System.out.println("Total credits = " + child.getCredits());
				
		return child;
	}
	
}
