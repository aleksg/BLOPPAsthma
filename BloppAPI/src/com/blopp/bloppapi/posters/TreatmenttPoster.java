package com.blopp.bloppapi.posters;

import com.blopp.bloppapi.gsondeserializers.ChildCreditsDeserializer;
import com.blopp.bloppapi.models.Child;
import com.blopp.bloppapi.models.Treatment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TreatmenttPoster extends BLOPPPoster<Child>
{
	
	private static final String phpPage = "register_medicine_taken?";
	public TreatmenttPoster(String params)
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
		System.out.println("MedicineID = " + child.getCredits());
		System.out.println("Healthstate = " + child.getHealthState());
		System.out.println("MedicalPlanDoseID = " + child.getMedicalPlanId());
				
		return child;
	}
	
}
