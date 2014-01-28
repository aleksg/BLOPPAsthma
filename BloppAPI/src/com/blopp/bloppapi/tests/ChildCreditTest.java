package com.blopp.bloppapi.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.junit.Test;

import com.blopp.bloppapi.models.ChildCredits;
import com.blopp.bloppapi.models.Treatment;
import com.blopp.bloppapi.parsers.CreditParser;
import com.blopp.bloppapi.posters.TreatmenttPoster;

public class ChildCreditTest extends TestCase
{
	private CreditParser parser;
	private TreatmenttPoster treatmentPoster;
	private ChildCredits initialCredits;
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		parser = new CreditParser(52);
		
	}
	
	public Treatment initTreatment() 
	{
		Treatment treatment = new Treatment();
		treatment.setUid(52);
		treatment.setMedicineID(1);
		treatment.setHealthStateID(1);
		treatment.setTime("13:22:00");
		treatment.setDayDate("2013-11-20");
		return treatment;
	}
	
	//Should contain tests that registers a new treatment, and ensures that the resulting credits are what they should be. 
	@Test
	public void test(){
		assertNotNull(parser);
		assertNotNull(parser.parse());
		assertNotNull(parser.parse().getCredits());
		initialCredits = parser.parse();
		System.out.println("Child has: " + parser.parse().getCredits() + " stars");
		addTreatment();
		getNewCredits();
	}
	
	@Test
	private void getNewCredits() {
		parser = new CreditParser(52);
		assertNotSame(parser.parse().getCredits(), initialCredits);
		System.out.println("New credits are: " + parser.parse().getCredits());
	}

	public void addTreatment(){
		ChildCredits credits;
		try {
			treatmentPoster = new TreatmenttPoster(initTreatment().toPostParameters());
			treatmentPoster.postData();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
