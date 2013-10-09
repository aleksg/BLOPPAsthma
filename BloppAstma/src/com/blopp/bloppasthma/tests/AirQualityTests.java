package com.blopp.bloppasthma.tests;

import org.junit.Assert;


import android.test.AndroidTestCase;

import com.blopp.bloppasthma.airqualityfeed.AirQualityCast;

public class AirQualityTests extends AndroidTestCase
{
	private AirQualityCast airQualityCast;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		airQualityCast = new AirQualityCast();
		airQualityCast.execute();
		airQualityCast.get();
	}
	
	
	public void objectIsNotNull()
	{
		Assert.assertNotNull(airQualityCast.getPollenStateAtDayModel());
	}
	
}
