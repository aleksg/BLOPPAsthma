package com.blopp.bloppapi.posters;

import java.io.IOException;
import java.net.MalformedURLException;

import com.blopp.bloppapi.gsondeserializers.ChildDeserializer;
import com.blopp.bloppapi.models.Child;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class AddChildPoster extends BLOPPPoster
{
	private static final String phpPage = "add_child?";
	public AddChildPoster(String params)
	{
		super(phpPage, params);
	}
	
	@Override
	public void parseData()
	{
		System.out.println(getReply());
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Child.class, new ChildDeserializer());
		Gson gson = builder.create();
		
		Child child = gson.fromJson(getReply(), Child.class);
		System.out.println("Child id = " + child.getUid());
		System.out.println("Medical plan id = " + child.getMedicalPlanId());

//		int childId = object.get("child_id").getAsInt();
//		int medicalPlanId = object.get("medical_plan_id").getAsInt();
//		
//		System.out.println("ChildId = " + Integer.toString(childId));
//		System.out.println("MedicalPlanId = " + Integer.toString(medicalPlanId));
	}
	
	

}
