package com.blopp.bloppapi.tests;

import junit.framework.Test;
import junit.framework.TestSuite;



public class AllTests
{
	public static Test suite(){
		TestSuite suite = new TestSuite(AllTests.class.getName());
		suite.addTestSuite(AddChildPostTest.class);
		suite.addTestSuite(ChildCreditTest.class);
		return suite;
		
	}
}

