package com.blopp.bloppasthma.mockups;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Reward
{
	private int stars;
	private String description;
	private boolean isOrdered;
	private byte[] imageBytes;
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
	
	public Reward setByteArray(byte[] bytes)
	{
		this.imageBytes = bytes;
		return this;
	}
	public byte[] getByteArray()
	{
		return this.imageBytes;
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
	public Bitmap getBitmap()
	{
		return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
	}
	public Reward setBitmap(Bitmap bitmap)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
		return this.setByteArray(out.toByteArray());
	}
}
