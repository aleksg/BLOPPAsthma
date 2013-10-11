package com.blopp.bloppasthma.tests;

import org.junit.Assert;

import com.blopp.bloppasthma.JsonModels.AddChildPostModel;
import com.blopp.bloppasthma.jsonposters.AddChildPoster;

import android.test.AndroidTestCase;

public class AddChildTest extends AndroidTestCase
{
	private AddChildPoster poster;
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		AddChildPostModel model = new AddChildPostModel();
		poster = new AddChildPoster(model.toString());
		poster.execute();
		poster.get();
	}
	
	public void resultReceived()
	{
		Assert.assertNotNull(poster.getReceivedChildId());
	}
}
