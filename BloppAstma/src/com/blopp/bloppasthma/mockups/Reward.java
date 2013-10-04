package com.blopp.bloppasthma.mockups;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class Reward
{
	private int stars;
	private String description;
	private boolean isReceived;
	private Bitmap bitmap;
	private boolean repeat;
	public Reward()
	{	
		
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

	public boolean isReceived()
	{
		return isReceived;
	}

	public Reward setReceived(boolean isReceived)
	{
		this.isReceived = isReceived;
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
