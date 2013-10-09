package com.blopp.bloppasthma.airqualityfeed;

import com.blopp.bloppasthma.jsonparsers.GenericJSONParser;
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON;
import com.blopp.bloppasthma.jsonparsers.IInitializeFromJSON.AirQualityParser;


/**
 * Generic parser that is used by some classes to retrieve data from the database via JSON.
 * Implements IInitializeFromJSON, which contains a method for converting a JSON-object into it's respective model 
 * @author gisvold
 *
 */
public abstract class GenericAirQualityJSONParser extends GenericJSONParser implements IInitializeFromJSON, AirQualityParser
{

	public GenericAirQualityJSONParser() {
		super(MyURL);
		URL = MyURL;
	}
	
}
