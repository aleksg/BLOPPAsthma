package com.blopp.bloppapi.tests;

import org.junit.Test;

import com.blopp.bloppapi.parsers.CreditParser;

import junit.framework.TestCase;

public class ChildCreditTest extends TestCase
{
	private CreditParser parser;
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		parser = new CreditParser(52);
		
	}
	
	//Should contain tests that registers a new treatment, and ensures that the resulting credits are what they should be. 
	@Test
	public void test(){
		assertNotNull(parser);
		assertNotNull(parser.parse());
		assertNotNull(parser.parse().getCredits());
		System.out.println("Child has: " + parser.parse().getCredits() + " stars");
	}
}
