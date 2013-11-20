package com.blopp.bloppapi.gsondeserializers;
import java.lang.reflect.Type;

import com.blopp.bloppapi.models.Child;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;


public class ChildDeserializer implements JsonDeserializer<Child>
{

	@Override
	public Child deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException
	{
		Child child = new Child();
		JsonObject jsonObject = json.getAsJsonObject();
		child.setMedicalPlanId(jsonObject.get("medical_plan_id").getAsInt());
		child.setUid(jsonObject.get("child_id").getAsInt());
		
		return child;
	}

}
