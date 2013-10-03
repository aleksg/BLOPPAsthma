package com.blopp.bloppasthma.models;

import android.graphics.Bitmap;

//Initial testing purposes for the log.
public class Medicine implements java.io.Serializable
{
	private static final long serialVersionUID = 611307667361582993L;
	private int id;
	private String name;
	private int instructionsId;
	private int imgId;
	private String color;
	private Bitmap bitmap;

	public Medicine(){
		
	}
	public Medicine setId(int id)
	{
		this.id = id;
		return this;
	}

	public String getName()
	{
		return this.name;
	}

	public Medicine setName(String name)
	{
		this.name = name;
		return this;
	}

	public Bitmap getBitmap()
	{
		return bitmap;
	}

	public Medicine setBitmap(Bitmap bitmap)
	{
		this.bitmap = bitmap;
		return this;
	}

	public int getId()
	{
		return this.id;
	}

	public int getInstructionsId()
	{
		return instructionsId;
	}

	public Medicine setInstructionsId(int instructionsId)
	{
		if (instructionsId < 0)
			throw new IllegalArgumentException(
					"instructionsId must be bigger than null");

		this.instructionsId = instructionsId;
		return this;
	}

	public int getImgId()
	{
		return imgId;
	}

	public Medicine setImgId(int imgId)
	{
		this.imgId = imgId;
		return this;
	}

	public String getColor()
	{
		return color;
	}

	public Medicine setColor(String color)
	{
		this.color = color;
		return this;
	}

}
