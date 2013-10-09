package com.blopp.bloppasthma.mockups;

import android.graphics.Bitmap;

public class Reward
{
	private int stars;
	private String description;
	private boolean isOrdered;
	private Bitmap bitmap;
	private boolean repeat;
	private int id;
	
	public Reward()
	{	
		
	}
	public Reward(int currentMax)
	{
		this.id = currentMax+1;
	}
	public int getId()
	{
		return id;
	}
	
	public int getStars()
	{
		return stars;
	}

	public Reward setStars(int stars)
	{
		this.stars = stars;
		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public Reward setDescription(String description)
	{
		this.description = description;
		return this;
	}

	public boolean isOrdered()
	{
		return isOrdered;
	}

	public Reward setOrdered(boolean order)
	{
		this.isOrdered = order;
		return this;
	}
	
	public Reward setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		return this;
	}
	public Bitmap getBitmap()
	{
		return this.bitmap;
	}

	public boolean isRepeat()
	{
		return repeat;
	}

	public Reward setRepeat(boolean repeat)
	{
		this.repeat = repeat;
		return this;
	}
}
