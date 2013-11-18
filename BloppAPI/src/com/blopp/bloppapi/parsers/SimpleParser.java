package com.blopp.bloppapi.parsers;

public class SimpleParser extends BLOPPParser
{

	public SimpleParser(String relativeURL)
	{
		super(relativeURL);
		
	}

	@Override
	public void parse(String result)
	{
		fetchData();
		
	}

}
