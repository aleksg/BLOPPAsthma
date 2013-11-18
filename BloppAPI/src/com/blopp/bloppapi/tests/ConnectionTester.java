package com.blopp.bloppapi.tests;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.blopp.bloppapi.parsers.SimpleParser;

public class ConnectionTester extends TestCase
{
	private SimpleParser parser;
	@Before
	public void setUp() throws Exception
	{
		
		parser = new SimpleParser("get_child_state&id=52");
	}
	
	
	
	@Test
	public void test()
	{
		assertNotNull(parser.fetchData());
		
	}

}
