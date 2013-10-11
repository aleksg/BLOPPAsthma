package com.blopp.bloppasthma.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(AirQualityTests.class);
		suite.addTestSuite(AddChildTest.class);
		//$JUnit-END$
		return suite;
	}

}
