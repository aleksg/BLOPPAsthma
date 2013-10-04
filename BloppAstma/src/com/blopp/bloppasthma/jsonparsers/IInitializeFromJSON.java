package com.blopp.bloppasthma.jsonparsers;

/**
 * Used by GenericParser and every subclass to initialize objects based on the databaseresult formatted as JSON-objects
 * @author aarseth_90
 *
 */
public interface IInitializeFromJSON
{
	/**
	 * Initializes the appropriate object, using data from JSON. 
	 * @param result, the result containing JSON-formatted data
	 */
	abstract void initializeDataFromJSON(String result);
	
	public interface BLOPParser
	{
		public static final String MyURL = "http://folk.ntnu.no/yngvesva/blopp/";
	}
	public interface AirQualityParser
	{
		//TODO: Fix URL
		public static final String MyURL = "http://dataservice.luftkvalitet.info/airqualityindex/StationsInArea/v2/?area=Trondheim&format=xml&hoursback=-3&key=yHYqw2iD";
	}
	
}
