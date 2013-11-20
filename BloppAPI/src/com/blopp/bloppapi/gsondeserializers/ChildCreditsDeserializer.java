package com.blopp.bloppapi.gsondeserializers;

import java.lang.reflect.Type;

import com.blopp.bloppapi.models.ChildCredits;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ChildCreditsDeserializer implements JsonDeserializer<ChildCredits>
{

	@Override
	public ChildCredits deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException
	{
		ChildCredits childCredits = new ChildCredits();
		JsonObject jsonObject = json.getAsJsonObject();
		JsonObject information = jsonObject.get("information").getAsJsonObject();
		childCredits.setCredits(information.get("credits").getAsInt());
		return childCredits;
	}

}
