package com.blopp.bloppasthma.airqualityfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.blopp.bloppasthma.models.AirQuality;
import com.blopp.bloppasthma.models.AirQualityAtDayModel;
import com.blopp.bloppasthma.models.PollenStateAtDayModel;

public class AirQualityCast {
	
	private AirQualityAtDayModel airQualityAtDayModel;
	
	public AirQualityCast()
	{
		super();
	}
	
	public void initializeDataFromXML(InputStream is)
	{
		try 
		{
			airQualityAtDayModel = new AirQualityAtDayModel();
			ArrayList<AirQuality> airQualityList = new ArrayList<AirQuality>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			
			Element element = null;
			//String location = getTagValue("Location", element);
			//int aqi = Integer.parseInt(getTagValue("AQI", element));
			
			airQualityAtDayModel.setAirQualityAtDay(null);
		
	} catch (ParserConfigurationException e) {
			e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}
	}
		
	
//	private static String getTagValue(String sTag, Element eElement)
//	{
//		
//	}
	
	public AirQualityAtDayModel getPollenStateAtDayModel()
	{
		return this.airQualityAtDayModel;
	}

}
