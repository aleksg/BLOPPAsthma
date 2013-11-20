package com.blopp.bloppapi.posters;

import com.blopp.bloppapi.gsondeserializers.ChildDeserializer;
import com.blopp.bloppapi.models.Child;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddChildPoster extends BLOPPPoster<Child>
{
	private static final String phpPage = "add_child?";
	public AddChildPoster(String params)
	{
		super(phpPage, params);
	}
	
	@Override
	public Child parseData()
	{
//		System.out.println(getReply());
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Child.class, new ChildDeserializer());
		Gson gson = builder.create();
		Child child = gson.fromJson(getReply(), Child.class);
		
		return child;
	}
	
	

}
