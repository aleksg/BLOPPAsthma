package com.blopp.bloppasthma.models;

import java.util.concurrent.ExecutionException;

import android.util.Log;

import com.blopp.bloppasthma.jsonparsers.ChildModelParser;

public class ChildRewards {
	private int credits;
	protected ChildModelParser childModelParser;
	
	public ChildRewards(int childId)
	{
		childModelParser = new ChildModelParser(childId);
	}
	
	public void initChildModelParser()
	{
		childModelParser.execute();
		try {
			childModelParser.get();
		} catch (InterruptedException e) {
			Log.e("ChildRewards", e.getMessage());
		} catch (ExecutionException e) {
			Log.e("ChildRewards", e.getMessage());
		}
		this.credits = childModelParser.getLogResult().getCredits();
	}
	
	public int getCredits()
	{
		return credits;
	}
}
