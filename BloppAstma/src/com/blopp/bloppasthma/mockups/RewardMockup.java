package com.blopp.bloppasthma.mockups;

import android.graphics.Bitmap;

public class RewardMockup
{
	private int stars;
	private String description;
	private boolean isReceived;
	private Bitmap bitmap;
	public RewardMockup()
	{	
		
	}

	public int getStars()
	{
		return stars;
	}

	public RewardMockup setStars(int stars)
	{
		this.stars = stars;
		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public RewardMockup setDescription(String description)
	{
		this.description = description;
		return this;
	}

	public boolean isReceived()
	{
		return isReceived;
	}

	public RewardMockup setReceived(boolean isReceived)
	{
		this.isReceived = isReceived;
		return this;
	}
	
	public RewardMockup setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		return this;
	}
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}
	
}
